package org.gestion_patient.service.impl;

import lombok.AllArgsConstructor;
import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.Antecedentssante;
import org.gestion_patient.entity.Patient;
import org.gestion_patient.entityDto.AntecedentssanteDto;
import org.gestion_patient.entityDto.PatientDto;
import org.gestion_patient.exception.ResourceNotFoundException;
import org.gestion_patient.mapper.AntecedentsanteMapper;
import org.gestion_patient.repository.AntecedentssanteRepository;
import org.gestion_patient.repository.PatientRepository;
import org.gestion_patient.service.AntecedentssanteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AntecedentssanteServiceImpl implements AntecedentssanteService {

    private AntecedentssanteRepository antecedentssanteRepository;
    private PatientRepository patientRepository;

    //Cryptage des données sensibles via le mapper
    @Override
    public AntecedentssanteDto create(AntecedentssanteDto antecedentssanteDto,int idPatient) throws Exception {
        Patient patient = patientRepository.findById(idPatient).orElseThrow(()->new ResourceNotFoundException("Patient with id"+idPatient+" doesn't exist"));
        Antecedentssante antecedentssante = AntecedentsanteMapper.mapToAntecedentssante(antecedentssanteDto,patient);
        return AntecedentsanteMapper.mapToAntecedentssanteDto(antecedentssanteRepository.save(antecedentssante));
    }

    //Cryptage des données via la fonction set, pour les données sensibles, car les données sont déjà cryptées, et seules celles qui changent doivent être crypttées. Pas possible d'utiliser le Mapper ds ce but
    @Override
    public AntecedentssanteDto update(int idToUpdate, AntecedentssanteDto antecedentssanteDtoUpdated) throws Exception {
        Antecedentssante antecedentssanteToUpdate = antecedentssanteRepository.findById(idToUpdate).orElseThrow(()->new ResourceNotFoundException("Antecedent with id"+idToUpdate+" doesn't exist"));
        antecedentssanteToUpdate.setDateUpdate(antecedentssanteDtoUpdated.getDateUpdate());
        if(antecedentssanteDtoUpdated.getAntTraumatique()!=null){antecedentssanteToUpdate.setAntTraumatique(Crypto.cryptService(antecedentssanteDtoUpdated.getAntTraumatique()));}
        if(antecedentssanteDtoUpdated.getAntChirurgicaux()!=null){antecedentssanteToUpdate.setAntChirurgicaux(Crypto.cryptService(antecedentssanteDtoUpdated.getAntChirurgicaux()));}
        if(antecedentssanteDtoUpdated.getAntFamilliaux()!=null){antecedentssanteToUpdate.setAntFamilliaux(Crypto.cryptService(antecedentssanteDtoUpdated.getAntFamilliaux()));}
        if(antecedentssanteDtoUpdated.getAntOrl()!=null){antecedentssanteToUpdate.setAntOrl(Crypto.cryptService(antecedentssanteDtoUpdated.getAntOrl()));}
        if(antecedentssanteDtoUpdated.getAntVisceral()!=null){antecedentssanteToUpdate.setAntVisceral(Crypto.cryptService(antecedentssanteDtoUpdated.getAntVisceral()));}
        if(antecedentssanteDtoUpdated.getAntCardioPulmonaire()!=null){antecedentssanteToUpdate.setAntCardioPulmonaire(Crypto.cryptService(antecedentssanteDtoUpdated.getAntCardioPulmonaire()));}
        if(antecedentssanteDtoUpdated.getAntUroGynecaux()!=null){antecedentssanteToUpdate.setAntUroGynecaux(Crypto.cryptService(antecedentssanteDtoUpdated.getAntUroGynecaux()));}
        if(antecedentssanteDtoUpdated.getAntPsy()!=null){antecedentssanteToUpdate.setAntPsy(Crypto.cryptService(antecedentssanteDtoUpdated.getAntPsy()));}
        if(antecedentssanteDtoUpdated.getAntNotesDiverses()!=null){antecedentssanteToUpdate.setAntNotesDiverses(Crypto.cryptService(antecedentssanteDtoUpdated.getAntNotesDiverses()));}
        if(antecedentssanteDtoUpdated.getMaternite()!=null){antecedentssanteToUpdate.setMaternite(antecedentssanteDtoUpdated.getMaternite());}
        if(antecedentssanteDtoUpdated.getPoidsDeNaissance()!=null){antecedentssanteToUpdate.setPoidsDeNaissance(antecedentssanteDtoUpdated.getPoidsDeNaissance());}
        if(antecedentssanteDtoUpdated.getTailleDeNaissance()!=null){antecedentssanteToUpdate.setTailleDeNaissance(antecedentssanteDtoUpdated.getTailleDeNaissance());}
        if(antecedentssanteDtoUpdated.getPerimetreCranien()!=null){antecedentssanteToUpdate.setPerimetreCranien(antecedentssanteDtoUpdated.getPerimetreCranien());}
        if(antecedentssanteDtoUpdated.getApgar()!=null){antecedentssanteToUpdate.setApgar(antecedentssanteDtoUpdated.getApgar());}
        if(antecedentssanteDtoUpdated.getDepassementDeTerme()!=null){antecedentssanteToUpdate.setDepassementDeTerme(antecedentssanteDtoUpdated.getDepassementDeTerme());}
        if(antecedentssanteDtoUpdated.getDeformationDuCrane()!=null){antecedentssanteToUpdate.setDeformationDuCrane(antecedentssanteDtoUpdated.getDeformationDuCrane());}
        if(antecedentssanteDtoUpdated.getBosseSeroSanguine()!=null){antecedentssanteToUpdate.setBosseSeroSanguine(antecedentssanteDtoUpdated.getBosseSeroSanguine());}
        if(antecedentssanteDtoUpdated.getCephalhematome()!=null){antecedentssanteToUpdate.setCephalhematome(antecedentssanteDtoUpdated.getCephalhematome());}
        if(antecedentssanteDtoUpdated.getParalysieObstetricaleDuPlexusBrachial()!=null){antecedentssanteToUpdate.setParalysieObstetricaleDuPlexusBrachial(antecedentssanteDtoUpdated.getParalysieObstetricaleDuPlexusBrachial());}
        if(antecedentssanteDtoUpdated.getParalysieFaciale()!=null){antecedentssanteToUpdate.setParalysieFaciale(antecedentssanteDtoUpdated.getParalysieFaciale());}
        if(antecedentssanteDtoUpdated.getFractureClavicule()!=null){antecedentssanteToUpdate.setFractureClavicule(antecedentssanteDtoUpdated.getFractureClavicule());}
        if(antecedentssanteDtoUpdated.getApgar()!=null){antecedentssanteToUpdate.setApgar(antecedentssanteDtoUpdated.getApgar());}
        if(antecedentssanteDtoUpdated.getDysplasieHanche()!=null){antecedentssanteToUpdate.setDysplasieHanche(antecedentssanteDtoUpdated.getDysplasieHanche());}
        if(antecedentssanteDtoUpdated.getPlagiocephalie()!=null){antecedentssanteToUpdate.setPlagiocephalie(antecedentssanteDtoUpdated.getPlagiocephalie());}
        if(antecedentssanteDtoUpdated.getTorticolis()!=null){antecedentssanteToUpdate.setTorticolis(antecedentssanteDtoUpdated.getTorticolis());}
        if(antecedentssanteDtoUpdated.getRefluxGastroOesophagien()!=null){antecedentssanteToUpdate.setRefluxGastroOesophagien(antecedentssanteDtoUpdated.getRefluxGastroOesophagien());}
        if(antecedentssanteDtoUpdated.getColiques()!=null){antecedentssanteToUpdate.setColiques(antecedentssanteDtoUpdated.getColiques());}
        if(antecedentssanteDtoUpdated.getEfficaciteSuccion()!=null){antecedentssanteToUpdate.setEfficaciteSuccion(antecedentssanteDtoUpdated.getEfficaciteSuccion());}
        if(antecedentssanteDtoUpdated.getParalysieFaciale()!=null){antecedentssanteToUpdate.setParalysieFaciale(antecedentssanteDtoUpdated.getParalysieFaciale());}
        if(antecedentssanteDtoUpdated.getParalysieFaciale()!=null){antecedentssanteToUpdate.setParalysieFaciale(antecedentssanteDtoUpdated.getParalysieFaciale());}
        if(antecedentssanteDtoUpdated.getParalysieFaciale()!=null){antecedentssanteToUpdate.setParalysieFaciale(antecedentssanteDtoUpdated.getParalysieFaciale());}
        if(antecedentssanteDtoUpdated.getParalysieFaciale()!=null){antecedentssanteToUpdate.setParalysieFaciale(antecedentssanteDtoUpdated.getParalysieFaciale());}
        if(antecedentssanteDtoUpdated.getSucagePouce()!=null){antecedentssanteToUpdate.setSucagePouce(antecedentssanteDtoUpdated.getSucagePouce());}
        if(antecedentssanteDtoUpdated.getTetine()!=null){antecedentssanteToUpdate.setTetine(antecedentssanteDtoUpdated.getTetine());}
        if(antecedentssanteDtoUpdated.getPresenceBruitsArticulaires()!=null){antecedentssanteToUpdate.setPresenceBruitsArticulaires(antecedentssanteDtoUpdated.getPresenceBruitsArticulaires());}
        if(antecedentssanteDtoUpdated.getTics()!=null){antecedentssanteToUpdate.setTics(antecedentssanteDtoUpdated.getTics());}
        if(antecedentssanteDtoUpdated.getGestation()!=null){antecedentssanteToUpdate.setGestation(antecedentssanteDtoUpdated.getGestation());}
        if(antecedentssanteDtoUpdated.getGrossesseMultiple()!=null){antecedentssanteToUpdate.setGrossesseMultiple(antecedentssanteDtoUpdated.getGrossesseMultiple());}
        if(antecedentssanteDtoUpdated.getDouleursPendantGrossesse()!=null){antecedentssanteToUpdate.setDouleursPendantGrossesse(antecedentssanteDtoUpdated.getDouleursPendantGrossesse());}
        if(antecedentssanteDtoUpdated.getEtatPsychoEmotionnel()!=null){antecedentssanteToUpdate.setEtatPsychoEmotionnel(antecedentssanteDtoUpdated.getEtatPsychoEmotionnel());}
        if(antecedentssanteDtoUpdated.getDisproportionFoetoPelvienne()!=null){antecedentssanteToUpdate.setDisproportionFoetoPelvienne(antecedentssanteDtoUpdated.getDisproportionFoetoPelvienne());}
        if(antecedentssanteDtoUpdated.getTraitementLieGrossesse()!=null){antecedentssanteToUpdate.setTraitementLieGrossesse(antecedentssanteDtoUpdated.getTraitementLieGrossesse());}
        if(antecedentssanteDtoUpdated.getMouvementsBebe()!=null){antecedentssanteToUpdate.setMouvementsBebe(antecedentssanteDtoUpdated.getMouvementsBebe());}
        if(antecedentssanteDtoUpdated.getCesariennePrevue()!=null){antecedentssanteToUpdate.setCesariennePrevue(antecedentssanteDtoUpdated.getCesariennePrevue());}
        if(antecedentssanteDtoUpdated.getProjetPeridurale()!=null){antecedentssanteToUpdate.setProjetPeridurale(antecedentssanteDtoUpdated.getProjetPeridurale());}
        if(antecedentssanteDtoUpdated.getProjetAllaitement()!=null){antecedentssanteToUpdate.setProjetAllaitement(antecedentssanteDtoUpdated.getProjetAllaitement());}
        if(antecedentssanteDtoUpdated.getTics()!=null){antecedentssanteToUpdate.setTics(antecedentssanteDtoUpdated.getTics());}
        if(antecedentssanteDtoUpdated.getNausees()!=null){antecedentssanteToUpdate.setNausees(antecedentssanteDtoUpdated.getNausees());}
        if(antecedentssanteDtoUpdated.getConstipation()!=null){antecedentssanteToUpdate.setConstipation(antecedentssanteDtoUpdated.getConstipation());}
        if(antecedentssanteDtoUpdated.getDiarrhees()!=null){antecedentssanteToUpdate.setDiarrhees(antecedentssanteDtoUpdated.getDiarrhees());}
        if(antecedentssanteDtoUpdated.getAigreursEstomac()!=null){antecedentssanteToUpdate.setAigreursEstomac(antecedentssanteDtoUpdated.getAigreursEstomac());}
        if(antecedentssanteDtoUpdated.getOedemesMembresInferieurs()!=null){antecedentssanteToUpdate.setOedemesMembresInferieurs(antecedentssanteDtoUpdated.getOedemesMembresInferieurs());}
        if(antecedentssanteDtoUpdated.getPesanteurPelvienne()!=null){antecedentssanteToUpdate.setPesanteurPelvienne(antecedentssanteDtoUpdated.getPesanteurPelvienne());}
        if(antecedentssanteDtoUpdated.getIncontinence()!=null){antecedentssanteToUpdate.setIncontinence(antecedentssanteDtoUpdated.getIncontinence());}
        if(antecedentssanteDtoUpdated.getTensionMammaire()!=null){antecedentssanteToUpdate.setTensionMammaire(antecedentssanteDtoUpdated.getTensionMammaire());}
        if(antecedentssanteDtoUpdated.getMastose()!=null){antecedentssanteToUpdate.setMastose(antecedentssanteDtoUpdated.getMastose());}
        if(antecedentssanteDtoUpdated.getEcoulementsVaginaux()!=null){antecedentssanteToUpdate.setEcoulementsVaginaux(antecedentssanteDtoUpdated.getEcoulementsVaginaux());}
        if(antecedentssanteDtoUpdated.getTailleBebe()!=null){antecedentssanteToUpdate.setTailleBebe(antecedentssanteDtoUpdated.getTailleBebe());}
        if(antecedentssanteDtoUpdated.getPoidsBebe()!=null){antecedentssanteToUpdate.setPoidsBebe(antecedentssanteDtoUpdated.getPoidsBebe());}
        if(antecedentssanteDtoUpdated.getDouleursAbdominales()!=null){antecedentssanteToUpdate.setDouleursAbdominales(antecedentssanteDtoUpdated.getDouleursAbdominales());}
        if(antecedentssanteDtoUpdated.getFievre()!=null){antecedentssanteToUpdate.setFievre(antecedentssanteDtoUpdated.getFievre());}
        if(antecedentssanteDtoUpdated.getAllaitementMaternel()!=null){antecedentssanteToUpdate.setAllaitementMaternel(antecedentssanteDtoUpdated.getAllaitementMaternel());}
        if(antecedentssanteDtoUpdated.getTypeRespiration()!=null){antecedentssanteToUpdate.setTypeRespiration(antecedentssanteDtoUpdated.getTypeRespiration());}



        Antecedentssante antecedentssanteUpdated = antecedentssanteRepository.save(antecedentssanteToUpdate);
        return AntecedentsanteMapper.mapToAntecedentssanteDto(antecedentssanteUpdated);
    }

    @Override
    public AntecedentssanteDto getByidPatient(int id) throws Exception {
        Antecedentssante antecedentssante = antecedentssanteRepository.findByPatientIdPatient(id);
        if(antecedentssante!=null){
            return AntecedentsanteMapper.mapToAntecedentssanteDto(antecedentssante);
        }
        else{return null;}
    }


    }





