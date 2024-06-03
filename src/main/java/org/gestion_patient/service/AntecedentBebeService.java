package org.gestion_patient.service;

import org.gestion_patient.entityDto.AntecedentBebeDto;


public interface AntecedentBebeService {
    AntecedentBebeDto create(AntecedentBebeDto antecedentBebeDto, int idPatient);
    AntecedentBebeDto update(int id,AntecedentBebeDto antecedentBebeDtoUpdated) ;
    AntecedentBebeDto getByidPatient(int id) ;
}
