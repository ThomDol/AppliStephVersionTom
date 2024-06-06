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
        //Verification si Patient existe déjà par nom-prenom-date naissance ou email avec ce praticien (Cryptage des données Dto avant comparasion, car données cryptées dans la base)
        Patient patientToCreate = patientRepository.findByIdentiteNomAndIdentitePrenomAndDateNaissanceAndPraticienIdPraticien(Crypto.cryptService(patientDto.getNomPatient()),Crypto.cryptService(patientDto.getPrenomPatient()),Crypto.cryptService(patientDto.getDateNaissance()),idPraticienConnecte);
        Patient patientToCreateEmail = patientRepository.findByIdentiteEmailAndPraticienIdPraticien(Crypto.cryptService(patientDto.getEmail()),idPraticienConnecte);
        if(patientToCreate!=null || patientToCreateEmail!=null){throw new RessourceAlreadyexistsException("Patient already exists fot this praticien with nom and birth date, or by email");}
        else{
            //Verification si identité de la personne déjà enregistrée, si oui l'utilise. Sinon creation (Cryptage des données Dto avant comparasion, car données cryptées dans la base)
            Personne personneToSave = personneRepository.findByNomAndPrenomAndEmail(Crypto.cryptService(patientDto.getNomPatient()),Crypto.cryptService(patientDto.getPrenomPatient()),Crypto.cryptService(patientDto.getEmail()));
            if (personneToSave==null){
                personneToSave = new Personne();
                personneToSave.setNom(Crypto.cryptService(patientDto.getNomPatient()));
                personneToSave.setPrenom(Crypto.cryptService(patientDto.getPrenomPatient()));
                personneToSave.setEmail(Crypto.cryptService(patientDto.getEmail()));
                personneRepository.save(personneToSave);}
            //genre, typePatient, seront récupérés dans le front
            Genre genre = genreRepository.findByNomGenre(patientDto.getNomGenre());
            TypePatient typePatient = typePatientRepository.findTypePatientByNomTypePatient(patientDto.getNomTypePatient());
            //Lieu recupéré si saisi ds le front via API BAN, et enregistré ds la bdd si pas encore fait
            Lieu lieu;
            if(patientDto.getNomVille()!=null && patientDto.getCodePostal()!=null){
                lieu = lieuRepository.findByNomVilleAndCodePostal(patientDto.getNomVille(),patientDto.getCodePostal());
                if(lieu==null){
                    lieu = new Lieu();
                    lieu.setNomVille(patientDto.getNomVille());
                    lieu.setCodePostal(patientDto.getCodePostal());
                    lieuRepository.save(lieu);}
            }
            else{lieu = null;}
            //Profession recupéré via le front si saisie  et enregistrée en bdd si pas déjà dedans, avant soumission de cette requete
            Profession profession;
            if(patientDto.getNomProfession()!=null){profession = professionRepository.findByLibelleProfession(patientDto.getNomProfession());}
            else{profession =null;}
         //Medecin traitant récupéré ds le front si saisi, et enregistré en bdd si pas déjà dedans, avant soumission de cette requete
            Medecintraitant medecintraitant;
            if(patientDto.getNomMedecinTraitant()!=null && patientDto.getPrenomMedecinTraitant()!=null){medecintraitant = medecintraitantRepository.findByIdentiteDocNomAndIdentiteDocPrenomAndLieuNomVille(Crypto.cryptService(patientDto.getNomMedecinTraitant()), Crypto.cryptService(patientDto.getPrenomMedecinTraitant()),patientDto.getNomVille());}
        else{medecintraitant=null;}
        //Persisitence du patient ds la base de données
        Praticienconnecte praticienconnecte = praticienconnecteRepository.findById(idPraticienConnecte).orElseThrow(() -> new ResourceNotFoundException("Praticien not found with given Id" + idPraticienConnecte));
        Patient patientTSave = PatientMapper.mapToPatient(patientDto, lieu, genre, profession, typePatient, medecintraitant, personneToSave, praticienconnecte);
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
        if(upadtedPatientDto.getNomMedecinTraitant()!=null && upadtedPatientDto.getPrenomMedecinTraitant()!=null && upadtedPatientDto.getVilleMedecinTraitant()!=null){patientToUpdate.setMedecinTraitant(medecintraitantRepository.findByIdentiteDocNomAndIdentiteDocPrenomAndLieuNomVille(Crypto.cryptService(upadtedPatientDto.getNomMedecinTraitant()),Crypto.cryptService(upadtedPatientDto.getPrenomMedecinTraitant()),upadtedPatientDto.getVilleMedecinTraitant()));}

        return PatientMapper.mapToPatientDto(patientRepository.save(patientToUpdate));
    }
}