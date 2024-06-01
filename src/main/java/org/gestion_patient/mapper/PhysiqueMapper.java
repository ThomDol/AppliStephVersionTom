package org.gestion_patient.mapper;

import org.gestion_patient.entity.Physique;
import org.gestion_patient.entityDto.PhysiqueDto;

public class PhysiqueMapper {
    public static PhysiqueDto mapToPhysiqueDto (Physique physique){
        return new PhysiqueDto(
                physique.getIdPhysique(),
                displayFloat(physique.getPoids()),
                displayFloat(physique.getTaille()),
                displayBoolean(physique.getDroitier()),
                displayBoolean(physique.getLunettes()),
                displayBoolean(physique.getDentaire()),
                physique.getDateMesure()
        );
    }

    public static Physique mapToPhysique (PhysiqueDto physiqueDto){
        Physique physique = new Physique();
                physique.setIdPhysique(physiqueDto.getIdPhysique());
                physique.setPoids(physiqueDto.getPoids());
                physique.setTaille(physiqueDto.getTaille());
                physique.setDroitier(physiqueDto.getDroitier());
                physique.setLunettes(physiqueDto.getLunettes());
                physique.setDentaire(physiqueDto.getDentaire());
                physique.setDateMesure(physiqueDto.getDateMesure());
        return physique;
    }


    public static Float displayFloat (Float elem){return elem!=null?elem:null;}
    public static Boolean displayBoolean (Boolean elem){return elem!=null?elem:null;}
}
