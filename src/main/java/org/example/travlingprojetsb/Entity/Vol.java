package org.example.travlingprojetsb.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.travlingprojetsb.Enum.EtatDeVol;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "Vol")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Vol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String flightNumber;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate dateDepart;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate dateArrive;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    LocalTime timeDepart;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    LocalTime timeArrive;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    EtatDeVol etatVol;


    @ManyToMany(mappedBy = "vols")
    private List<Packe> packs;

    @ManyToOne
    @JoinColumn(name = "aeroport_depart_id", nullable = false)
    private Aeroport aeroportDepart; // Aéroport de départ

    @ManyToOne
    @JoinColumn(name = "aeroport_arrivee_id", nullable = false)
    private Aeroport aeroportArrivee; // Aéroport d'arrivée

    @OneToMany(mappedBy = "vol", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Escale> escales;
}
