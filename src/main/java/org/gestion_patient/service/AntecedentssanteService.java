package org.gestion_patient.service;


import org.gestion_patient.entityDto.AntecedentssanteDto;



public interface AntecedentssanteService {
    AntecedentssanteDto create(AntecedentssanteDto antecedentssanteDto,int idPatient) throws Exception;
    AntecedentssanteDto update(int id,AntecedentssanteDto antecedentssanteDtoUpdated) throws Exception;
    AntecedentssanteDto getByidPatient(int id) throws Exception;
}
