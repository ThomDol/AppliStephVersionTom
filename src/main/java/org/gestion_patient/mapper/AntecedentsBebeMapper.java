package org.gestion_patient.mapper;

import org.gestion_patient.entity.AntecedentsBebe;
import org.gestion_patient.entity.Patient;
import org.gestion_patient.entityDto.AntecedentsBebeDto;
import org.gestion_patient.entityDto.DataUtil;

public class AntecedentsBebeMapper {

        public static AntecedentsBebeDto mapToAntecedentssanteBebeDto (AntecedentsBebe antecedentBebe)  {
                return new AntecedentsBebeDto(
                        antecedentBebe.getIdAntecedentBebe(),
                        antecedentBebe.getDateCreation(),
                        antecedentBebe.getDateUpdate(),
                        displayString(antecedentBebe.getMaternite()),
                        displayFloat(antecedentBebe.getPerimetreCranien()),
                        displayFloat(antecedentBebe.getApgar()),
                        displayBoolean(antecedentBebe.getDepassementDeTerme()),
                        displayBoolean(antecedentBebe.getPrematurite()),
                        displayBoolean(antecedentBebe.getDeformationDuCrane()),
                        displayBoolean(antecedentBebe.getBosseSeroSanguine()),
                        displayBoolean(antecedentBebe.getCephalhematome()),
                        displayBoolean(antecedentBebe.getParalysieObstetricaleDuPlexusBrachial()),
                        displayBoolean(antecedentBebe.getParalysieFaciale()),
                        displayBoolean(antecedentBebe.getFractureClavicule()),
                        displayBoolean(antecedentBebe.getDysplasieHanche()),
                        displayBoolean(antecedentBebe.getPlagiocephalie()),
                        displayBoolean(antecedentBebe.getTorticolis()),
                        displayBoolean(antecedentBebe.getRefluxGastroOesophagien()),
                        displayBoolean(antecedentBebe.getColiques()),
                        displayBoolean(antecedentBebe.getAllaitementMaternelle()),
                        displayInt(antecedentBebe.getEfficaciteSuccion()),
                        displayBoolean(antecedentBebe.getSucagePouce()),
                        displayBoolean(antecedentBebe.getTetine()),
                        displayString(antecedentBebe.getTypeRespiration()),
                        displayBoolean(antecedentBebe.getPresenceBruitsArticulaires()),
                        displayBoolean(antecedentBebe.getTics()),
                        antecedentBebe.getPatient().getIdPatient());
        }
        public static AntecedentsBebe mapToAntecedentBebe (AntecedentsBebeDto antecedentBebeDto, Patient patient){
                return new AntecedentsBebe(
                        antecedentBebeDto.getIdAntecedentBebe(),
                        antecedentBebeDto.getDateCreation(),
                        antecedentBebeDto.getDateUpdate(),
                        DataUtil.displayString(antecedentBebeDto.getMaternite()),
                        DataUtil.displayFloat(antecedentBebeDto.getPerimetreCranien()),
                        DataUtil.displayFloat(antecedentBebeDto.getApgar()),
                        DataUtil.displayBoolean(antecedentBebeDto.getDepassementDeTerme()),
                        DataUtil.displayBoolean(antecedentBebeDto.getPrematurite()),
                        DataUtil.displayBoolean(antecedentBebeDto.getDeformationDuCrane()),
                        DataUtil.displayBoolean(antecedentBebeDto.getBosseSeroSanguine()),
                        DataUtil.displayBoolean(antecedentBebeDto.getCephalhematome()),
                        DataUtil.displayBoolean(antecedentBebeDto.getParalysieObstetricaleDuPlexusBrachial()),
                        DataUtil.displayBoolean(antecedentBebeDto.getParalysieFaciale()),
                        DataUtil.displayBoolean(antecedentBebeDto.getFractureClavicule()),
                        DataUtil.displayBoolean(antecedentBebeDto.getDysplasieHanche()),
                        DataUtil.displayBoolean(antecedentBebeDto.getPlagiocephalie()),
                        DataUtil.displayBoolean(antecedentBebeDto.getTorticolis()),
                        DataUtil.displayBoolean(antecedentBebeDto.getRefluxGastroOesophagien()),
                        DataUtil.displayBoolean(antecedentBebeDto.getColiques()),
                        DataUtil.displayBoolean(antecedentBebeDto.getAllaitementMaternelle()),
                        DataUtil.displayInt(antecedentBebeDto.getEfficaciteSuccion()),
                        DataUtil.displayBoolean(antecedentBebeDto.getSucagePouce()),
                        DataUtil.displayBoolean(antecedentBebeDto.getTetine()),
                        DataUtil.displayString(antecedentBebeDto.getTypeRespiration()),
                        DataUtil.displayBoolean(antecedentBebeDto.getPresenceBruitsArticulaires()),
                        DataUtil.displayBoolean(antecedentBebeDto.getTics()),
                        patient);
        }


        public static Boolean displayBoolean (Boolean elem){
                return elem!=null?elem:null;
        }
        public static String displayString (String elem){return elem!=null?elem:null;}
        public static Integer displayInt (Integer elem){return elem!=null?elem:null;}
        public static Float displayFloat (Float elem){return elem!=null?elem:null;}

}