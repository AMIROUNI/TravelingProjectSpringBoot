package org.example.travlingprojetsb.Repository;

import org.example.travlingprojetsb.Entity.Hotel;
import org.example.travlingprojetsb.Entity.Vol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel,Long> {
    @Query("SELECT h FROM Hotel h WHERE h.id IN :hotelIds")
    List<Hotel> findHotelByIds(@Param("hotelIds") List<Long> hotelIds);
}
