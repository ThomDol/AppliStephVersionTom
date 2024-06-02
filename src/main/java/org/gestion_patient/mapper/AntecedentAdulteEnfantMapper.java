package org.gestion_patient.mapper;

import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.AntecedentAdulteEnfant;
import org.gestion_patient.entity.Patient;
import org.gestion_patient.entityDto.AntecedentAdulteEnfantDto;

public class AntecedentAdulteEnfantMapper {
    public static AntecedentAdulteEnfantDto mapToAntecedentAdulteEnfantDto (AntecedentAdulteEnfant antecedentAdulteEnfant) throws Exception {
        return new AntecedentAdulteEnfantDto(
                antecedentAdulteEnfant.getIdAntecedentAdulteEnfant(),
                antecedentAdulteEnfant.getDateCreation(),
                antecedentAdulteEnfant.getDateUpdate()!=null?antecedentAdulteEnfant.getDateUpdate():null,
                displayInt(antecedentAdulteEnfant.getGrossesse()),
                displayBoolean(antecedentAdulteEnfant.getFumeur()),
                displayString(antecedentAdulteEnfant.getAllergie()),
                displayString(antecedentAdulteEnfant.getTraitement()),
                displayStringDecrypt(antecedentAdulteEnfant.getAntTraumatique()),
                displayStringDecrypt(antecedentAdulteEnfant.getAntChirurgicaux()),
                displayStringDecrypt(antecedentAdulteEnfant.getAntFamilliaux()),
                displayStringDecrypt(antecedentAdulteEnfant.getAntOrl()),
                displayStringDecrypt(antecedentAdulteEnfant.getAntVisceral()),
                displayStringDecrypt(antecedentAdulteEnfant.getAntCardioPulmonaire()),
                displayStringDecrypt(antecedentAdulteEnfant.getAntUroGynecaux()),
                displayStringDecrypt(antecedentAdulteEnfant.getAntPsy()),
                displayStringDecrypt(antecedentAdulteEnfant.getAntNotesDiverses()),
                antecedentAdulteEnfant.getPatient().getIdPatient()
        );
    }

    public static AntecedentAdulteEnfant mapToAntecedentAdulteEnfant (AntecedentAdulteEnfantDto antecedentAdulteEnfantDto, Patient patient) throws Exception {
        return new AntecedentAdulteEnfant (
                antecedentAdulteEnfantDto.getIdAntecedentAdulteEnfant(),
                antecedentAdulteEnfantDto.getDateCreation(),
                displayString(antecedentAdulteEnfantDto.getDateUpdate()),
                displayInt(antecedentAdulteEnfantDto.getGrossesse()),
                displayBoolean(antecedentAdulteEnfantDto.getFumeur()),
                displayString(antecedentAdulteEnfantDto.getAllergie()),
                displayString(antecedentAdulteEnfantDto.getTraitement()),
                displayStringEncrypt(antecedentAdulteEnfantDto.getAntTraumatique()),
                displayStringEncrypt(antecedentAdulteEnfantDto.getAntChirurgicaux()),
                displayStringEncrypt(antecedentAdulteEnfantDto.getAntFamilliaux()),
                displayStringEncrypt(antecedentAdulteEnfantDto.getAntOrl()),
                displayStringEncrypt(antecedentAdulteEnfantDto.getAntVisceral()),
                displayStringEncrypt(antecedentAdulteEnfantDto.getAntCardioPulmonaire()),
                displayStringEncrypt(antecedentAdulteEnfantDto.getAntUroGynecaux()),
                displayStringEncrypt(antecedentAdulteEnfantDto.getAntPsy()),
                displayStringEncrypt(antecedentAdulteEnfantDto.getAntNotesDiverses()),
                patient);

        }


    public static Boolean displayBoolean (Boolean elem){
        return elem!=null?elem:null;
    }
    public static Integer displayInt (Integer elem){
        return elem!=null?elem:null;
    }
    public static String displayString (String elem){return elem!=null?elem:null;}
    public static String displayStringEncrypt (String elem) throws Exception {return elem!=null? Crypto.cryptService(elem):null;}
    public static String displayStringDecrypt (String elem) throws Exception {return elem!=null? Crypto.decryptService(elem):null;}
}
