package org.example.travlingprojetsb.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Table(name = "Hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nomHotel;


    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;


    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int nombreEtoiles; // Nombre d'étoiles de l'hôtel



    @Column(nullable = false)
    private double latitude;



    @Column(nullable = false)
    private  double longitude;



    @ManyToMany(mappedBy = "hotels")
    private List<Packe> packs;



}