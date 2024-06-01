package org.gestion_patient.mapper;

import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.Medecintraitant;
import org.gestion_patient.entity.Personne;
import org.gestion_patient.entityDto.MedecintraitantDto;

public class MedecinTraitantMapper {
    public static MedecintraitantDto mapToMedecinTraitantDto (Medecintraitant medecintraitant) throws Exception {
        return new MedecintraitantDto(
                medecintraitant.getIdMedecinTraitant(),
                Crypto.decryptService(medecintraitant.getIdentiteDoc().getNom()),
                Crypto.decryptService(medecintraitant.getIdentiteDoc().getPrenom())
        );
    }
    //Personne associée sera cryptée avant utilisation du Mapper
    public static Medecintraitant mapToMedecinTraitant (MedecintraitantDto medeinTraitantDto, Personne personne) throws Exception {
        return new Medecintraitant(
                medeinTraitantDto.getIdMedecinTraitant(),
                personne
        );
    }


}
