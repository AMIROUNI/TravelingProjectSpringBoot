package org.example.travlingprojetsb.Service;

import org.example.travlingprojetsb.Entity.Hotel;
import org.example.travlingprojetsb.Repository.*;
import org.example.travlingprojetsb.Entity.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;


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



    public void saveHotelToDB(MultipartFile file, String nomHotel, String description, int nombreEtoiles, double latitude, double longitude) {
        Hotel hotel = new Hotel();
        String fileName = org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename());

        if (fileName.contains("..")) {
            throw new IllegalArgumentException("Chemin de fichier invalide : " + fileName);
        }

        try {
            // Encodage de l'image en base64
            hotel.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors du traitement du fichier uploadé", e);
        }

        // Affecter les autres propriétés
        hotel.setNomHotel(nomHotel);
        hotel.setDescription(description);
        hotel.setNombreEtoiles(nombreEtoiles);
        hotel.setLatitude(latitude);
        hotel.setLongitude(longitude);

        // Enregistrer l'objet hotel dans la base de données
        hotelRepository.save(hotel);
    }


   public   Hotel updateHotel(Hotel hotel){
        return  hotelRepository.saveAndFlush(hotel);
     }

}
