package org.gestion_patient.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "antecedents_sante", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id_patient")
})


public class Antecedentssante {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_antecedents", nullable = false)
    private int idAntecedents;

    @Column(name = "date_creation", nullable = false)
    private String dateCreation;

    @Column(name = "date_update", nullable = true)
    private String dateUpdate;

    @Column(name = "ant_traumatique", nullable = true, length = 600)
    private String antTraumatique;

    @Column(name = "ant_chirurgicaux", nullable = true, length = 600)
    private String antChirurgicaux;

    @Column(name = "ant_familliaux", nullable = true, length = 600)
    private String antFamilliaux;

    @Column(name = "ant_orl", nullable = true, length = 600)
    private String antOrl;

    @Column(name = "ant_visceral", nullable = true, length = 600)
    private String antVisceral;

    @Column(name = "ant_cardio_pulmonaire", nullable = true, length = 600)
    private String antCardioPulmonaire;

    @Column(name = "ant_uro_gynecaux", nullable = true, length = 600)
    private String antUroGynecaux;

    @Column(name = "ant_psy", nullable = true, length = 600)
    private String antPsy;

    @Lob
    @Column(name = "ant_notes_diverses", nullable = true)
    private String antNotesDiverses;

    @Column(name = "maternite", nullable = true, length = 100)
    private String maternite;

    @Column(name = "poids_de_naissance", nullable = true)
    private Float poidsDeNaissance;

    @Column(name = "taille_de_naissance", nullable = true)
    private Float tailleDeNaissance;

    @Column(name = "perimetre_cranien", nullable = true)
    private Float perimetreCranien;

    @Column(name = "apgar", nullable = true)
    private Float apgar;

    @Column(name = "depassement_de_terme", nullable = true)
    private Boolean depassementDeTerme;

    @Column(name = "prematurite", nullable = true)
    private Boolean prematurite;

    @Column(name = "deformation_du_crane", nullable = true)
    private Boolean deformationDuCrane;

    @Column(name = "bosse_sero_sanguine", nullable = true)
    private Boolean bosseSeroSanguine ;

    @Column(name = "cephalhematome", nullable = true)
    private Boolean cephalhematome;

    @Column(name = "paralysie_obstetricale_du_plexus_brachial", nullable = true)
    private Boolean paralysieObstetricaleDuPlexusBrachial;

    @Column(name = "paralysie_faciale", nullable = true)
    private Boolean paralysieFaciale;

    @Column(name = "fracture_clavicule", nullable = true)
    private Boolean fractureClavicule;

    @Column(name = "dysplasie_hanche", nullable = true)
    private Boolean dysplasieHanche;

    @Column(name = "plagiocephalie", nullable = true)
    private Boolean plagiocephalie;

    @Column(name = "torticolis", nullable = true)
    private Boolean torticolis;

    @Column(name = "reflux_gastro_oesophagien", nullable = true)
    private Boolean refluxGastroOesophagien;

    @Column(name = "coliques", nullable = true)
    private Boolean coliques;

    @Column(name = "efficacite_succion", nullable = true)
    private Integer efficaciteSuccion;

    @Column(name = "sucage_pouce", nullable = true)
    private Boolean sucagePouce;

    @Column(name = "tetine", nullable = true)
    private Boolean tetine;

    @Column(name = "presence_bruits_articulaires", nullable = true)
    private Boolean presenceBruitsArticulaires;

    @Column(name = "tics", nullable = true)
    private Boolean tics;

    @Column(name = "gestation", nullable = true)
    private Boolean gestation;

    @Column(name = "grossesse_multiple", nullable = true)
    private Boolean grossesseMultiple;

    @Column(name = "douleurs_pendant_grossesse", nullable = true, length = 100)
    private String douleursPendantGrossesse;

    @Column(name = "etat_psycho_emotionnel", nullable = true, length = 100)
    private String etatPsychoEmotionnel;

    @Column(name = "disproportion_foeto_pelvienne", nullable = true)
    private Boolean disproportionFoetoPelvienne;

    @Column(name = "traitement_lie_grossesse", nullable = true, length = 200)
    private String traitementLieGrossesse;

    @Column(name = "mouvements_bebe", nullable = true, length = 200)
    private String mouvementsBebe;

    @Column(name = "cesarienne_prevue", nullable = true)
    private Boolean cesariennePrevue;

    @Column(name = "projet_peridurale", nullable = true)
    private Boolean projetPeridurale;

    @Column(name = "projet_allaitement", nullable = true)
    private Boolean projetAllaitement;

    @Column(name = "nausees", nullable = true)
    private Boolean nausees;

    @Column(name = "constipation", nullable = true)
    private Boolean constipation;

    @Column(name = "diarrhees", nullable = true)
    private Boolean diarrhees;

    @Column(name = "aigreurs_estomac", nullable = true)
    private Boolean aigreursEstomac;

    @Column(name = "oedemes_membres_inferieurs", nullable = true)
    private Boolean oedemesMembresInferieurs;

    @Column(name = "pesanteur_pelvienne", nullable = true)
    private Boolean pesanteurPelvienne;

    @Column(name = "incontinence", nullable = true)
    private Boolean incontinence;

    @Column(name = "tension_mammaire", nullable = true)
    private Boolean tensionMammaire;

    @Column(name = "mastose", nullable = true)
    private Boolean mastose;

    @Column(name = "ecoulements_vaginaux", nullable = true)
    private Boolean ecoulementsVaginaux;

    @Column(name = "taille_bebe", nullable = true)
    private Float tailleBebe;

    @Column(name = "poids_bebe", nullable = true)
    private Float poidsBebe;

    @Column(name = "douleurs_abdominales", nullable = true)
    private Boolean douleursAbdominales;

    @Column(name = "fievre", nullable = true)
    private Boolean fievre;

    @Column(name = "allaitement_maternel", nullable = true)
    private Boolean allaitementMaternel;

    @Column(name = "type_respiration", nullable = true,length=100)
    private String typeRespiration ;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_patient", nullable = false)
    private Patient patient;





}