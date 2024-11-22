package org.example.travlingprojetsb.Service;

import org.example.travlingprojetsb.Entity.Packe;
import org.example.travlingprojetsb.Repository.PackeRepository;
import org.example.travlingprojetsb.Entity.Packe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackeService {

    @Autowired
   PackeRepository packeRepository;


    public Packe addPacke(Packe packe) {
        return packeRepository.save(packe);
    }

    public void deletePackeById(Long id) {
        packeRepository.deleteById(id);
    }



    public List<Packe> findAllPackes() {
        return packeRepository.findAll();
    }

    public Packe updatePacke(Packe packe) {
        if (packeRepository.existsById((long) packe.getId())) {
            return packeRepository.save(packe);
        } else {
            throw new RuntimeException("Cannot update Packe with ID: " + packe.getId() + ", it does not exist.");
        }
    }
}
