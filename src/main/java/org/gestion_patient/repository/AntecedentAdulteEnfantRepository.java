package org.gestion_patient.repository;

import org.gestion_patient.entity.AntecedentAdulteEnfant;
import org.gestion_patient.entity.Antecedentssante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AntecedentAdulteEnfantRepository extends JpaRepository<AntecedentAdulteEnfant,Integer> {
    AntecedentAdulteEnfant findByPatientIdPatient (int id);
}
