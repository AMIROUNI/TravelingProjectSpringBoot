package org.example.travlingprojetsb.Repository;

import org.example.travlingprojetsb.Entity.Passager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PassagerRepository extends JpaRepository<Passager,Long> {

    @Query("SELECT p FROM Passager p WHERE p.reservation.id = :id_reservation")
    List<Passager> findByIdReservation(@Param("id_reservation") Long id_reservation);
}
