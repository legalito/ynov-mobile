package com.formation.velo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

@Table(name = "Parking")
public class Parking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "recordId is mandatory")
    private String recordId;
    private String nom;
    private int voitureElectriqueCapacite;
    private int voitureCapacite;
    private int veloCapacite;
    private int motoCapacite;
    private int pmrCapacite;
    private String accesPMR;
    private String adresse;
    private String telephone;
    private String site;
    private String payement;
    private double longitude;
    private double lattitude;
}
