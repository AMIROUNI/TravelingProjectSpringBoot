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
@RequestMapping("/villes")
public class VilleController {

    @Autowired
    private VilleService villeService;

    @GetMapping
    public String showVillesPage(Model model) {
        model.addAttribute("formVille", new Ville());
        List<Ville> villes = villeService.findAllVilles();
        model.addAttribute("villes", villes);
        return "new_ville";
    }

    @PostMapping("/saveVille")
    public String saveVille(@RequestParam("file") MultipartFile file,
                            @RequestParam("name") String name,
                            @RequestParam("description") String description) {
        villeService.saveVilleToDB(file, name, description);
        return "redirect:/villes";
    }

    @GetMapping("/deleteVille/{id}")
    public String deleteVille(@PathVariable("id") Long id) {
        villeService.deleteVilleById(id);
        return "redirect:/villes";
    }

    @GetMapping("/editVille/{id}")
    public String showEditVillePage(@PathVariable("id") Long id, Model model) {
        Ville ville = villeService.findVilleById(id);
        model.addAttribute("updateVille", ville);
        return "update_ville";
    }

    @PostMapping("/updateVille/{id}")
    public String updateVille(@PathVariable("id") Long id,
                              @ModelAttribute("updateVille") Ville ville) {
        villeService.updateVille(id, ville);
        return "redirect:/villes";
    }
}
