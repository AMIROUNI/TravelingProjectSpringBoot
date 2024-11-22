package org.example.travlingprojetsb.Entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import  org.example.travlingprojetsb.Enum.EtatDeVol;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.Enum;

@Entity
@Table (name = "Vol")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE)


public class Vol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @Column(nullable = false)
    String flightNumber;
    @Column(nullable = false)
    Date dateDepart;
    @Column(nullable = false)

    Date dateArrive;
    @Column(nullable = false)

    Time timeDepart;

    @Column(nullable = false)
    Time timeArrive;





    @Column(nullable = false)
    EtatDeVol etatVol;


    @ManyToMany(mappedBy = "vols")
    private List<Packe> packs;



    @OneToMany(mappedBy = "aeroportDepart")
    private List<Vol> volsDepart; // Liste des vols au départ de cet aéroport

    @OneToMany(mappedBy = "aeroportArrivee")
    private List<Vol> volsArrivee; // Liste des vols à destination de cet aéroport


    @OneToMany(mappedBy = "vol")
    private List<Escale> escales;


}
