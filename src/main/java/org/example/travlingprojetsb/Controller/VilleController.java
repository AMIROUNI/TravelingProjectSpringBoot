package org.example.travlingprojetsb.Controller;

import org.example.travlingprojetsb.Entity.Ville;
import org.example.travlingprojetsb.Service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/ville")
public class VilleController {

    @Autowired
    private VilleService villeService;

    // Affichage du formulaire d'ajout d'une ville
    @GetMapping("/add")
    public String showAddVilleForm(Model model) {
        model.addAttribute("formVille", new Ville());
        List<Ville> villes = villeService.findAllVilles();
        model.addAttribute("villes", villes);
        return "new_ville";
    }

    // Enregistrement d'une nouvelle ville
    @PostMapping("/saveVille")
    public String saveVille(@RequestParam("file") MultipartFile file,
                            @RequestParam("name") String name,
                            @RequestParam("description") String description) {
        villeService.saveVilleToDB(file, name, description);
        return "redirect:/ville/list";
    }

    // Affichage de la liste des villes
    @GetMapping("/list")
    public String showVilleList(Model model) {
        List<Ville> villes = villeService.findAllVilles();
        model.addAttribute("villes", villes);
        return "new_ville";
    }

    // Suppression d'une ville
    @GetMapping("/deleteVille/{id}")
    public String deleteVille(@PathVariable Long id) {
        villeService.deleteVilleById(id);
        return "redirect:/ville/list";
    }
}
