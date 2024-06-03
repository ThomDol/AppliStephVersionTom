package org.gestion_patient.repository;

import org.gestion_patient.entity.AntecedentBebe;
import org.gestion_patient.entity.Antecedentssante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AntecedentBebeRepository extends JpaRepository<AntecedentBebe,Integer> {
    AntecedentBebe findByPatientIdPatient (int id);
}
