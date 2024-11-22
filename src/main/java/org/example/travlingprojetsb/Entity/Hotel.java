package org.example.travlingprojetsb.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nomHotel;


    @Column(nullable = false)
    private String image;


    @Column(nullable = false)
    private String descreptoin;

    @Column(nullable = false)
    private int nombreEtoiles; // Nombre d'étoiles de l'hôtel

    @ManyToMany(mappedBy = "hotels")
    private List<Packe> packs;
}