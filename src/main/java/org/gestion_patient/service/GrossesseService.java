package org.gestion_patient.service;

import org.gestion_patient.entityDto.AntecedentssanteDto;
import org.gestion_patient.entityDto.GrossesseDto;

public interface GrossesseService {
    GrossesseDto create(GrossesseDto grossesseDto, int idPatient) ;
    GrossesseDto update(int id,GrossesseDto grossesseDto) ;
    GrossesseDto getByidPatient(int id) ;

}
