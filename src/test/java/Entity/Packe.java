package Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

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

    // Relation Many-to-Many avec Vol
    @ManyToMany
    @JoinTable(
            name = "packe_vol",
            joinColumns = @JoinColumn(name = "packe_id"),
            inverseJoinColumns = @JoinColumn(name = "vol_id")
    )
    private Set<Vol> vols;

    // Relation Many-to-Many avec Hotel
    @ManyToMany
    @JoinTable(
            name = "packe_hotel",
            joinColumns = @JoinColumn(name = "packe_id"),
            inverseJoinColumns = @JoinColumn(name = "hotel_id")
    )
    private Set<Hotel> hotels;
}
