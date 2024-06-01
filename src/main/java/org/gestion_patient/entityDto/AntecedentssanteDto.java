package org.gestion_patient.entityDto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gestion_patient.entity.Patient;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class AntecedentssanteDto {
    private int idAntecedents;
    private String dateCreation;
    private String dateUpdate;
    private String antTraumatique;
    private String antChirurgicaux;
    private String antFamilliaux;
    private String antOrl;
    private String antVisceral;
    private String antCardioPulmonaire;
    private String antUroGynecaux;
    private String antPsy;
    private String antNotesDiverses;
    private String maternite;
    private Float poidsDeNaissance;
    private Float tailleDeNaissance;
    private Float perimetreCranien;
    private Float apgar;
    private Boolean depassementDeTerme;
    private Boolean prematurite;
    private Boolean deformationDuCrane;
    private Boolean bosseSeroSanguine ;
    private Boolean cephalhematome;
    private Boolean paralysieObstetricaleDuPlexusBrachial;
    private Boolean paralysieFaciale;
    private Boolean fractureClavicule;
    private Boolean dysplasieHanche;
    private Boolean plagiocephalie;
    private Boolean torticolis;
    private Boolean refluxGastroOesophagien;
    private Boolean coliques;
    private Integer efficaciteSuccion;
    private Boolean sucagePouce;
    private Boolean tetine;
    private Boolean presenceBruitsArticulaires;
    private Boolean tics;
    private Boolean gestation;
    private Boolean grossesseMultiple;
    private String douleursPendantGrossesse;
    private String etatPsychoEmotionnel;
    private Boolean disproportionFoetoPelvienne;
    private String traitementLieGrossesse;
    private String mouvementsBebe;
    private Boolean cesariennePrevue;
    private Boolean projetPeridurale;
    private Boolean projetAllaitement;
    private Boolean nausees;
    private Boolean constipation;
    private Boolean diarrhees;
    private Boolean aigreursEstomac;
    private Boolean oedemesMembresInferieurs;
    private Boolean pesanteurPelvienne;
    private Boolean incontinence;
    private Boolean tensionMammaire;
    private Boolean mastose;
    private Boolean ecoulementsVaginaux;
    private Float tailleBebe;
    private Float poidsBebe;
    private Boolean douleursAbdominales;
    private Boolean fievre;
    private Boolean allaitementMaternel;
    private String typeRespiration ;
    private int idPatient;



}
