package org.gestion_patient.entityDto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostAccouchementDto {

    private int idGrossessePostPartum;
    private String qualiteSommeil;
    private Boolean reeducationPerinee;
    private String instabiliteVesicale;
    private String ecoulementsVaginaux;
    private Boolean retourDeCouche;
    private Boolean douleurAbdominales;
    private Boolean fievre;
    private String infosComplementaires;
    private int idAccouchement;

}
