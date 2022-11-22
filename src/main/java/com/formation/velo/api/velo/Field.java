package com.formation.velo.api.velo;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Field {
    @SerializedName("available_bike_stands")
    private int availableBikeStands;
    @SerializedName("bike_stands")
    private int bikeStands;
    private String address;
    private String name;
    private String status;
    @SerializedName("available_bikes")
    private int availableBikes;
    private double[] position;
}
