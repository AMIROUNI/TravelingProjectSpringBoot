package org.example.travlingprojetsb.Entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

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

    @Column(nullable = false)
    String prename;
    @Column(nullable = false)
    String  lastname;
    @Column(nullable = false)
    Integer age;
    @Column(nullable = false)
    String address ;
    @Column(nullable = false)
    String sexe ;
    @Column(nullable = false)
    String email ;
    @Column(nullable = false)
    String password ;
    @Column(nullable = false)

    Integer CIN ;
    @Column(nullable = false)
    Integer telphone;
    @OneToMany (mappedBy = "client",cascade = CascadeType.REMOVE)
    List<Reservation> reservations;


}
