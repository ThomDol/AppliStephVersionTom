package org.gestion_patient.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "antecedents_adulte_enfant", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id_patient")
})
public class AntecedentAdulteEnfant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_antecedents", nullable = false)
    private int idAntecedentAdulteEnfant;

    @Column(name = "date_creation", nullable = false)
    private String dateCreation;

    @Column(name = "date_update", nullable = true)
    private String dateUpdate;

    @Column(name = "maternite", nullable = true)
    private Integer grossesse;

    @Column(name = "fumeur", nullable = true)
    private Boolean fumeur;

    @Column(name = "allergie", nullable = true, length = 300)
    private String allergie;

    @Column(name = "traitement", nullable = true, length = 300)
    private String traitement;

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
    @Column(name = "ant_notes_diverses",columnDefinition = "TEXT", nullable = true)
    private String antNotesDiverses;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_patient", nullable = false)
    private Patient patient;


}
