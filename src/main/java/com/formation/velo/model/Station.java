package com.formation.velo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

@Table(name = "Stations")
public class Station implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "name is mandatory")
    private String name;
    private double lattitude;
    private double longitude;
    private String status;
    private int bikeStands;
    private int availableBikes;
    private int availableBikeStands;

    @NotBlank(message = "recordId is mandatory")
    private String recordId;
    private String adresse;
    private boolean estModifier;
}
