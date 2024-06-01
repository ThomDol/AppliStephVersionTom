package org.gestion_patient.service.impl;

import lombok.AllArgsConstructor;
import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.Medecintraitant;
import org.gestion_patient.entity.Personne;
import org.gestion_patient.entityDto.MedecintraitantDto;
import org.gestion_patient.exception.ResourceNotFoundException;
import org.gestion_patient.exception.RessourceAlreadyexistsException;
import org.gestion_patient.mapper.MedecinTraitantMapper;
import org.gestion_patient.repository.MedecintraitantRepository;
import org.gestion_patient.repository.PersonneRepository;
import org.gestion_patient.service.MedecinTraitantService;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MedecinTraitantImpl implements MedecinTraitantService {
    private final PersonneRepository personneRepository;
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
        Medecintraitant medecintraitantToSave = medecintraitantRepository.findByIdentiteDocNomAndIdentiteDocPrenom(Crypto.cryptService(medecintraitantDto.getNomMedecinTraitant()), Crypto.cryptService(medecintraitantDto.getPrenomMedecinTraitant()));
        if(medecintraitantToSave!=null){
            throw new RessourceAlreadyexistsException("This Medecintraitant alredy exists");
        }
        Personne personne = new Personne();
        personne.setNom(Crypto.cryptService(medecintraitantDto.getNomMedecinTraitant()));
        personne.setPrenom(Crypto.cryptService(medecintraitantDto.getPrenomMedecinTraitant()));
        personne=personneRepository.save(personne);
        Medecintraitant medecintraitant = MedecinTraitantMapper.mapToMedecinTraitant(medecintraitantDto,personne);
       Medecintraitant medecintraitantSaved = medecintraitantRepository.save(medecintraitant);
        return MedecinTraitantMapper.mapToMedecinTraitantDto(medecintraitantSaved);
    }

    @Override
    public MedecintraitantDto updateMedecinTraintant(MedecintraitantDto medecintraitantDto , int id ) throws Exception {
      Medecintraitant medecintraitant =  medecintraitantRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Medecin not found with given id " + id));
      Personne personneToUpdatde =   medecintraitant.getIdentiteDoc();
      personneToUpdatde.setNom(Crypto.cryptService(medecintraitantDto.getNomMedecinTraitant()));
      personneToUpdatde.setPrenom(Crypto.cryptService(medecintraitantDto.getPrenomMedecinTraitant()));
      personneToUpdatde = personneRepository.save(personneToUpdatde);
      medecintraitant.setIdentiteDoc(personneToUpdatde);
      medecintraitant=medecintraitantRepository.save(medecintraitant);
      return MedecinTraitantMapper.mapToMedecinTraitantDto(medecintraitant);

    }
}
