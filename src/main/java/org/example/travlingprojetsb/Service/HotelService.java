package org.example.travlingprojetsb.Service;

import org.example.travlingprojetsb.Entity.Hotel;
import org.example.travlingprojetsb.Repository.*;
import org.example.travlingprojetsb.Entity.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private Repository.HotelRepository hotelRepository;


    public Hotel addHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }


    public void deleteHotelById(Long id) {
        hotelRepository.deleteById(id);
    }


    public Hotel findHotelById(Long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (hotel.isPresent()) {
            return hotel.get();
        } else {
            throw new RuntimeException("Hotel not found with ID: " + id);
        }
    }


    public List<Hotel> findAllHotels() {
        return hotelRepository.findAll();
    }


    public Hotel updateHotel(Hotel hotel) {
        if (hotelRepository.existsById((long) hotel.getId())) {
            return hotelRepository.saveAllAndFlush(hotel);
        } else {
            throw new RuntimeException("Cannot update Hotel with ID: " + hotel.getId() + ", it does not exist.");
        }
    }
}
