package org.gestion_patient.mapper;

import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.*;
import org.gestion_patient.entityDto.*;

import java.util.List;


public class PatientMapper {
    public static PatientDto mapToPatientDto (Patient patient) throws Exception {


        return new PatientDto(
                patient.getIdPatient(),
                Crypto.decryptService(patient.getDateNaissance()),
                DataUtil.displayStringDecrypt(patient.getTel()),
                patient.getVille()==null?null:patient.getVille().getNomVille(),
                patient.getVille()==null?null:patient.getVille().getCodePostal(),
                patient.getGenre().getNomGenre(),
                patient.getProfession()==null?null:patient.getProfession().getLibelleProfession(),
                patient.getTypePatient().getNomTypePatient(),
                patient.getMedecinTraitant()==null?null: Crypto.decryptService(patient.getMedecinTraitant().getIdentiteDoc().getNom()),
                patient.getMedecinTraitant()==null?null:Crypto.decryptService(patient.getMedecinTraitant().getIdentiteDoc().getPrenom()),
                patient.getMedecinTraitant()==null?null:patient.getMedecinTraitant().getLieu().getNomVille(),
                Crypto.decryptService(patient.getIdentite().getNom()),
                Crypto.decryptService(patient.getIdentite().getPrenom()),
                patient.getEmail()==null?null:Crypto.decryptService(patient.getEmail())

        );
    }

    public static Patient mapToPatient(PatientDto patientDto, Lieu lieu, Genre genre , Profession profession , TypePatient typePatient , Medecintraitant medecintraitant , Personne personne, Praticienconnecte praticienconnecte) throws Exception {
        Patient patient=new Patient();
        patient.setIdPatient(patientDto.getIdPatient());
        patient.setDateNaissance(Crypto.cryptService(patientDto.getDateNaissance()));
        patient.setTel(DataUtil.displayStringEncrypt(patientDto.getTel()));
        if(lieu!=null){patient.setVille(lieu);}
        patient.setGenre(genre);
        if(profession!=null){patient.setProfession(profession);}
        patient.setTypePatient(typePatient);
        if(medecintraitant!=null){patient.setMedecinTraitant(medecintraitant);}
        patient.setIdentite(personne);
        System.out.println(patientDto.getEmail());
        if(patientDto.getEmail()!=null){patient.setEmail(Crypto.cryptService(patientDto.getEmail()));}
        patient.setPraticien(praticienconnecte);


        return patient;}



}