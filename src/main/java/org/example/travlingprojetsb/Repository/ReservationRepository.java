package org.example.travlingprojetsb.Repository;

import org.example.travlingprojetsb.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
}
