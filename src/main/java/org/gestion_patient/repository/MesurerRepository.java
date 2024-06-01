package org.gestion_patient.repository;

import org.gestion_patient.entity.Mesurer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MesurerRepository extends JpaRepository<Mesurer,Integer> {
    Mesurer findByPhysiqueDateMesureAndPatientIdPatient(String dateMesure, int patientId);
    List<Mesurer> findAllByPatientIdPatient(int patientId);
    Mesurer findByPatientIdPatientAndPhysiqueIdPhysique(int physiqueId,int patientId);
}
