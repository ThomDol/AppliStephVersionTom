package org.gestion_patient.mapper;

import org.gestion_patient.entity.Grossesse;
import org.gestion_patient.entity.Patient;
import org.gestion_patient.entityDto.GrossesseDto;

public class GrossesseMapper {
    public static GrossesseDto mapToGrossesseDto(Grossesse grossesse) {
        return new GrossesseDto(
                grossesse.getIdGrossesse(),
                grossesse.getDateCreation(),
                grossesse.getMaternite(),
                displayBoolean(grossesse.getGrossesseMultiple()),
                displayString(grossesse.getDouleursPendantGrossesse()),
                displayString(grossesse.getEtatPsychoEmotionnel()),
                displayString(grossesse.getTraitementLieGrossesse()),
                displayString(grossesse.getMouvementsBebe()),
                displayBoolean(grossesse.getCesariennePrevue()),
                displayBoolean(grossesse.getProjetPeridurale()),
                displayBoolean(grossesse.getProjetAllaitement()),
                displayBoolean(grossesse.getNausees()),
                displayBoolean(grossesse.getConstipation()),
                displayBoolean(grossesse.getDiarrhees()),
                displayBoolean(grossesse.getAigreursEstomac()),
                displayBoolean(grossesse.getOedemesMembresInferieurs()),
                displayBoolean(grossesse.getPesanteurPelvienne()),
                displayBoolean(grossesse.getIncontinence()),
                displayBoolean(grossesse.getTensionMammaire()),
                displayBoolean(grossesse.getMastose()),
                grossesse.getPatient().getIdPatient());
    }

    public static Grossesse mapToGrossesse (GrossesseDto grossesseDto, Patient patient){
        return new Grossesse(
                grossesseDto.getIdGrossesse(),
                grossesseDto.getDateCreation(),
                grossesseDto.getMaternite(),
                displayBoolean(grossesseDto.getGrossesseMultiple()),
                displayString(grossesseDto.getDouleursPendantGrossesse()),
                displayString(grossesseDto.getEtatPsychoEmotionnel()),
                displayString(grossesseDto.getTraitementLieGrossesse()),
                displayString(grossesseDto.getMouvementsBebe()),
                displayBoolean(grossesseDto.getCesariennePrevue()),
                displayBoolean(grossesseDto.getProjetPeridurale()),
                displayBoolean(grossesseDto.getProjetAllaitement()),
                displayBoolean(grossesseDto.getNausees()),
                displayBoolean(grossesseDto.getConstipation()),
                displayBoolean(grossesseDto.getDiarrhees()),
                displayBoolean(grossesseDto.getAigreursEstomac()),
                displayBoolean(grossesseDto.getOedemesMembresInferieurs()),
                displayBoolean(grossesseDto.getPesanteurPelvienne()),
                displayBoolean(grossesseDto.getIncontinence()),
                displayBoolean(grossesseDto.getTensionMammaire()),
                displayBoolean(grossesseDto.getMastose()),
                patient);
    }

    public static Boolean displayBoolean (Boolean elem){
        return elem!=null?elem:null;
    }
    public static String displayString (String elem){return elem!=null?elem:null;}
}
