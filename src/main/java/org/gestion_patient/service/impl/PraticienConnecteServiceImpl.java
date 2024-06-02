package org.gestion_patient.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.*;
import org.gestion_patient.entityDto.PraticienconnecteDto;
import org.gestion_patient.exception.ResourceNotFoundException;
import org.gestion_patient.exception.RessourceAlreadyexistsException;
import org.gestion_patient.mapper.PraticienConnecteMapper;
import org.gestion_patient.repository.*;
import org.gestion_patient.service.PraticienConnecteService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PraticienConnecteServiceImpl implements PraticienConnecteService {

    private PraticienconnecteRepository praticienconnecteRepository;
    private RoleRepository roleRepository;
    private LieuRepository lieuRepository;
    private InfosprofessionnelleRepository infosprofessionnelleRepository;
    private PersonneRepository personneRepository;
    private PasswordEncoder passwordEncoder;



    @Override
    public List<PraticienconnecteDto> findAll() {
        List<Praticienconnecte> praticiens=praticienconnecteRepository.findAll();
        return praticiens.stream().map(praticien-> {
            try {
                return PraticienConnecteMapper.mapToPraticienConnecteDto(praticien);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();

    }

    @Override
    public PraticienconnecteDto findById(int id) throws Exception {
        Praticienconnecte praticienconnecte=praticienconnecteRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Praticien not found with given id: " + id));
        return PraticienConnecteMapper.mapToPraticienConnecteDto(praticienconnecte);

    }

    @Override
    public PraticienconnecteDto create(PraticienconnecteDto praticienconnecteDto) throws Exception {
        //Verification si personne déjà enregistrée (par son email), si oui leve une exception.(email saisi crypté avant vérification,car email crypté ds base de données)
        Personne personneToSave = personneRepository.findByEmail(Crypto.cryptService(praticienconnecteDto.getEmail()));
        if(personneToSave!=null) {
            throw new RessourceAlreadyexistsException ("Praticien already exist with this email");}
        else{
        //Verification si Info societe du praticien existe , si oui leve une exception.(num adeli et siret crypté vérifiction, car données cryptées dans base de données)
        Infosprofessionnelles infos = infosprofessionnelleRepository.findByNumAdeliAndNumSiret(Crypto.cryptService(praticienconnecteDto.getNumAdeli()),Crypto.cryptService(praticienconnecteDto.getNumSiret()));
        if(infos!=null){ throw new RessourceAlreadyexistsException ("Info Pro already exists");}
        else{
        //Si pas d'exception, creation des infos professionnelles et de la personne
            Personne personne = new Personne();
            personne.setNom(Crypto.cryptService(praticienconnecteDto.getNomPraticienConnecte()));
            personne.setPrenom(Crypto.cryptService(praticienconnecteDto.getPrenomPraticienConnecte()));
            personne.setEmail(Crypto.cryptService(praticienconnecteDto.getEmail()));
            personneToSave = personneRepository.save(personne);

            Infosprofessionnelles infosprofessionnelles = new Infosprofessionnelles();
            infosprofessionnelles.setNumAdeli(Crypto.cryptService(praticienconnecteDto.getNumAdeli()));
            infosprofessionnelles.setNumSiret(Crypto.cryptService(praticienconnecteDto.getNumSiret()));
            Infosprofessionnelles infosprofessionnellesToSave = infosprofessionnelleRepository.save(infosprofessionnelles);
        //Hashage du mot de passe :
        praticienconnecteDto.setPassword(passwordEncoder.encode(praticienconnecteDto.getPassword()));
        //Lieu sera récupéré dans le front et crée si pas enrgistré, avant soumission de la requete create Patient
        Lieu lieu = lieuRepository.findByNomVilleAndCodePostal(praticienconnecteDto.getNomVille(),praticienconnecteDto.getCodePostal());
        //Role sera récupéré dans me front (radio box -> User ou admin)
        Role role=roleRepository.findByNomRole(praticienconnecteDto.getNomRole());
        //Persistence du nouveau praticien avec les infos ci dessus
        Praticienconnecte praticienToSave = PraticienConnecteMapper.mapToPraticienConnecte(praticienconnecteDto,role,lieu,infosprofessionnellesToSave,personneToSave);
        Praticienconnecte praticienSaved =praticienconnecteRepository.save(praticienToSave);
        return PraticienConnecteMapper.mapToPraticienConnecteDto(praticienSaved);}}
    }

    @Override
    public PraticienconnecteDto update(int id, PraticienconnecteDto praticienconnecteDto) throws Exception {
        Praticienconnecte praticienconnecte = praticienconnecteRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Praticien not found with given id: " + id));
        //Nouveau Password mis à jour si modifié
        if(praticienconnecteDto.getPassword()!=null){praticienconnecte.setPassword(passwordEncoder.encode(praticienconnecteDto.getPassword()));}
        //Nouvelle ville mise à jour si besoin. Si pas ds base, sera créee avant soumission
        if(praticienconnecteDto.getCodePostal()!=null && praticienconnecteDto.getNomVille()!=null){praticienconnecte.setVille(lieuRepository.findByNomVilleAndCodePostal(praticienconnecteDto.getNomVille(),praticienconnecteDto.getCodePostal()));}
        //Recuperation des infos Personnes, et mise à jour du nom et email si besoin
        Personne personne= praticienconnecte.getIdentite();
        if(praticienconnecteDto.getNomPraticienConnecte()!=null){
            personne.setNom(Crypto.cryptService(praticienconnecteDto.getNomPraticienConnecte()));
            praticienconnecte.setIdentite(personne);
            personneRepository.save(personne);}
        if(praticienconnecteDto.getPrenomPraticienConnecte()!=null){
            personne.setPrenom(Crypto.cryptService(praticienconnecteDto.getPrenomPraticienConnecte()));
            praticienconnecte.setIdentite(personne);
            personneRepository.save(personne);}
        if(praticienconnecteDto.getEmail()!=null){
            personne.setEmail(Crypto.cryptService(praticienconnecteDto.getEmail()));
            praticienconnecte.setIdentite(personne);
            personneRepository.save(personne);}

        //Persisitence du praticien mis à jour
        return PraticienConnecteMapper.mapToPraticienConnecteDto(praticienconnecteRepository.save(praticienconnecte));

    }

    @Override
    @Transactional //Pour éviter le problème de LazyInitializationException.indique que vous essayez d'accéder à une propriété ou une collection d'une entité qui est chargée de manière paresseuse (lazy-loaded), mais que la session Hibernate n'est plus ouverte pour effectuer le chargement. Cette exception se produit souvent dans les applications qui utilisent la couche de persistance Hibernate/JPA et peut être particulièrement courante lors de l'utilisation de transactions ou de l'accès à des entités en dehors de leur contexte de session.
    public PraticienconnecteDto loadByEmail(String email) throws Exception {
       Praticienconnecte praticien =  praticienconnecteRepository.findByIdentiteEmail(Crypto.cryptService(email));
        if(praticien!=null){return PraticienConnecteMapper.mapToPraticienConnecteDto(praticien);}
        else{return null;}
    }


}
