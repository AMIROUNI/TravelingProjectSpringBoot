package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import  Enum.EtatDeReservation;

import java.util.Date;
import java.util.List;

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
    private EtatDeReservation etat; // État de la réservation

    //relation many to one avec packe
    @ManyToOne
    @JoinColumn(name="id_packe")
    private Packe packe;

    //relation one to many avec passager
    @OneToMany(mappedBy = "passanger" ,cascade = CascadeType.REMOVE)
    private List<Reservation> reservations;

}
