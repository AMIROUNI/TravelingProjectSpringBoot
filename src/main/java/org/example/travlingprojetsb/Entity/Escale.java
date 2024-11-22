package org.example.travlingprojetsb.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Escale")
public class Escale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vol_id", nullable = false)
    private Vol vol; // Référence au vol auquel cette escale appartient

    @ManyToOne
    @JoinColumn(name = "aeroport_id", nullable = false)
    private Aeroport aeroport; // Aéroport où l'escale se fait

    @Column(nullable = false)
    private String heureArrivee; // Heure d'arrivée à l'escale

    @Column(nullable = false)
    private String heureDepart; // Heure de départ de l'escale

}
