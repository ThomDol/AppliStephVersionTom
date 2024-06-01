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
@Table(name = "medecintraitant")
public class Medecintraitant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medecin_traitant", nullable = false)
    private int idMedecinTraitant;

    @Column(name = "nom_medecin_traitant", nullable = false, length = 80)
    private String nomMedecinTraitant;

    @Column(name = "prenom_medecin_traitant", nullable = false, length = 80)
    private String prenomMedecinTraitant;

}