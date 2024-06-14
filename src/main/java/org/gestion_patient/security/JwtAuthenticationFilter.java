package org.gestion_patient.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entityDto.DataUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@AllArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("attemptAuthentification");
        String email, password;

        try{
            Map<String, String> requestMap = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            email = requestMap.get("email");
            password = requestMap.get("password");
            System.out.println(email);
            System.out.println(password);
        } catch (IOException e) {
            throw new AuthenticationServiceException(e.getMessage(), e);
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,password);
        return authenticationManager.authenticate(authenticationToken);
        }



    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("successfulAuthentification");
        String clientIpAddress = request.getRemoteAddr();
        User user = (User) authResult.getPrincipal();
        Algorithm algorithm =  Algorithm.HMAC256(DataUtil.TokenKey);
        String jwtAccessToken = JWT.create().withSubject(user.getUsername()) // Définit le sujet du jeton (le nom d'utilisateur)
                .withExpiresAt(new Date(System.currentTimeMillis()+60*60*1000)) // Définit la date d'expiration (15 minutes à partir de maintenant)
                .withIssuer(request.getRequestURL().toString()) // Définit l'émetteur du jeton (l'URL de la requête en cours)
                .withClaim("roles",user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .withClaim("ip", clientIpAddress) // Ajoute une réclamation personnalisée pour l'adresse IP du client
                .sign(algorithm);

        Map<String,String> idToken = new HashMap<>();
        try {
            idToken.put("accessToken", Crypto.cryptService(jwtAccessToken));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getOutputStream(),idToken);
    }

    }








