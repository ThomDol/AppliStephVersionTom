package org.gestion_patient.repository;

import org.gestion_patient.entity.Antecedentssante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AntecedentssanteRepository extends JpaRepository<Antecedentssante,Integer> {
    Antecedentssante findByPatientIdPatient (int id);

}
