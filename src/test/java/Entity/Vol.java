package Entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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

    String flightNumber;


}
