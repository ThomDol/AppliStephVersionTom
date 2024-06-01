package org.gestion_patient.mapper;

import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.Antecedentssante;
import org.gestion_patient.entity.Patient;
import org.gestion_patient.entityDto.AntecedentssanteDto;

public class AntecedentsanteMapper {
    public static AntecedentssanteDto mapToAntecedentssanteDto (Antecedentssante antecedentssante) throws Exception {
        return new AntecedentssanteDto(
                antecedentssante.getIdAntecedents(),
                antecedentssante.getDateCreation(),
                antecedentssante.getDateUpdate()!=null?antecedentssante.getDateUpdate():null,
                displayStringDecrypt(antecedentssante.getAntTraumatique()),
                displayStringDecrypt(antecedentssante.getAntChirurgicaux()),
                displayStringDecrypt(antecedentssante.getAntFamilliaux()),
                displayStringDecrypt(antecedentssante.getAntOrl()),
                displayStringDecrypt(antecedentssante.getAntVisceral()),
                displayStringDecrypt(antecedentssante.getAntCardioPulmonaire()),
                displayStringDecrypt(antecedentssante.getAntUroGynecaux()),
                displayStringDecrypt(antecedentssante.getAntPsy()),
                displayStringDecrypt(antecedentssante.getAntNotesDiverses()),
                displayString(antecedentssante.getMaternite()),
                displayFloat(antecedentssante.getPoidsDeNaissance()),
                displayFloat(antecedentssante.getTailleDeNaissance()),
                displayFloat(antecedentssante.getPerimetreCranien()),
                displayFloat(antecedentssante.getApgar()),
                displayBoolean(antecedentssante.getDepassementDeTerme()),
                displayBoolean(antecedentssante.getPrematurite()),
                displayBoolean(antecedentssante.getDeformationDuCrane()),
                displayBoolean(antecedentssante.getBosseSeroSanguine()),
                displayBoolean(antecedentssante.getCephalhematome()),
                displayBoolean(antecedentssante.getParalysieObstetricaleDuPlexusBrachial()),
                displayBoolean(antecedentssante.getParalysieFaciale()),
                displayBoolean(antecedentssante.getFractureClavicule()),
                displayBoolean(antecedentssante.getDysplasieHanche()),
                displayBoolean(antecedentssante.getPlagiocephalie()),
                displayBoolean(antecedentssante.getTorticolis()),
                displayBoolean(antecedentssante.getRefluxGastroOesophagien()),
                displayBoolean(antecedentssante.getColiques()),
                displayInt(antecedentssante.getEfficaciteSuccion()),
                displayBoolean(antecedentssante.getSucagePouce()),
                displayBoolean(antecedentssante.getTetine()),
                displayBoolean(antecedentssante.getPresenceBruitsArticulaires()),
                displayBoolean(antecedentssante.getTics()),
                displayBoolean(antecedentssante.getGestation()),
                displayBoolean(antecedentssante.getGrossesseMultiple()),
                displayString(antecedentssante.getDouleursPendantGrossesse()),
                displayString(antecedentssante.getEtatPsychoEmotionnel()),
                displayBoolean(antecedentssante.getDisproportionFoetoPelvienne()),
                displayString(antecedentssante.getTraitementLieGrossesse()),
                displayString(antecedentssante.getMouvementsBebe()),
                displayBoolean(antecedentssante.getCesariennePrevue()),
                displayBoolean(antecedentssante.getProjetPeridurale()),
                displayBoolean(antecedentssante.getProjetAllaitement()),
                displayBoolean(antecedentssante.getNausees()),
                displayBoolean(antecedentssante.getConstipation()),
                displayBoolean(antecedentssante.getDiarrhees()),
                displayBoolean(antecedentssante.getAigreursEstomac()),
                displayBoolean(antecedentssante.getOedemesMembresInferieurs()),
                displayBoolean(antecedentssante.getCephalhematome()),
                displayBoolean(antecedentssante.getIncontinence()),
                displayBoolean(antecedentssante.getTensionMammaire()),
                displayBoolean(antecedentssante.getMastose()),
                displayBoolean(antecedentssante.getEcoulementsVaginaux()),
                displayFloat(antecedentssante.getTailleBebe()),
                displayFloat(antecedentssante.getPoidsBebe()),
                displayBoolean(antecedentssante.getDouleursAbdominales()),
                displayBoolean(antecedentssante.getFievre()),
                displayBoolean(antecedentssante.getAllaitementMaternel()),
                displayString(antecedentssante.getTypeRespiration()),
                antecedentssante.getPatient().getIdPatient()
        );
    }

    public static Antecedentssante mapToAntecedentssante (AntecedentssanteDto antecedentssanteDto, Patient patient) throws Exception {
        return new Antecedentssante(
                antecedentssanteDto.getIdAntecedents(),
                antecedentssanteDto.getDateCreation(),
                antecedentssanteDto.getDateUpdate()!=null?antecedentssanteDto.getDateUpdate():null,
                displayStringEncrypt(antecedentssanteDto.getAntTraumatique()),
                displayStringEncrypt(antecedentssanteDto.getAntChirurgicaux()),
                displayStringEncrypt(antecedentssanteDto.getAntFamilliaux()),
                displayStringEncrypt(antecedentssanteDto.getAntOrl()),
                displayStringEncrypt(antecedentssanteDto.getAntVisceral()),
                displayStringEncrypt(antecedentssanteDto.getAntCardioPulmonaire()),
                displayStringEncrypt(antecedentssanteDto.getAntUroGynecaux()),
                displayStringEncrypt(antecedentssanteDto.getAntPsy()),
                displayStringEncrypt(antecedentssanteDto.getAntNotesDiverses()),
                displayStringEncrypt(antecedentssanteDto.getMaternite()),
                displayFloat(antecedentssanteDto.getPoidsDeNaissance()),
                displayFloat(antecedentssanteDto.getTailleDeNaissance()),
                displayFloat(antecedentssanteDto.getPerimetreCranien()),
                displayFloat(antecedentssanteDto.getApgar()),
                displayBoolean(antecedentssanteDto.getDepassementDeTerme()),
                displayBoolean(antecedentssanteDto.getPrematurite()),
                displayBoolean(antecedentssanteDto.getDeformationDuCrane()),
                displayBoolean(antecedentssanteDto.getBosseSeroSanguine()),
                displayBoolean(antecedentssanteDto.getCephalhematome()),
                displayBoolean(antecedentssanteDto.getParalysieObstetricaleDuPlexusBrachial()),
                displayBoolean(antecedentssanteDto.getParalysieFaciale()),
                displayBoolean(antecedentssanteDto.getFractureClavicule()),
                displayBoolean(antecedentssanteDto.getDysplasieHanche()),
                displayBoolean(antecedentssanteDto.getPlagiocephalie()),
                displayBoolean(antecedentssanteDto.getTorticolis()),
                displayBoolean(antecedentssanteDto.getRefluxGastroOesophagien()),
                displayBoolean(antecedentssanteDto.getColiques()),
                displayInt(antecedentssanteDto.getEfficaciteSuccion()),
                displayBoolean(antecedentssanteDto.getSucagePouce()),
                displayBoolean(antecedentssanteDto.getTetine()),
                displayBoolean(antecedentssanteDto.getPresenceBruitsArticulaires()),
                displayBoolean(antecedentssanteDto.getTics()),
                displayBoolean(antecedentssanteDto.getGestation()),
                displayBoolean(antecedentssanteDto.getGrossesseMultiple()),
                displayString(antecedentssanteDto.getDouleursPendantGrossesse()),
                displayString(antecedentssanteDto.getEtatPsychoEmotionnel()),
                displayBoolean(antecedentssanteDto.getDisproportionFoetoPelvienne()),
                displayString(antecedentssanteDto.getTraitementLieGrossesse()),
                displayString(antecedentssanteDto.getMouvementsBebe()),
                displayBoolean(antecedentssanteDto.getCesariennePrevue()),
                displayBoolean(antecedentssanteDto.getProjetPeridurale()),
                displayBoolean(antecedentssanteDto.getProjetAllaitement()),
                displayBoolean(antecedentssanteDto.getNausees()),
                displayBoolean(antecedentssanteDto.getConstipation()),
                displayBoolean(antecedentssanteDto.getDiarrhees()),
                displayBoolean(antecedentssanteDto.getAigreursEstomac()),
                displayBoolean(antecedentssanteDto.getOedemesMembresInferieurs()),
                displayBoolean(antecedentssanteDto.getCephalhematome()),
                displayBoolean(antecedentssanteDto.getIncontinence()),
                displayBoolean(antecedentssanteDto.getTensionMammaire()),
                displayBoolean(antecedentssanteDto.getMastose()),
                displayBoolean(antecedentssanteDto.getEcoulementsVaginaux()),
                displayFloat(antecedentssanteDto.getTailleBebe()),
                displayFloat(antecedentssanteDto.getPoidsBebe()),
                displayBoolean(antecedentssanteDto.getDouleursAbdominales()),
                displayBoolean(antecedentssanteDto.getFievre()), 
                displayBoolean(antecedentssanteDto.getAllaitementMaternel()),
                displayString(antecedentssanteDto.getTypeRespiration()),
                patient
        );
    }


    public static Boolean displayBoolean (Boolean elem){
        return elem!=null?elem:null;
    }
    public static String displayString (String elem){return elem!=null?elem:null;}
    public static String displayStringEncrypt (String elem) throws Exception {return elem!=null? Crypto.cryptService(elem):null;}
    public static String displayStringDecrypt (String elem) throws Exception {return elem!=null? Crypto.decryptService(elem):null;}


    public static Integer displayInt (Integer elem){return elem!=null?elem:null;}
    public static Float displayFloat (Float elem){return elem!=null?elem:null;}




}


