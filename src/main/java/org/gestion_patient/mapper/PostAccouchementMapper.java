package org.gestion_patient.mapper;

import org.gestion_patient.entity.Accouchement;
import org.gestion_patient.entity.PostAccouchement;
import org.gestion_patient.entity.Patient;
import org.gestion_patient.entityDto.DataUtil;
import org.gestion_patient.entityDto.PostAccouchementDto;

public class PostAccouchementMapper {

    public static PostAccouchementDto mapToGrossessePostPartumDto(PostAccouchement postAccouchement) {
        return new PostAccouchementDto(
                postAccouchement.getIdGrossessePostPartum(),
                DataUtil.displayString(postAccouchement.getQualiteSommeil()),
                DataUtil.displayBoolean(postAccouchement.getReeducationPerinee()),
                DataUtil.displayString(postAccouchement.getInstabiliteVesicale()),
                DataUtil.displayString(postAccouchement.getEcoulementsVaginaux()),
                DataUtil.displayBoolean(postAccouchement.getRetourDeCouche()),
                DataUtil.displayBoolean(postAccouchement.getDouleurAbdominales()),
                DataUtil.displayBoolean(postAccouchement.getFievre()),
                DataUtil.displayString(postAccouchement.getInfosComplementaires()),
                postAccouchement.getAccouchement().getIdAccouchement());
    }

    public static PostAccouchement mapToGrossessePostPartum(PostAccouchementDto postAccouchementDto, Accouchement accouchement) {
        return new PostAccouchement(
                postAccouchementDto.getIdGrossessePostPartum(),
                DataUtil.displayString(postAccouchementDto.getQualiteSommeil()),
                DataUtil.displayBoolean(postAccouchementDto.getReeducationPerinee()),
                DataUtil.displayString(postAccouchementDto.getInstabiliteVesicale()),
                DataUtil.displayString(postAccouchementDto.getEcoulementsVaginaux()),
                DataUtil.displayBoolean(postAccouchementDto.getRetourDeCouche()),
                DataUtil.displayBoolean(postAccouchementDto.getDouleurAbdominales()),
                DataUtil.displayBoolean(postAccouchementDto.getFievre()),
                DataUtil.displayString(postAccouchementDto.getInfosComplementaires()),
                accouchement
        );
    }

}
