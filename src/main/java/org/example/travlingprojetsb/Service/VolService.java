package org.example.travlingprojetsb.Service;


import org.example.travlingprojetsb.Entity.Vol;
import org.example.travlingprojetsb.Repository.VolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VolService {

    @Autowired
    private VolRepository volRepository;

    // 1. Récupérer tous les vols
    public List<Vol> findAllVols() {
        return volRepository.findAll();
    }

    // 2. Ajouter un nouveau vol
    public void saveVol(Vol vol) {
        volRepository.save(vol);
    }

    // 3. Trouver un vol par son ID
    public Vol findVolById(Long id) {
        Optional<Vol> optionalVol = volRepository.findById(id);
        return optionalVol.orElse(null);
    }

    // 4. Mettre à jour un vol existant
    public void updateVol(Vol updatedVol) {
        if (volRepository.existsById(updatedVol.getId())) {
            volRepository.save(updatedVol);
        }
    }

    // 5. Supprimer un vol par son ID
    public void deleteVol(Long id) {
        if (volRepository.existsById(id)) {
            volRepository.deleteById(id);
        }



    }
    // Fetch a list of Vols based on the list of volIds
    public List<Vol> findVolsByIds(List<Long> volIds) {
        return volRepository.findAllById(volIds);
    }
}
