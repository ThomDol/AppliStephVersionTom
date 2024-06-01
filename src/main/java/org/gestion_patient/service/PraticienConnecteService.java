package org.gestion_patient.service;

import org.gestion_patient.entity.Praticienconnecte;
import org.gestion_patient.entityDto.PraticienconnecteDto;

import java.util.List;

public interface PraticienConnecteService {
    List<PraticienconnecteDto> findAll();
    PraticienconnecteDto findById(int id) throws Exception;
    PraticienconnecteDto create(PraticienconnecteDto praticienconnecteDto) throws Exception;
    PraticienconnecteDto update(int id,PraticienconnecteDto praticienconnecteDto) throws Exception;
    PraticienconnecteDto loadByEmail(String id) throws Exception;
}
