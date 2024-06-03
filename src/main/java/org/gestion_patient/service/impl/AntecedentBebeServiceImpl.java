package org.gestion_patient.service.impl;


import lombok.AllArgsConstructor;
import org.gestion_patient.entity.AntecedentBebe;
import org.gestion_patient.entity.Patient;
import org.gestion_patient.entityDto.AntecedentBebeDto;
import org.gestion_patient.exception.ResourceNotFoundException;
import org.gestion_patient.exception.RessourceAlreadyexistsException;
import org.gestion_patient.mapper.AntecedentBebeMapper;
import org.gestion_patient.repository.AntecedentBebeRepository;
import org.gestion_patient.repository.PatientRepository;
import org.gestion_patient.service.AntecedentBebeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AntecedentBebeServiceImpl implements AntecedentBebeService {
    private AntecedentBebeRepository antecedentBebeRepository;
    private PatientRepository patientRepository;

    @Override
    public AntecedentBebeDto create(AntecedentBebeDto antecedentBebeDto, int idPatient) {
        Patient patient = patientRepository.findById(idPatient).orElseThrow(()->new ResourceNotFoundException("Patient with id"+idPatient+" doesn't exist"));
        List<AntecedentBebe> antecedentBebeList = antecedentBebeRepository.findAll();
        if(antecedentBebeList!=null){throw  new RessourceAlreadyexistsException("Un seul antecedent possible");}
        AntecedentBebe antecedentBebe = AntecedentBebeMapper.mapToAntecedentBebe(antecedentBebeDto,patient);
        return AntecedentBebeMapper.mapToAntecedentssanteBebeDto(antecedentBebeRepository.save(antecedentBebe));
    }

    @Override
    public AntecedentBebeDto update(int idToUpdate, AntecedentBebeDto antecedentBebeDtoUpdated) {
        AntecedentBebe antecedentBebeToUpdate = antecedentBebeRepository.findById(idToUpdate).orElseThrow(()->new ResourceNotFoundException("AntecedentBebe with id"+idToUpdate+" doesn't exist"));
        if(antecedentBebeDtoUpdated.getMaternite()!=null){antecedentBebeToUpdate.setMaternite(antecedentBebeDtoUpdated.getMaternite());}
        if(antecedentBebeDtoUpdated.getPerimetreCranien()!=null){antecedentBebeToUpdate.setPerimetreCranien(antecedentBebeDtoUpdated.getPerimetreCranien());}
        if(antecedentBebeDtoUpdated.getApgar()!=null){antecedentBebeToUpdate.setApgar(antecedentBebeDtoUpdated.getApgar());}
        if(antecedentBebeDtoUpdated.getDepassementDeTerme()!=null){antecedentBebeToUpdate.setDepassementDeTerme(antecedentBebeDtoUpdated.getDepassementDeTerme());}
        if(antecedentBebeDtoUpdated.getPrematurite()!=null){antecedentBebeToUpdate.setPrematurite(antecedentBebeDtoUpdated.getPrematurite());}
        if(antecedentBebeDtoUpdated.getDeformationDuCrane()!=null){antecedentBebeToUpdate.setDeformationDuCrane(antecedentBebeDtoUpdated.getDeformationDuCrane());}
        if(antecedentBebeDtoUpdated.getBosseSeroSanguine()!=null){antecedentBebeToUpdate.setBosseSeroSanguine(antecedentBebeDtoUpdated.getBosseSeroSanguine());}
        if(antecedentBebeDtoUpdated.getCephalhematome()!=null){antecedentBebeToUpdate.setCephalhematome(antecedentBebeDtoUpdated.getCephalhematome());}
        if(antecedentBebeDtoUpdated.getParalysieObstetricaleDuPlexusBrachial()!=null){antecedentBebeToUpdate.setParalysieObstetricaleDuPlexusBrachial(antecedentBebeDtoUpdated.getParalysieObstetricaleDuPlexusBrachial());}
        if(antecedentBebeDtoUpdated.getParalysieFaciale()!=null){antecedentBebeToUpdate.setParalysieFaciale(antecedentBebeDtoUpdated.getParalysieFaciale());}
        if(antecedentBebeDtoUpdated.getFractureClavicule()!=null){antecedentBebeToUpdate.setFractureClavicule(antecedentBebeDtoUpdated.getFractureClavicule());}
        if(antecedentBebeDtoUpdated.getDysplasieHanche()!=null){antecedentBebeToUpdate.setDysplasieHanche(antecedentBebeDtoUpdated.getDysplasieHanche());}
        if(antecedentBebeDtoUpdated.getPlagiocephalie()!=null){antecedentBebeToUpdate.setPlagiocephalie(antecedentBebeDtoUpdated.getPlagiocephalie());}
        if(antecedentBebeDtoUpdated.getTorticolis()!=null){antecedentBebeToUpdate.setTorticolis(antecedentBebeDtoUpdated.getTorticolis());}
        if(antecedentBebeDtoUpdated.getRefluxGastroOesophagien()!=null){antecedentBebeToUpdate.setRefluxGastroOesophagien(antecedentBebeDtoUpdated.getRefluxGastroOesophagien());}
        if(antecedentBebeDtoUpdated.getColiques()!=null){antecedentBebeToUpdate.setColiques(antecedentBebeDtoUpdated.getColiques());}
        if(antecedentBebeDtoUpdated.getAllaitementMaternelle()!=null){antecedentBebeToUpdate.setAllaitementMaternelle(antecedentBebeDtoUpdated.getAllaitementMaternelle());}
        if(antecedentBebeDtoUpdated.getEfficaciteSuccion()!=null){antecedentBebeToUpdate.setEfficaciteSuccion(antecedentBebeDtoUpdated.getEfficaciteSuccion());}
        if(antecedentBebeDtoUpdated.getSucagePouce()!=null){antecedentBebeToUpdate.setSucagePouce(antecedentBebeDtoUpdated.getSucagePouce());}
        if(antecedentBebeDtoUpdated.getTetine()!=null){antecedentBebeToUpdate.setTetine(antecedentBebeDtoUpdated.getTetine());}
        if(antecedentBebeDtoUpdated.getTypeRespiration()!=null){antecedentBebeToUpdate.setTypeRespiration(antecedentBebeDtoUpdated.getTypeRespiration());}
        if(antecedentBebeDtoUpdated.getPresenceBruitsArticulaires()!=null){antecedentBebeToUpdate.setPresenceBruitsArticulaires(antecedentBebeDtoUpdated.getPresenceBruitsArticulaires());}
        if(antecedentBebeDtoUpdated.getTics()!=null){antecedentBebeToUpdate.setTics(antecedentBebeDtoUpdated.getTics());}

        AntecedentBebe antecedentBebeUpdated = antecedentBebeRepository.save(antecedentBebeToUpdate);
        return AntecedentBebeMapper.mapToAntecedentssanteBebeDto(antecedentBebeUpdated);
    }

    @Override
    public AntecedentBebeDto getByidPatient(int id) {
        AntecedentBebe antecedentBebe = antecedentBebeRepository.findByPatientIdPatient(id);
        if(antecedentBebe!=null){
            return AntecedentBebeMapper.mapToAntecedentssanteBebeDto(antecedentBebe);
        }
        else{return null;}
    }
}
