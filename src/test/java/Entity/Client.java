package Entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table (name = "Clients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE)


public class Client {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String prename;

    String  lastname;

    Integer age;

    String address ;

    String sexe ;

    String email ;
    String password ;

    Integer CIN ;


}
