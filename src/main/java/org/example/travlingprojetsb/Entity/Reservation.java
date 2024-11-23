package org.example.travlingprojetsb.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.travlingprojetsb.Enum.EtatDeReservation;


import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.Enum;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Date dateDeResrvation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EtatDeReservation etat= EtatDeReservation.EN_ATTENTE; // État de la réservation

    @Column(nullable = false)
    private Date timeDeResrvation = new Date();

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client  client;

    @ManyToOne
    @JoinColumn(name = "id_packe") // Foreign key column
    private Packe packe;

    @OneToMany (mappedBy = "reservation",cascade = CascadeType.REMOVE)
    List<Passager> passagers;

}
