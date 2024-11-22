package org.example.travlingprojetsb.Service;

import org.example.travlingprojetsb.Entity.Ville;
import org.example.travlingprojetsb.Repository.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VilleService {

    @Autowired
    private VilleRepository villeRepository;


    public Ville addVille(Ville ville) {
        return villeRepository.save(ville);
    }


    public void deleteVilleById(Long id) {
        villeRepository.deleteById(id);
    }

    public Ville findVilleById(Long id) {
        Optional<Ville> ville = villeRepository.findById(id);
        if (ville.isPresent()) {
            return ville.get();
        } else {
            throw new RuntimeException("Ville not found with ID: " + id);
        }
    }

    public List<Ville> findAllVilles() {
        return villeRepository.findAll();
    }

    public Ville updateVille(Long id, Ville ville) {
        Ville existingVille = findVilleById(id);
        existingVille.setName(ville.getName());
        existingVille.setImagesVille(ville.getImagesVille());
        existingVille.setDescription(ville.getDescription());
        return villeRepository.save(existingVille);
    }
}
