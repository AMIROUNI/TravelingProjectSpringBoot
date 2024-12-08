package org.example.travlingprojetsb.Repository;

import org.example.travlingprojetsb.Entity.Escale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EscaleRepository extends JpaRepository<Escale,Long> {
    List<Escale> findByVolId(Long volId);
}
