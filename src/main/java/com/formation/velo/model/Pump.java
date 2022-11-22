package com.formation.velo.model;

import com.google.api.client.util.DateTime;
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

@Table(name = "Pump")
public class Pump implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String regionName;
    private double lattitude;
    private double longitude;
    private double prix;
    private Date dateMaj;
    private String ville;
    private String adresse;
    @NotBlank(message = "recordId is mandatory")
    private String recordId;
    private String carburant;
}
