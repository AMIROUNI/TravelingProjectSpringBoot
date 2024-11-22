package org.example.travlingprojetsb.Repository;

import org.example.travlingprojetsb.Entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,Long> {
}
