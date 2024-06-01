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
@Table(name = "praticienconnecte")
public class Praticienconnecte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_praticien", nullable = false)
    private int idPraticien;

    @Column(name = "password", nullable = false, length = 80)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_role", nullable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_ville", nullable = false)
    private Lieu ville;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_infos_professionnelles", nullable = false)
    private Infosprofessionnelles infosProfessionnelles;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_identite", nullable = false)
    private Personne identite;

    @OneToMany(mappedBy = "praticien", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Patient> patientList=new ArrayList<>();



}