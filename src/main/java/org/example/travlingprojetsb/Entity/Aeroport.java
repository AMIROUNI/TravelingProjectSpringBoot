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
@Table(name = "Aeroport")
public class Aeroport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Identifiant unique de l'aéroport

    @Column(nullable = false)
    private String nom; // Nom de l'aéroport

    @Column(nullable = false, unique = true, length = 3)
    private String codeIATA; // Code IATA (ex : TUN pour Tunis-Carthage)

    @Column(nullable = false)
    private String ville; // Ville où se trouve l'aéroport

    @Column(nullable = false)
    private String pays; // Pays où se trouve l'aéroport

    @Column(nullable = true)
    private String description; // Description ou remarques sur l'aéroport



    @ManyToOne
    @JoinColumn(name = "aeroport_depart_id", nullable = false)
    private Aeroport aeroportDepart; // Aéroport de départ

    @ManyToOne
    @JoinColumn(name = "aeroport_arrivee_id", nullable = false)
    private Aeroport aeroportArrivee; // Aéroport d'arrivée




    @OneToMany(mappedBy = "aeroport")
    private List<Escale> escales;


    
}
