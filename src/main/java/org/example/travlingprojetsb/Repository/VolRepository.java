package org.example.travlingprojetsb.Repository;

import org.example.travlingprojetsb.Entity.Vol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VolRepository extends JpaRepository<Vol,Long> {
    List<Vol> findAllById(List<Long> ids);
}
