package org.example.travlingprojetsb.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Packe") // ou "Package", selon votre choix
public class Packe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nomPacke; // Nom du package

    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;


    @Column(nullable = false)
    private int rating;


    @Column(nullable = false)
    private String description; // Nom du package



    @Column(nullable = false)
    private float prix ; // Nom du package


    // Relation Many-to-Many avec Vol
    @ManyToMany
    @JoinTable(
            name = "packe_vol",
            joinColumns = @JoinColumn(name = "packe_id"),
            inverseJoinColumns = @JoinColumn(name = "vol_id")
    )
    private List<Vol> vols=new ArrayList<>();

    // Relation Many-to-Many avec Hotel
    @ManyToMany
    @JoinTable(
            name = "packe_hotel",
            joinColumns = @JoinColumn(name = "packe_id"),
            inverseJoinColumns = @JoinColumn(name = "hotel_id")
    )
    private List<Hotel> hotels=new ArrayList<>();

    @OneToMany(mappedBy = "packe", cascade = CascadeType.ALL)
    private List<Reservation> reservations;
}
