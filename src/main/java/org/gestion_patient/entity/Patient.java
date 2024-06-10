package org.gestion_patient.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_patient", nullable = false)
    private int idPatient;

    @Column(name = "date_naissance", nullable = false)
    private String dateNaissance;

    @Column(name = "tel", nullable = true, length = 80)
    private String tel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ville", nullable = true)
    private Lieu ville;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_genre", nullable = false)
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profession", nullable = true)
    private Profession profession;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_type_patient", nullable = false)
    private TypePatient typePatient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medecin_traitant", nullable = true)
    private Medecintraitant medecinTraitant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_identite", nullable = false)
    private Personne identite;

    @Column(name = "email",nullable=true, length = 80)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_praticien", nullable = false)
    private Praticienconnecte praticien;



    @ManyToMany
    @JoinTable(
            name="pratiquer",
            joinColumns = @JoinColumn(name = "id_patient"),
            inverseJoinColumns = @JoinColumn(name = "id_sport")
    )
    private List<Sport> sportList = new ArrayList<>();


}