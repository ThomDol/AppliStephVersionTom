package org.gestion_patient.security;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.Praticienconnecte;
import org.gestion_patient.entityDto.PraticienconnecteDto;
import org.gestion_patient.service.PraticienConnecteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@AllArgsConstructor
public class PraticienConnecteDetailService implements UserDetailsService {


    private PraticienConnecteService praticienConnecteService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        try {
            //Charger l'utilisateur lié à cet email
            PraticienconnecteDto praticienconnecteDto = praticienConnecteService.loadByEmail(email);
            if (praticienconnecteDto == null) {
                throw new UsernameNotFoundException("User not found with email: " + email);
            }
            String decryptedEmail = praticienconnecteDto.getEmail();
            String password = praticienconnecteDto.getPassword();

            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(praticienconnecteDto.getNomRole()));
            return new User(decryptedEmail, password, authorities);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
