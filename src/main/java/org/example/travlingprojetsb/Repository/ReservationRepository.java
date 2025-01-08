package org.example.travlingprojetsb.Repository;

import org.example.travlingprojetsb.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
     List<Reservation> findReservationsByUserId(Long userID);
}
