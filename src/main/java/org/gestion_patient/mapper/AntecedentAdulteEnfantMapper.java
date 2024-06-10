package org.gestion_patient.mapper;


import org.gestion_patient.entity.AntecedentAdulteEnfant;
import org.gestion_patient.entity.Patient;
import org.gestion_patient.entityDto.AntecedentAdulteEnfantDto;
import org.gestion_patient.entityDto.DataUtil;

public class AntecedentAdulteEnfantMapper {
    public static AntecedentAdulteEnfantDto mapToAntecedentAdulteEnfantDto (AntecedentAdulteEnfant antecedentAdulteEnfant) throws Exception {
        return new AntecedentAdulteEnfantDto(
                antecedentAdulteEnfant.getIdAntecedentAdulteEnfant(),
                antecedentAdulteEnfant.getDateCreation(),
                antecedentAdulteEnfant.getDateUpdate()!=null?antecedentAdulteEnfant.getDateUpdate():null,
                DataUtil.displayInt(antecedentAdulteEnfant.getGrossesse()),
                DataUtil.displayBoolean(antecedentAdulteEnfant.getFumeur()),
                DataUtil.displayString(antecedentAdulteEnfant.getAllergie()),
                DataUtil.displayString(antecedentAdulteEnfant.getTraitement()),
                DataUtil.displayStringDecrypt(antecedentAdulteEnfant.getAntTraumatique()),
                DataUtil.displayStringDecrypt(antecedentAdulteEnfant.getAntChirurgicaux()),
                DataUtil.displayStringDecrypt(antecedentAdulteEnfant.getAntFamilliaux()),
                DataUtil.displayStringDecrypt(antecedentAdulteEnfant.getAntOrl()),
                DataUtil.displayStringDecrypt(antecedentAdulteEnfant.getAntVisceral()),
                DataUtil.displayStringDecrypt(antecedentAdulteEnfant.getAntCardioPulmonaire()),
                DataUtil.displayStringDecrypt(antecedentAdulteEnfant.getAntUroGynecaux()),
                DataUtil.displayStringDecrypt(antecedentAdulteEnfant.getAntPsy()),
                DataUtil.displayStringDecrypt(antecedentAdulteEnfant.getAntNotesDiverses()),
                antecedentAdulteEnfant.getPatient().getIdPatient()
        );
    }

    public static AntecedentAdulteEnfant mapToAntecedentAdulteEnfant (AntecedentAdulteEnfantDto antecedentAdulteEnfantDto, Patient patient) throws Exception {
        return new AntecedentAdulteEnfant (
                antecedentAdulteEnfantDto.getIdAntecedentAdulteEnfant(),
                antecedentAdulteEnfantDto.getDateCreation(),
                DataUtil.displayString(antecedentAdulteEnfantDto.getDateUpdate()),
                DataUtil.displayInt(antecedentAdulteEnfantDto.getGrossesse()),
                DataUtil.displayBoolean(antecedentAdulteEnfantDto.getFumeur()),
                DataUtil.displayString(antecedentAdulteEnfantDto.getAllergie()),
                DataUtil.displayString(antecedentAdulteEnfantDto.getTraitement()),
                DataUtil.displayStringEncrypt(antecedentAdulteEnfantDto.getAntTraumatique()),
                DataUtil.displayStringEncrypt(antecedentAdulteEnfantDto.getAntChirurgicaux()),
                DataUtil.displayStringEncrypt(antecedentAdulteEnfantDto.getAntFamilliaux()),
                DataUtil.displayStringEncrypt(antecedentAdulteEnfantDto.getAntOrl()),
                DataUtil.displayStringEncrypt(antecedentAdulteEnfantDto.getAntVisceral()),
                DataUtil.displayStringEncrypt(antecedentAdulteEnfantDto.getAntCardioPulmonaire()),
                DataUtil.displayStringEncrypt(antecedentAdulteEnfantDto.getAntUroGynecaux()),
                DataUtil.displayStringEncrypt(antecedentAdulteEnfantDto.getAntPsy()),
                DataUtil.displayStringEncrypt(antecedentAdulteEnfantDto.getAntNotesDiverses()),
                patient);

        }



}
