package org.gestion_patient.service.impl;

import lombok.AllArgsConstructor;
import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.*;
import org.gestion_patient.entityDto.PatientDto;
import org.gestion_patient.exception.ResourceNotFoundException;
import org.gestion_patient.exception.RessourceAlreadyexistsException;
import org.gestion_patient.mapper.PatientMapper;
import org.gestion_patient.repository.*;
import org.gestion_patient.service.PatientService;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {
    private PatientRepository patientRepository;
    private LieuRepository lieuRepository;
    private GenreRepository genreRepository;
    private ProfessionRepository professionRepository;
    private TypePatientRepository typePatientRepository;
    private MedecintraitantRepository medecintraitantRepository;
    private PersonneRepository personneRepository;
    private PraticienconnecteRepository praticienconnecteRepository;



    //Creation d'un nouveau Patient
    @Override
    public PatientDto createPatient(PatientDto patientDto,int idPraticienConnecte) throws Exception {
        //Verification si identité de la personne déjà enregistrée, si oui leve une exception. Sinon creation (Cryptage des données Dto avant comparasion, car données cryptées dans la base)
        Personne personneEnteredNomPrenom = personneRepository.findByNomAndPrenom(Crypto.cryptService(patientDto.getNomPatient()),Crypto.cryptService(patientDto.getPrenomPatient()));
        Personne personneEnteredEmail = personneRepository.findByEmail(Crypto.cryptService(patientDto.getEmail()));
        if (personneEnteredNomPrenom!=null || personneEnteredEmail!=null){throw new RessourceAlreadyexistsException("Patient already exists with these information");}
        else{Personne personne= new Personne();
        personne.setNom(Crypto.cryptService(patientDto.getNomPatient()));
        personne.setPrenom(Crypto.cryptService(patientDto.getPrenomPatient()));
        personne.setEmail(Crypto.cryptService(patientDto.getEmail()));
        Personne savedPersonne = personneRepository.save(personne);
        //Lieu , genre, typePatient,profession, et medecinTraitant seront récupérés dans le front et crée si pas encore enregistrés avant soumission de cette requête
        Lieu lieu = lieuRepository.findByNomVilleAndCodePostal(patientDto.getNomVille(),patientDto.getCodePostal());
        Genre genre = genreRepository.findByNomGenre(patientDto.getNomGenre());
        TypePatient typePatient = typePatientRepository.findTypePatientByNomTypePatient(patientDto.getNomTypePatient());
        Profession profession = professionRepository.findByLibelleProfession(patientDto.getNomProfession());
        Medecintraitant medecintraitant = medecintraitantRepository.findByIdentiteDocNomAndIdentiteDocPrenom(Crypto.cryptService(patientDto.getNomMedecinTraitant()), Crypto.cryptService(patientDto.getPrenomMedecinTraitant()));
        //Persisitence du patient ds la base de données
        Praticienconnecte praticienconnecte = praticienconnecteRepository.findById(idPraticienConnecte).orElseThrow(() -> new ResourceNotFoundException("Praticien not found with given Id" + idPraticienConnecte));
        Patient patientTSave = PatientMapper.mapToPatient(patientDto, lieu, genre, profession, typePatient, medecintraitant, savedPersonne, praticienconnecte);
        return PatientMapper.mapToPatientDto(patientRepository.save(patientTSave));}

    }

    @Override
    public List<PatientDto> getAllPatientByPraticien(int idPraticien) {
        Praticienconnecte praticienconnecte= praticienconnecteRepository.findById(idPraticien).orElseThrow(()->new ResourceNotFoundException("Praticien not found with given Id"+idPraticien));
        List<Patient> patients = patientRepository.findAllByPraticien(praticienconnecte);

        return patients.stream().map(patient-> {
            try {
                return PatientMapper.mapToPatientDto(patient);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    @Override
    public PatientDto getById(int id) throws Exception {
        Patient patient = patientRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Patient not found with given Id"+id));
        return PatientMapper.mapToPatientDto(patient);
    }

    @Override
    public void deletePatient(int id) {
     Patient patient = patientRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Patient not found with given Id"+id));
     patientRepository.delete(patient);
    }

    @Override
    public PatientDto updatePatient(int id, PatientDto upadtedPatientDto) throws Exception {
        Patient patientToUpdate = patientRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Patient not found with given Id"+id));
        if(upadtedPatientDto.getDateNaissance()!=null){patientToUpdate.setDateNaissance(Crypto.cryptService(upadtedPatientDto.getDateNaissance()));}
        if(upadtedPatientDto.getTel()!=null){patientToUpdate.setTel(Crypto.cryptService(upadtedPatientDto.getTel()));}
        Personne personneToUpdate = patientToUpdate.getIdentite();
        if(upadtedPatientDto.getNomPatient()!=null){personneToUpdate.setNom(Crypto.cryptService(upadtedPatientDto.getNomPatient()));}
        if(upadtedPatientDto.getEmail()!=null){personneToUpdate.setEmail(Crypto.cryptService(upadtedPatientDto.getEmail()));}
        personneRepository.save(personneToUpdate);
        patientToUpdate.setIdentite(personneToUpdate);
        if(upadtedPatientDto.getCodePostal()!=null && upadtedPatientDto.getNomVille()!=null){
        patientToUpdate.setVille(lieuRepository.findByNomVilleAndCodePostal(upadtedPatientDto.getNomVille(),upadtedPatientDto.getCodePostal()));}
        if(upadtedPatientDto.getNomProfession()!=null){patientToUpdate.setProfession(professionRepository.findByLibelleProfession(upadtedPatientDto.getNomProfession()));}
        if(upadtedPatientDto.getNomMedecinTraitant()!=null && upadtedPatientDto.getPrenomMedecinTraitant()!=null){patientToUpdate.setMedecinTraitant(medecintraitantRepository.findByIdentiteDocNomAndIdentiteDocPrenom(Crypto.cryptService(upadtedPatientDto.getNomMedecinTraitant()),Crypto.cryptService(upadtedPatientDto.getPrenomMedecinTraitant())));}

        return PatientMapper.mapToPatientDto(patientRepository.save(patientToUpdate));
    }
}