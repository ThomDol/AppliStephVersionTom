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
public class GrossessePostPartum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grossesse_postpartum", nullable = false)
    private int idGrossessePostPartum;

    @Column(name = "qualite_someil", nullable = true, length = 100)
    private String qualiteSommeil;

    @Column(name = "reeducation_perinee", nullable = true)
    private Boolean reeducationPerinee;

    @Column(name = "instabilite_vesicale", nullable = true, length = 100)
    private String instabiliteVesicale;

    @Column(name = "ecoulements_vaginaux", nullable = true, length=200)
    private String ecoulementsVaginaux;

    @Column(name = "retour_de_couche", nullable = true)
    private Boolean retourDeCouche;

    @Column(name = "douleur_abdominales", nullable = true)
    private Boolean douleurAbdominales;

    @Column(name = "fievre", nullable = true)
    private Boolean fievre;

    @Column(name = "infos_complementaires", nullable = true, length = 200)
    private String infosComplementaires;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_patient", nullable = false)
    private Patient patient;


}
