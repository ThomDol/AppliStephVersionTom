package org.gestion_patient.service.impl;

import lombok.AllArgsConstructor;
import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.Medecintraitant;
import org.gestion_patient.entityDto.MedecintraitantDto;
import org.gestion_patient.exception.ResourceNotFoundException;
import org.gestion_patient.exception.RessourceAlreadyexistsException;
import org.gestion_patient.mapper.MedecinTraitantMapper;
import org.gestion_patient.repository.MedecintraitantRepository;
import org.gestion_patient.service.MedecinTraitantService;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MedecinTraitantImpl implements MedecinTraitantService {
    MedecintraitantRepository medecintraitantRepository;

    //Decryptage pour affichage via le Mapper
    @Override
    public List<MedecintraitantDto> getAll() {
        List<Medecintraitant> medecins = medecintraitantRepository.findAll();
        return medecins.stream().map(medecin-> {
            try {
                return MedecinTraitantMapper.mapToMedecinTraitantDto(medecin);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }
    //Decryptage pour affichage via le Mapper
    @Override
    public MedecintraitantDto findMedecintraitantById(int id) throws Exception {
        Medecintraitant medecinTraitant = medecintraitantRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Medecin not found with given id " + id));
        return MedecinTraitantMapper.mapToMedecinTraitantDto(medecinTraitant);
    }

    //Cryptage via le Mapper
    @Override
    public MedecintraitantDto createMedecintraitant(MedecintraitantDto medecintraitantDto) throws Exception {
        Medecintraitant medecintraitantToSave = medecintraitantRepository.findByNomMedecinTraitantAndPrenomMedecinTraitant(medecintraitantDto.getNomMedecinTraitant(), medecintraitantDto.getPrenomMedecinTraitant());
        if(medecintraitantToSave!=null){
            throw new RessourceAlreadyexistsException("This Medecintraitant alredy exists");
        }
        Medecintraitant medecintraitant = MedecinTraitantMapper.mapToMedecinTraitant(medecintraitantDto);
       Medecintraitant medecintraitants = medecintraitantRepository.save(medecintraitant);
        return MedecinTraitantMapper.mapToMedecinTraitantDto(medecintraitants);
    }

    @Override
    public MedecintraitantDto updateMedecinTraintant(MedecintraitantDto medecintraitantDto , int id ) throws Exception {
      Medecintraitant medecintraitant =  medecintraitantRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Medecin not found with given id " + id));
        medecintraitant.setNomMedecinTraitant(Crypto.cryptService(medecintraitantDto.getNomMedecinTraitant()));
        medecintraitant.setPrenomMedecinTraitant(Crypto.cryptService(medecintraitantDto.getPrenomMedecinTraitant()));
        medecintraitantRepository.save(medecintraitant);
        return MedecinTraitantMapper.mapToMedecinTraitantDto(medecintraitant);

    }
}
