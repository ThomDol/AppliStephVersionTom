package org.gestion_patient.mapper;

import org.gestion_patient.entity.GrossessePostPartum;
import org.gestion_patient.entity.Patient;
import org.gestion_patient.entityDto.GrossessePostPartumDto;

public class GrossessePostPartumMapper {

    public static GrossessePostPartumDto mapToGrossessePostPartumDto (GrossessePostPartum grossessePostPartum){
        return new GrossessePostPartumDto(
        grossessePostPartum.getIdGrossessePostPartum(),
        displayString(grossessePostPartum.getQualiteSommeil()),
        displayBoolean(grossessePostPartum.getReeducationPerinee()),
        displayString(grossessePostPartum.getInstabiliteVesicale()),
        displayString(grossessePostPartum.getEcoulementsVaginaux()),
        displayBoolean(grossessePostPartum.getRetourDeCouche()),
        displayBoolean(grossessePostPartum.getDouleurAbdominales()),
        displayBoolean(grossessePostPartum.getFievre()),
        displayString(grossessePostPartum.getInfosComplementaires()),
        grossessePostPartum.getPatient().getIdPatient());
    }

    public static GrossessePostPartum mapToGrossessePostPartum (GrossessePostPartumDto grossessePostPartumDto, Patient patient){
        return new GrossessePostPartum(
                grossessePostPartumDto.getIdGrossessePostPartum(),
                displayString(grossessePostPartumDto.getQualiteSommeil()),
                displayBoolean(grossessePostPartumDto.getReeducationPerinee()),
                displayString(grossessePostPartumDto.getInstabiliteVesicale()),
                displayString(grossessePostPartumDto.getEcoulementsVaginaux()),
                displayBoolean(grossessePostPartumDto.getRetourDeCouche()),
                displayBoolean(grossessePostPartumDto.getDouleurAbdominales()),
                displayBoolean(grossessePostPartumDto.getFievre()),
                displayString(grossessePostPartumDto.getInfosComplementaires()),
                patient
        );
    }

    public static Boolean displayBoolean (Boolean elem){
        return elem!=null?elem:null;
    }
    public static String displayString (String elem){return elem!=null?elem:null;}
}
