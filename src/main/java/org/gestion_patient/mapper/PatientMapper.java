package org.gestion_patient.mapper;

import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.*;
import org.gestion_patient.entityDto.*;

import java.util.List;
import java.util.stream.Collectors;

public class PatientMapper {
    public static PatientDto mapToPatientDto (Patient patient) throws Exception {
        List<RendezvousDto> rendezvousDtos = patient.getRendezvousList().stream()
                .map(rendezvousDto-> {
                    try {
                        return RendezvousMapper.mapToRendezvousDto(rendezvousDto);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).toList();

        List<PhysiqueDto> physiqueDtos = patient.getPhysiqueList().stream()
                .map(PhysiqueMapper::mapToPhysiqueDto).toList();

        List<SportDto> sportDtos = patient.getSportList().stream()
                .map(SportMapper::mapToSportDto).toList();

        List<AntecedentssanteDto> antecedentList = patient.getAntecedentssantesList().stream()
                .map(antecedentDto-> {
                    try {
                        return AntecedentsanteMapper.mapToAntecedentssanteDto(antecedentDto);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).toList();

        List<AccouchementDto> accouchementList = patient.getAccouchementList().stream()
                .map(AccouchementMapper::mapToAccouchementDto).toList();


        return new PatientDto(
                patient.getIdPatient(),
                Crypto.decryptService(patient.getDateNaissance()),
                displayStringDecrypt(patient.getTel()),
                patient.getVille()==null?null:patient.getVille().getNomVille(),
                patient.getVille()==null?null:patient.getVille().getCodePostal(),
                patient.getGenre().getNomGenre(),
                patient.getProfession()==null?null:patient.getProfession().getLibelleProfession(),
                patient.getTypePatient().getNomTypePatient(),
                patient.getMedecinTraitant()==null?null: Crypto.decryptService(patient.getMedecinTraitant().getNomMedecinTraitant()),
                patient.getMedecinTraitant()==null?null:Crypto.decryptService(patient.getMedecinTraitant().getPrenomMedecinTraitant()),
                Crypto.decryptService(patient.getIdentite().getNom()),
                Crypto.decryptService(patient.getIdentite().getPrenom()),
                Crypto.decryptService(patient.getIdentite().getEmail()),
                rendezvousDtos,
                antecedentList,
                accouchementList,
                physiqueDtos,
                sportDtos
        );
    }
    public static Patient mapToPatient(PatientDto patientDto, Lieu lieu, Genre genre , Profession profession , TypePatient typePatient , Medecintraitant medecintraitant , Personne personne, Praticienconnecte praticienconnecte) throws Exception {
        Patient patient=new Patient();
        patient.setIdPatient(patientDto.getIdPatient());
        patient.setDateNaissance(Crypto.cryptService(patientDto.getDateNaissance()));
        patient.setTel(displayStringEncrypt(patientDto.getTel()));
        if(lieu!=null){patient.setVille(lieu);}
        patient.setGenre(genre);
        if(profession!=null){patient.setProfession(profession);}
        patient.setTypePatient(typePatient);
        if(medecintraitant!=null){patient.setMedecinTraitant(medecintraitant);}
        patient.setIdentite(personne);
        patient.setPraticien(praticienconnecte);

        if (patientDto.getRendezvousList() != null) {
            List<Rendezvous> rendezvousList = patientDto.getRendezvousList().stream()
                    .map(rendezvousDto-> {
                        try {
                            return RendezvousMapper.maptoRendezvous(rendezvousDto,patient);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }).toList();
        patient.setRendezvousList(rendezvousList);}

        if (patientDto.getPhysiqueList() != null) {
            List<Physique> physiqueList = patientDto.getPhysiqueList().stream()
                    .map(PhysiqueMapper::mapToPhysique).toList();
            patient.setPhysiqueList(physiqueList);
        }

        if (patientDto.getSportList() != null) {
            List<Sport> sportList = patientDto.getSportList().stream()
                    .map(SportMapper::mapToSport).toList();
            patient.setSportList(sportList);
        }

        if (patientDto.getAntecedentList() != null) {
            List<Antecedentssante> antecedentList = patientDto.getAntecedentList().stream()
                    .map(antecedentDto-> {
                        try {
                            return AntecedentsanteMapper.mapToAntecedentssante(antecedentDto,patient);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }).toList();
            patient.setAntecedentssantesList(antecedentList);
        }

        if (patientDto.getAccouchementList() != null) {
            List<Accouchement> accouchementList = patientDto.getAccouchementList().stream()
                    .map(accouchementDto->AccouchementMapper.mapToAccouchement(accouchementDto,patient)).toList();
            patient.setAccouchementList(accouchementList);
        }



        return patient;}


    public static String displayStringEncrypt (String elem) throws Exception {return elem!=null?Crypto.cryptService(elem):null;}
    public static String displayStringDecrypt (String elem) throws Exception {return elem!=null?Crypto.decryptService(elem):null;}

}






