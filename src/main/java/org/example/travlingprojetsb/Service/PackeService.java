package org.example.travlingprojetsb.Service;

import org.example.travlingprojetsb.Entity.Hotel;
import org.example.travlingprojetsb.Entity.Packe;
import org.example.travlingprojetsb.Entity.Vol;
import org.example.travlingprojetsb.Repository.HotelRepository;
import org.example.travlingprojetsb.Repository.PackeRepository;
import org.example.travlingprojetsb.Repository.VolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class PackeService {

    @Autowired
    private PackeRepository packeRepository;

    @Autowired
    private VolRepository volRepository;

    @Autowired
    private HotelRepository hotelRepository;

    public List<Packe> findAllPackes() {
        return packeRepository.findAll();
    }

    public Packe findPackeById(Long id) {
        return packeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Packe not found with ID: " + id));
    }

    public void savePackeToDB(MultipartFile file, String nomPacke, String description, float prix, List<Long> volsIds, List<Long> hotelsIds) {
        Packe packe = new Packe();
        try {
            if (!file.isEmpty()) {
                packe.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error processing uploaded file", e);
        }

        packe.setNomPacke(nomPacke);
        packe.setDescription(description);
        packe.setPrix(prix);

        List<Vol> vols = volRepository.findVolsByIds(volsIds);
        List<Hotel> hotels = hotelRepository.findHotelByIds(hotelsIds);
        packe.setVols(vols);
        packe.setHotels(hotels);

        packeRepository.save(packe);
    }

    public void deletePackeById(Long id) {
        packeRepository.deleteById(id);
    }

    public void updatePacke(Long id, MultipartFile file, String nomPacke, String description, float prix, List<Long> volsIds, List<Long> hotelsIds) {
        Packe packe = findPackeById(id);

        try {
            if (!file.isEmpty()) {
                packe.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error processing uploaded file", e);
        }

        packe.setNomPacke(nomPacke);
        packe.setDescription(description);
        packe.setPrix(prix);

        List<Vol> vols = volRepository.findAllById(volsIds);
        List<Hotel> hotels = hotelRepository.findAllById(hotelsIds);
        packe.setVols(vols);
        packe.setHotels(hotels);

        packeRepository.save(packe);
    }
}
