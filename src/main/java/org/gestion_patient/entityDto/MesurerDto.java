package org.gestion_patient.entityDto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gestion_patient.entity.MesurerId;

import java.time.LocalDate;

@Data
@AllArgsConstructor @NoArgsConstructor
public class MesurerDto {
    private int idPhysique;
    private Float poids;
    private Float taille;
    private Boolean droitier;
    private Boolean lunettes;
    private Boolean dentaire;
    private String dateMesure;
    private int idPatient;


}
