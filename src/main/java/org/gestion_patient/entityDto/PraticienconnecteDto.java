package org.gestion_patient.entityDto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PraticienconnecteDto {

    private int idPraticien;
    private String password;
    private String nomRole;
    private String nomVille;
    private String codePostal;
    private String numAdeli;
    private String numSiret;
    private String nomPraticienConnecte;
    private String prenomPraticienConnecte;
    private String email;
    private List<PatientDto> patientList;

}
