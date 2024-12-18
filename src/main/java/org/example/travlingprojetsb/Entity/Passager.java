package org.example.travlingprojetsb.Entity;

import jakarta.persistence.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "Passager")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE)


public class Passager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    Integer CIN;
    @Column(nullable = false)
    String nom;
    @Column(nullable = false)
    String prenom;
    @Column(nullable = false)
    Long phone;

    //relation maony to one avec reservation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

}