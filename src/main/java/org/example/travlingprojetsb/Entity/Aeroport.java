package org.example.travlingprojetsb.Entity;

import lombok.*;
import org.example.travlingprojetsb.Entity.Ville;
import jakarta.persistence.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Aeroport")
@Getter
@Setter
public class Aeroport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Identifiant unique de l'aéroport

    @Column(nullable = false)
    private String nom; // Nom de l'aéroport

    @Column(nullable = false, length = 10)
    private String codeIATA; // Code IATA (ex : TUN pour Tunis-Carthage)

    @Column(nullable = false)
    private String pays; // Pays où se trouve l'aéroport

    @Column(nullable = true)
    private String description; // Description ou remarques sur l'aéroport




    @Column(nullable = false)
    private double latitude;



    @Column(nullable = false)
    private  double longitude;




    @OneToMany(mappedBy = "aeroportDepart")
    private List<Vol> volsDepart; // Liste des vols au départ de cet aéroport

    @OneToMany(mappedBy = "aeroportArrivee")
    private List<Vol> volsArrivee; // Liste des vols à destination de cet aéroport

    @OneToMany(mappedBy = "aeroport")
    private List<Escale> escales;

    @ManyToOne
    @JoinColumn(name = "ville_id") // Creates the foreign key column 'ville_id'
    private Ville ville;

    
}
