package org.gestion_patient.mapper;

import org.gestion_patient.entity.AntecedentBebe;
import org.gestion_patient.entity.Antecedentssante;
import org.gestion_patient.entity.Patient;
import org.gestion_patient.entityDto.AnteceddentBebeDto;
import org.gestion_patient.entityDto.AntecedentssanteDto;

public class AntecedentBebeMapper {

        public static AnteceddentBebeDto mapToAntecedentssanteBebeDto (AntecedentBebe antecedentBebe)  {
                return new AnteceddentBebeDto(
                        antecedentBebe.getIdAntecedentBebe(),
                        antecedentBebe.getDateCreation(),
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
        public static AntecedentBebe mapToAntecedentBebe (AnteceddentBebeDto antecedentBebeDto, Patient patient){
                return new AntecedentBebe(
                        antecedentBebeDto.getIdAntecedentBebe(),
                        antecedentBebeDto.getDateCreation(),
                        displayString(antecedentBebeDto.getMaternite()),
                        displayFloat(antecedentBebeDto.getPerimetreCranien()),
                        displayFloat(antecedentBebeDto.getApgar()),
                        displayBoolean(antecedentBebeDto.getDepassementDeTerme()),
                        displayBoolean(antecedentBebeDto.getPrematurite()),
                        displayBoolean(antecedentBebeDto.getDeformationDuCrane()),
                        displayBoolean(antecedentBebeDto.getBosseSeroSanguine()),
                        displayBoolean(antecedentBebeDto.getCephalhematome()),
                        displayBoolean(antecedentBebeDto.getParalysieObstetricaleDuPlexusBrachial()),
                        displayBoolean(antecedentBebeDto.getParalysieFaciale()),
                        displayBoolean(antecedentBebeDto.getFractureClavicule()),
                        displayBoolean(antecedentBebeDto.getDysplasieHanche()),
                        displayBoolean(antecedentBebeDto.getPlagiocephalie()),
                        displayBoolean(antecedentBebeDto.getTorticolis()),
                        displayBoolean(antecedentBebeDto.getRefluxGastroOesophagien()),
                        displayBoolean(antecedentBebeDto.getColiques()),
                        displayBoolean(antecedentBebeDto.getAllaitementMaternelle()),
                        displayInt(antecedentBebeDto.getEfficaciteSuccion()),
                        displayBoolean(antecedentBebeDto.getSucagePouce()),
                        displayBoolean(antecedentBebeDto.getTetine()),
                        displayString(antecedentBebeDto.getTypeRespiration()),
                        displayBoolean(antecedentBebeDto.getPresenceBruitsArticulaires()),
                        displayBoolean(antecedentBebeDto.getTics()),
                        patient);
        }


        public static Boolean displayBoolean (Boolean elem){
                return elem!=null?elem:null;
        }
        public static String displayString (String elem){return elem!=null?elem:null;}
        public static Integer displayInt (Integer elem){return elem!=null?elem:null;}
        public static Float displayFloat (Float elem){return elem!=null?elem:null;}

}