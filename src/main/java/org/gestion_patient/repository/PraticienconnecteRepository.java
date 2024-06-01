package org.gestion_patient.repository;

import org.gestion_patient.entity.Praticienconnecte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PraticienconnecteRepository extends JpaRepository<Praticienconnecte,Integer> {
    Praticienconnecte findByIdentiteEmail (String email);
}
