package com.formation.velo.api.parking;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ParkingField {
    @SerializedName("nom_complet")
    private String nom;
    @SerializedName("capacite_vehicule_electrique")
    private int voitureElectriqueCapacite;
    @SerializedName("capacite_voiture")
    private int voitureCapacite;
    @SerializedName("capacite_velo")
    private int veloCapacite;
    @SerializedName("capacite_moto")
    private int motoCapacite;
    @SerializedName("capacite_pmr")
    private int pmrCapacite;

    @SerializedName("acces_pmr")
    private String accesPMR;
    private String adresse;
    private String telephone;
    @SerializedName("site_web")
    private String site;
    @SerializedName("moyen_paiement")
    private String payement;

    @SerializedName("location")
    private double[] position;
}
