package org.gestion_patient.mapper;

import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.*;
import org.gestion_patient.entityDto.PatientDto;
import org.gestion_patient.entityDto.PraticienconnecteDto;
import org.gestion_patient.entityDto.SportDto;

import java.util.List;

public class PraticienConnecteMapper {
    public static PraticienconnecteDto mapToPraticienConnecteDto (Praticienconnecte praticienConnecte) throws Exception {

                List< PatientDto > patientDtos = praticienConnecte.getPatientList().stream()
                        .map(patientDto-> {
                            try {
                                return PatientMapper.mapToPatientDto(patientDto);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }).toList();
                return new PraticienconnecteDto(
                praticienConnecte.getIdPraticien(),
                praticienConnecte.getPassword(),
                praticienConnecte.getRole().getNomRole(),
                praticienConnecte.getVille().getNomVille(),
                praticienConnecte.getVille().getCodePostal(),
                Crypto.decryptService(praticienConnecte.getInfosProfessionnelles().getNumAdeli()),
                Crypto.decryptService(praticienConnecte.getInfosProfessionnelles().getNumSiret()),
                Crypto.decryptService(praticienConnecte.getIdentite().getNom()),
                Crypto.decryptService(praticienConnecte.getIdentite().getPrenom()),
                Crypto.decryptService(praticienConnecte.getIdentite().getEmail()),
                patientDtos
        );
    }

    public static Praticienconnecte mapToPraticienConnecte (PraticienconnecteDto praticienConnecteDto, Role role, Lieu lieu, Infosprofessionnelles infosprofessionnelles, Personne personne) throws Exception {
        Praticienconnecte praticienconnecte = new Praticienconnecte();
        praticienconnecte.setIdPraticien(praticienConnecteDto.getIdPraticien());
                praticienconnecte.setPassword(praticienConnecteDto.getPassword());
                praticienconnecte.setRole(role);
                praticienconnecte.setVille(lieu);
                praticienconnecte.setInfosProfessionnelles(infosprofessionnelles);
                praticienconnecte.setIdentite(personne);
        return praticienconnecte;
    }


}
