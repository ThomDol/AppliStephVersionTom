package org.gestion_patient.entityDto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor @NoArgsConstructor
public class PhysiqueDto {
    private int idPhysique;
    private Float poids;
    private Float taille;
    private Boolean droitier;
    private Boolean lunettes;
    private Boolean dentaire;
    private String dateMesure;


}
