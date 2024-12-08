package org.example.travlingprojetsb.Repository;

import org.example.travlingprojetsb.Entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel,Long> {


    List<Hotel> findHotelByIds(List<Long> Ids);
}
