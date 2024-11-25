package org.example.travlingprojetsb.Service;

import ch.qos.logback.core.util.StringUtil;
import org.example.travlingprojetsb.Entity.Ville;
import org.example.travlingprojetsb.Repository.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class VilleService {

    @Autowired
    private VilleRepository villeRepository;




    public void saveVilleToDB(MultipartFile file, String name, String description) {
        Ville ville = new Ville();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        if (fileName.contains("..")) {
            throw new IllegalArgumentException("Invalid file path: " + fileName);
        }

        try {
            ville.setImagesVille(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException("Failed to process file upload", e);
        }

        ville.setName(name);
        ville.setDescription(description);
        villeRepository.save(ville);
    }
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
