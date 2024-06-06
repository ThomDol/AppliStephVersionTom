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
    private String dateNaissance="00/00/0000";

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_praticien", nullable = false)
    private Praticienconnecte praticien;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rendezvous> rendezvousList=new ArrayList<>();

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AntecedentsBebe> antecedentBebeList= new ArrayList<>();

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AntecedentAdulteEnfant> antecedentAdulteEnfantList= new ArrayList<>();

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Accouchement> accouchementList = new ArrayList<>();

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grossesse> grossesseList = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "mesurer",
            joinColumns = @JoinColumn(name = "id_patient"),
            inverseJoinColumns = @JoinColumn(name = "id_physique")
    )
    private List<Physique> physiqueList = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name="pratiquer",
            joinColumns = @JoinColumn(name = "id_patient"),
            inverseJoinColumns = @JoinColumn(name = "id_sport")
    )
    private List<Sport> sportList = new ArrayList<>();


}