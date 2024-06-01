package org.gestion_patient.mapper;

import org.gestion_patient.entity.Mesurer;
import org.gestion_patient.entity.MesurerId;
import org.gestion_patient.entity.Patient;
import org.gestion_patient.entity.Physique;
import org.gestion_patient.entityDto.MesurerDto;

public class MesurerMapper {
    public static MesurerDto mapToMesurerDto(Mesurer mesurer){
        return new MesurerDto(
                mesurer.getPhysique().getIdPhysique(),
                mesurer.getPhysique().getPoids(),
                mesurer.getPhysique().getTaille(),
                mesurer.getPhysique().getDroitier(),
                mesurer.getPhysique().getLunettes(),
                mesurer.getPhysique().getDentaire(),
                mesurer.getPhysique().getDateMesure(),
                mesurer.getPatient().getIdPatient()
        );
    }

    public static Mesurer mapToMesurer(MesurerDto mesurerDto, Physique physique, Patient patient){
        MesurerId mesurerId = new MesurerId();
        mesurerId.setIdPhysique(mesurerDto.getIdPhysique());
        mesurerId.setIdPatient(mesurerDto.getIdPatient());
        Mesurer mesurer = new Mesurer();
        mesurer.setMesurerId(mesurerId);
        mesurer.setPhysique(physique);
        mesurer.setPatient(patient);

        return mesurer;



    }




}
