package Entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import  Enum.EtatDeVol;

import java.sql.Time;
import java.util.Date;

@Entity
@Table (name = "Vol")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE)


public class Vol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @Column(nullable = false)
    String flightNumber;

    Date dateDepart;

    Date dateArrive;

    Time timeDepart;

    Time timeArrive;





    @Column(nullable = false)
    EtatDeVol etatVol;


}
