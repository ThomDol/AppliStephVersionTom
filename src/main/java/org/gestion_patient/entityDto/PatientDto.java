package org.gestion_patient.entityDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {

    private int idPatient;
    private String dateNaissance;
    private String tel;
    private String nomVille;
    private String codePostal;
    private String nomGenre;
    private String nomProfession;
    private String nomTypePatient;
    private String nomMedecinTraitant;
    private String prenomMedecinTraitant;
    private String villeMedecinTraitant;
    private String nomPatient ;
    private String prenomPatient ;
    private String email;
    private List<AntecedentsBebeDto> antecedentBebeList;
    private List<AntecedentAdulteEnfantDto> antecedentAdulteEnfantList;
    private List<GrossesseDto> grossesseList;
    private List<AccouchementDto> accouchementList;
    private List<PhysiqueDto> physiqueList;
    private List<SportDto> sportList;
    private List<RendezvousDto> rendezvousList;
}

