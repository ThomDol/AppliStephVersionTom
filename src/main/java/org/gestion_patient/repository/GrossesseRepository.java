package org.gestion_patient.repository;

import org.gestion_patient.entity.Antecedentssante;
import org.gestion_patient.entity.Grossesse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrossesseRepository extends JpaRepository<Grossesse,Integer> {
    Grossesse findByPatientIdPatient (int id);
}
