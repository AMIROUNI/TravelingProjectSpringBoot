package org.example.travlingprojetsb.Service;

import org.example.travlingprojetsb.Entity.Vol;
import org.example.travlingprojetsb.Repository.VolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolService {

    @Autowired
    private VolRepository volRepository;

    /**
     * Adds a new Vol.
     */
    public Vol addVol(Vol vol) {
        return volRepository.save(vol);
    }

    /**
     * Deletes a Vol by its ID.
     */
    public void deleteVolById(Long id) {
        volRepository.deleteById(id);
    }

    /**
     * Finds a Vol by its ID.
     */
    public Vol findVolById(Long id) {
        return volRepository.findById(id).orElse(null);
    }

    /**
     * Updates an existing Vol.
     */
    public Vol updateVol(Vol updatedVol) {
        if (volRepository.existsById(updatedVol.getId())) {
            return volRepository.save(updatedVol); // Save the updated Vol
        }
        return null; // Or throw an exception if needed
    }

    /**
     * Retrieves all Vols.
     */
    public List<Vol> findAllVols() {
        return volRepository.findAll();
    }
}
