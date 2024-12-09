package org.example.travlingprojetsb.Repository;

import org.example.travlingprojetsb.Entity.Vol;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


@Repository
public interface VolRepository extends JpaRepository<Vol, Long> {

    // Custom query to find Vols by a list of volIds
    @Query("SELECT v FROM Vol v WHERE v.id IN :volIds")
    List<Vol> findVolsByIds(@Param("volIds") List<Long> volIds);

}
