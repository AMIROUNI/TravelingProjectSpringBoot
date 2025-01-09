package org.example.travlingprojetsb.Repository;

import org.example.travlingprojetsb.Entity.Hotel;
import org.example.travlingprojetsb.Entity.Packe;
import org.example.travlingprojetsb.Entity.Vol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackeRepository extends JpaRepository<Packe,Long> {
    List<Vol> findVolsById(Long packeId);
    List<Hotel> findHotelsById(Long packeId);
}
