package org.example.travlingprojetsb.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Timer;

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
    @JoinColumn(name = "vol_id")
    @JsonIgnore
    private Vol vol;

    @ManyToOne
    @JoinColumn(name = "aeroport_id", nullable = true)
    private Aeroport aeroport; // Aéroport où l'escale se fait

    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalTime heureArrivee; // Heure d'arrivée à l'escale

    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalTime heureDepart; // Heure de départ de l'escale

}
