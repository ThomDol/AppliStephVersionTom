package org.gestion_patient.mapper;

import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.Medecintraitant;
import org.gestion_patient.entityDto.MedecintraitantDto;

public class MedecinTraitantMapper {
    public static MedecintraitantDto mapToMedecinTraitantDto (Medecintraitant medeinTraitant) throws Exception {
        return new MedecintraitantDto(
                medeinTraitant.getIdMedecinTraitant(),
                Crypto.decryptService(medeinTraitant.getNomMedecinTraitant()),
                Crypto.decryptService(medeinTraitant.getPrenomMedecinTraitant())
        );
    }

    public static Medecintraitant mapToMedecinTraitant (MedecintraitantDto medeinTraitantDto) throws Exception {
        return new Medecintraitant(
                medeinTraitantDto.getIdMedecinTraitant(),
                Crypto.cryptService(medeinTraitantDto.getNomMedecinTraitant()),
                Crypto.cryptService(medeinTraitantDto.getPrenomMedecinTraitant())
        );
    }


}
