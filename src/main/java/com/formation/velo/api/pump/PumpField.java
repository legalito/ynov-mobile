package com.formation.velo.api.pump;

import com.google.api.client.util.DateTime;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import java.util.Date;

@Getter
@Setter
public class PumpField {

    @SerializedName("reg_name")
    private String regionName;
    @SerializedName("prix_valeur")
    private double prix;
    @SerializedName("prix_maj")
    private Date dateMaj;
    private String ville;
    @SerializedName("adresse")
    private String adresse;
    @SerializedName("geom")
    private double[] position;
    @SerializedName("prix_nom")
    private String carburant;
}
