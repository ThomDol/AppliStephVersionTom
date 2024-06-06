package org.gestion_patient.service.impl;

import lombok.AllArgsConstructor;
import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.AntecedentAdulteEnfant;
import org.gestion_patient.entity.Patient;
import org.gestion_patient.entityDto.AntecedentAdulteEnfantDto;
import org.gestion_patient.exception.ResourceNotFoundException;
import org.gestion_patient.mapper.AntecedentAdulteEnfantMapper;
import org.gestion_patient.repository.AntecedentAdulteEnfantRepository;
import org.gestion_patient.repository.PatientRepository;
import org.gestion_patient.service.AntecedentAdulteEnfantService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AntecedentAdulteEnfantServiceImpl implements AntecedentAdulteEnfantService {
    private AntecedentAdulteEnfantRepository antecedentAdulteEnfantRepository;
    private PatientRepository patientRepository;

    @Override
    public AntecedentAdulteEnfantDto create(AntecedentAdulteEnfantDto antecedentAdulteEnfantDto, int idPatient) throws Exception {
        Patient patient = patientRepository.findById(idPatient).orElseThrow(()->new ResourceNotFoundException("Patient with id"+idPatient+" doesn't exist"));
        AntecedentAdulteEnfant antecedentAdulteEnfant = AntecedentAdulteEnfantMapper.mapToAntecedentAdulteEnfant(antecedentAdulteEnfantDto,patient);
        return AntecedentAdulteEnfantMapper.mapToAntecedentAdulteEnfantDto(antecedentAdulteEnfantRepository.save(antecedentAdulteEnfant));
    }

    @Override
    public AntecedentAdulteEnfantDto update(int idToUpdate, AntecedentAdulteEnfantDto antecedentAdulteEnfantDtoUpdated) throws Exception {
        AntecedentAdulteEnfant antecedentAdulteEnfantToUpdate = antecedentAdulteEnfantRepository.findById(idToUpdate).orElseThrow(()->new ResourceNotFoundException("AntecedentAdulteEnfant with id"+idToUpdate+" doesn't exist"));
        antecedentAdulteEnfantToUpdate.setDateUpdate(antecedentAdulteEnfantToUpdate.getDateUpdate());
        if(antecedentAdulteEnfantDtoUpdated.getGrossesse()!=null){antecedentAdulteEnfantToUpdate.setGrossesse(antecedentAdulteEnfantDtoUpdated.getGrossesse());}
        if(antecedentAdulteEnfantDtoUpdated.getFumeur()!=null){antecedentAdulteEnfantToUpdate.setFumeur(antecedentAdulteEnfantDtoUpdated.getFumeur());}
        if(antecedentAdulteEnfantDtoUpdated.getAllergie()!=null){antecedentAdulteEnfantToUpdate.setAllergie(antecedentAdulteEnfantDtoUpdated.getAllergie());}
        if(antecedentAdulteEnfantDtoUpdated.getTraitement()!=null){antecedentAdulteEnfantToUpdate.setTraitement(antecedentAdulteEnfantDtoUpdated.getTraitement());}
        if(antecedentAdulteEnfantDtoUpdated.getAntTraumatique()!=null){antecedentAdulteEnfantToUpdate.setAntTraumatique(Crypto.cryptService(antecedentAdulteEnfantDtoUpdated.getAntTraumatique()));}
        if(antecedentAdulteEnfantDtoUpdated.getAntChirurgicaux()!=null){antecedentAdulteEnfantToUpdate.setAntChirurgicaux(Crypto.cryptService(antecedentAdulteEnfantDtoUpdated.getAntChirurgicaux()));}
        if(antecedentAdulteEnfantDtoUpdated.getAntFamilliaux()!=null){antecedentAdulteEnfantToUpdate.setAntFamilliaux(Crypto.cryptService(antecedentAdulteEnfantDtoUpdated.getAntFamilliaux()));}
        if(antecedentAdulteEnfantDtoUpdated.getAntOrl()!=null){antecedentAdulteEnfantToUpdate.setAntOrl(Crypto.cryptService(antecedentAdulteEnfantDtoUpdated.getAntOrl()));}
        if(antecedentAdulteEnfantDtoUpdated.getAntVisceral()!=null){antecedentAdulteEnfantToUpdate.setAntVisceral(Crypto.cryptService(antecedentAdulteEnfantDtoUpdated.getAntVisceral()));}
        if(antecedentAdulteEnfantDtoUpdated.getAntCardioPulmonaire()!=null){antecedentAdulteEnfantToUpdate.setAntCardioPulmonaire(Crypto.cryptService(antecedentAdulteEnfantDtoUpdated.getAntCardioPulmonaire()));}
        if(antecedentAdulteEnfantDtoUpdated.getAntUroGynecaux()!=null){antecedentAdulteEnfantToUpdate.setAntUroGynecaux(Crypto.cryptService(antecedentAdulteEnfantDtoUpdated.getAntUroGynecaux()));}
        if(antecedentAdulteEnfantDtoUpdated.getAntPsy()!=null){antecedentAdulteEnfantToUpdate.setAntPsy(Crypto.cryptService(antecedentAdulteEnfantDtoUpdated.getAntPsy()));}
        if(antecedentAdulteEnfantDtoUpdated.getAntNotesDiverses()!=null){antecedentAdulteEnfantToUpdate.setAntNotesDiverses(Crypto.cryptService(antecedentAdulteEnfantDtoUpdated.getAntNotesDiverses()));}

        AntecedentAdulteEnfant antecedentAdulteEnfantUpdated = antecedentAdulteEnfantRepository.save(antecedentAdulteEnfantToUpdate);
        return AntecedentAdulteEnfantMapper.mapToAntecedentAdulteEnfantDto(antecedentAdulteEnfantUpdated);
    }

    @Override
    public AntecedentAdulteEnfantDto getByidPatient(int id) throws Exception {
        AntecedentAdulteEnfant antecedentAdulteEnfant = antecedentAdulteEnfantRepository.findByPatientIdPatient(id);
        if(antecedentAdulteEnfant!=null){
            return AntecedentAdulteEnfantMapper.mapToAntecedentAdulteEnfantDto(antecedentAdulteEnfant);
        }
        else{return null;}
    }
}
