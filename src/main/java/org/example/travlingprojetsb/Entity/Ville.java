package org.example.travlingprojetsb.Entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Time;
import java.util.Date;

@Entity
@Table (name = "Ville")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE)


public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String imagesVille;
    @Column(nullable = false)
    String description;

}