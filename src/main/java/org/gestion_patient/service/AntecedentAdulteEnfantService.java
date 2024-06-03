package org.gestion_patient.service;


import org.gestion_patient.entityDto.AntecedentAdulteEnfantDto;


public interface AntecedentAdulteEnfantService {
    AntecedentAdulteEnfantDto create(AntecedentAdulteEnfantDto antecedentAdulteEnfantDto, int idPatient) throws Exception;
    AntecedentAdulteEnfantDto update(int id,AntecedentAdulteEnfantDto antecedentAdulteEnfantDtoUpdated) throws Exception;
    AntecedentAdulteEnfantDto getByidPatient(int id) throws Exception;

}
