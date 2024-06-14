package org.gestion_patient.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private PraticienConnecteDetailService praticienConnecteDetailService;




    private AuthenticationConfiguration authConfiguration;


    //Cette méthode permet d’indiquer à Spring Security d’utiliser la classe PratitienConnecteDetailService pour authentifier des utilisateurs.
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(praticienConnecteDetailService).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();

    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf->csrf.disable())
            .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize.requestMatchers(HttpMethod.POST,"/praticien/usernamecheck").permitAll())
            .authorizeHttpRequests(authorize -> authorize.requestMatchers(HttpMethod.POST,"/login").permitAll())
            .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
            .addFilter(new JwtAuthenticationFilter(authenticationManager(authConfiguration)))
            .addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();}



}
