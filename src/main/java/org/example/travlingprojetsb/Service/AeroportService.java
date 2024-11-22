package org.example.travlingprojetsb.Service;

import org.example.travlingprojetsb.Entity.Aeroport;
import org.example.travlingprojetsb.Repository.AeroportRepository;
import org.example.travlingprojetsb.Entity.Aeroport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AeroportService {

    @Autowired
    private AeroportRepository aeroportRepository;


    public Aeroport addAeroport(Aeroport aeroport) {
        return aeroportRepository.save(aeroport);
    }


    public void deleteAeroportById(Long id) {
        aeroportRepository.deleteById(id);
    }



    public List<Aeroport> findAllAeroports() {
        return aeroportRepository.findAll();
    }

    public Aeroport updateAeroport(Aeroport aeroport) {
        if (aeroportRepository.existsById((long) aeroport.getId())) {
            return aeroportRepository.save(aeroport);
        } else {
            throw new RuntimeException("Cannot update Aeroport with ID: " + aeroport.getId() + ", it does not exist.");
        }
    }
}
