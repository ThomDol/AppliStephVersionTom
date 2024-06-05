package org.gestion_patient.service;

import org.gestion_patient.entityDto.AntecedentsBebeDto;


public interface AntecedentsBebeService {
    AntecedentsBebeDto create(AntecedentsBebeDto antecedentBebeDto, int idPatient);
    AntecedentsBebeDto update(int id, AntecedentsBebeDto antecedentBebeDtoUpdated) ;
    AntecedentsBebeDto getByidPatient(int id) ;
}
