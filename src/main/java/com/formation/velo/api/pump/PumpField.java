package com.formation.velo.api.pump;

import com.google.api.client.util.DateTime;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;

@Getter
@Setter
public class PumpField {

    @SerializedName("reg_name")
    private String regionName;
    @SerializedName("prix_valeur")
    private double prix;
    @SerializedName("prix_maj")
    private DateTime dateMaj;
    private String ville;
    private String adresse;
    @SerializedName("geom")
    private double[] position;
}
