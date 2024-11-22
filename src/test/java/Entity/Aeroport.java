package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
