package org.gestion_patient.service.impl;

import lombok.AllArgsConstructor;
import org.gestion_patient.entity.Mesurer;
import org.gestion_patient.entity.MesurerId;
import org.gestion_patient.entity.Patient;
import org.gestion_patient.entity.Physique;
import org.gestion_patient.entityDto.MesurerDto;
import org.gestion_patient.entityDto.PhysiqueDto;
import org.gestion_patient.exception.ResourceNotFoundException;
import org.gestion_patient.exception.RessourceAlreadyexistsException;
import org.gestion_patient.mapper.MesurerMapper;
import org.gestion_patient.repository.MesurerRepository;
import org.gestion_patient.repository.PatientRepository;
import org.gestion_patient.repository.PhysiqueRepository;
import org.gestion_patient.service.MesurerService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@AllArgsConstructor
public class MesurerServiceImpl implements MesurerService {
    private MesurerRepository mesurerRepository;
    private PhysiqueRepository physiqueRepository;
    private PatientRepository patientRepository;

    @Override
    public MesurerDto createByPatient(PhysiqueDto physiqueDto, int idPatient) {
        Patient patient = patientRepository.findById(idPatient).orElseThrow(() -> new ResourceNotFoundException("Patient not found with id"+idPatient));
        Physique physique = physiqueRepository.findByDateMesure(physiqueDto.getDateMesure());
        if(physique==null){
            Physique physiqueToSave = new Physique();
            physiqueToSave.setDateMesure(physiqueDto.getDateMesure());
            physiqueToSave.setPoids(physiqueDto.getPoids());
            physiqueToSave.setTaille(physiqueDto.getTaille());
            physiqueToSave.setLunettes(physiqueDto.getLunettes());
            physiqueToSave.setDroitier(physiqueDto.getDroitier());
            physiqueToSave.setDentaire(physiqueDto.getDentaire());
            Physique physiqueSaved = physiqueRepository.save(physiqueToSave);
            MesurerId mesurerId = new MesurerId();
            mesurerId.setIdPatient(idPatient);
            mesurerId.setIdPhysique(physiqueSaved.getIdPhysique());
            Mesurer mesurerToSave = new Mesurer(mesurerId, physiqueSaved, patient);
            return MesurerMapper.mapToMesurerDto(mesurerRepository.save(mesurerToSave));
        }
        else{throw new RessourceAlreadyexistsException("Data already saved at this day");
        }

    }



    @Override
    public List<MesurerDto> getAllMesurersByIdPatient(int idPatient) {
        List<Mesurer> mesurerList =mesurerRepository.findAllByPatientIdPatient(idPatient);
        return  mesurerList.stream().map(MesurerMapper::mapToMesurerDto).toList();
    }

    @Override
    public MesurerDto update(MesurerDto mesurerDto) {
        Mesurer mesurerToUpdate = mesurerRepository.findByPatientIdPatientAndPhysiqueIdPhysique(mesurerDto.getIdPatient(),mesurerDto.getIdPhysique());
        if(mesurerToUpdate!=null){
            Physique physique= physiqueRepository.findById(mesurerDto.getIdPhysique()).orElseThrow(()->new ResourceNotFoundException("No Physique data with id"+mesurerDto.getIdPhysique()));
            physique.setPoids(mesurerDto.getPoids());
            physique.setTaille(mesurerDto.getTaille());
            physique.setLunettes(mesurerDto.getLunettes());
            physique.setDroitier(mesurerDto.getDroitier());
            physique.setDentaire(mesurerDto.getDentaire());
            mesurerToUpdate.setPhysique(physique);
            return MesurerMapper.mapToMesurerDto(mesurerRepository.save(mesurerToUpdate));
        }
        else{throw new ResourceNotFoundException("Mesurer not Found");}
    }

}



