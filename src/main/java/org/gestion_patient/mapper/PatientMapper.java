package org.gestion_patient.mapper;

import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.*;
import org.gestion_patient.entityDto.*;

import java.util.List;


public class PatientMapper {
    public static PatientDto mapToPatientDto (Patient patient) throws Exception {
        List<RendezvousDto> rendezvousList = patient.getRendezvousList().stream()
                .map(rendezvous-> {
                    try {
                        return RendezvousMapper.mapToRendezvousDto(rendezvous);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).toList();

        List<PhysiqueDto> physiqueDtos = patient.getPhysiqueList().stream()
                .map(PhysiqueMapper::mapToPhysiqueDto).toList();

        List<SportDto> sportDtos = patient.getSportList().stream()
                .map(SportMapper::mapToSportDto).toList();

        List<AntecedentAdulteEnfantDto> antecedentAdulteEnfantList = patient.getAntecedentAdulteEnfantList().stream()
                .map(antecedent-> {
                    try {
                        return AntecedentAdulteEnfantMapper.mapToAntecedentAdulteEnfantDto(antecedent);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).toList();

        List<AntecedentsBebeDto> antecedentBebeList = patient.getAntecedentBebeList().stream()
                .map(AntecedentsBebeMapper::mapToAntecedentssanteBebeDto).toList();


        List<GrossesseDto> grossesseList = patient.getGrossesseList().stream()
                .map(GrossesseMapper::mapToGrossesseDto).toList();


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
                patient.getMedecinTraitant()==null?null: Crypto.decryptService(patient.getMedecinTraitant().getIdentiteDoc().getNom()),
                patient.getMedecinTraitant()==null?null:Crypto.decryptService(patient.getMedecinTraitant().getIdentiteDoc().getPrenom()),
                patient.getMedecinTraitant()==null?null:patient.getMedecinTraitant().getLieu().getNomVille(),
                Crypto.decryptService(patient.getIdentite().getNom()),
                Crypto.decryptService(patient.getIdentite().getPrenom()),
                Crypto.decryptService(patient.getIdentite().getEmail()),
                antecedentBebeList,
                antecedentAdulteEnfantList,
                grossesseList,
                accouchementList,
                physiqueDtos,
                sportDtos,
                rendezvousList
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



        if (patientDto.getAntecedentBebeList() != null) {
            List<AntecedentsBebe> antecedentBebeList = patientDto.getAntecedentBebeList().stream()
                    .map(accouchementDto-> AntecedentsBebeMapper.mapToAntecedentBebe(accouchementDto,patient)).toList();
            patient.setAntecedentBebeList(antecedentBebeList);
        }

        if (patientDto.getAntecedentAdulteEnfantList() != null) {
            List<AntecedentAdulteEnfant> antecedentAdulteEnfantList = patientDto.getAntecedentAdulteEnfantList().stream()
                    .map(antecedentDto-> {
                        try {
                            return AntecedentAdulteEnfantMapper.mapToAntecedentAdulteEnfant(antecedentDto,patient);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }).toList();
            patient.setAntecedentAdulteEnfantList(antecedentAdulteEnfantList);
        }



        if (patientDto.getGrossesseList() != null) {
            List<Grossesse> grossesseList = patientDto.getGrossesseList().stream()
                    .map(accouchementDto->GrossesseMapper.mapToGrossesse(accouchementDto,patient)).toList();
            patient.setGrossesseList(grossesseList);
        }


        if (patientDto.getAccouchementList() != null) {
            List<Accouchement> accouchementList = patientDto.getAccouchementList().stream()
                    .map(accouchementDto->AccouchementMapper.mapToAccouchement(accouchementDto,patient)).toList();
            patient.setAccouchementList(accouchementList);
        }

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



        return patient;}


    public static String displayStringEncrypt (String elem) throws Exception {return elem!=null?Crypto.cryptService(elem):null;}
    public static String displayStringDecrypt (String elem) throws Exception {return elem!=null?Crypto.decryptService(elem):null;}

}






