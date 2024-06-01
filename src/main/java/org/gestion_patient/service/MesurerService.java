package org.gestion_patient.service;

import org.gestion_patient.entity.MesurerId;
import org.gestion_patient.entityDto.MesurerDto;
import org.gestion_patient.entityDto.PhysiqueDto;

import java.util.List;

public interface MesurerService {
    MesurerDto createByPatient(PhysiqueDto physiqueDto, int idPatient);
    List<MesurerDto> getAllMesurersByIdPatient(int idPatient );
    MesurerDto update(MesurerDto mesurerDto);
}
