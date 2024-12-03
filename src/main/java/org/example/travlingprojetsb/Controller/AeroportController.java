package org.example.travlingprojetsb.Controller;

import org.example.travlingprojetsb.Entity.Aeroport;
import org.example.travlingprojetsb.Entity.Hotel;
import org.example.travlingprojetsb.Entity.Ville;
import org.example.travlingprojetsb.Service.AeroportService;
import org.example.travlingprojetsb.Service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/aeroport")
public class AeroportController {

    @Autowired
    private AeroportService aeroportService;

    @Autowired
    private VilleService villeService;

    // Show the add aeroport form
    @RequestMapping("/addAeroport")
    public String addAeroport(Model model) {
        Aeroport aeroport = new Aeroport();
        model.addAttribute("formAeroport", aeroport);

        // Get all villes from the VilleService
        List<Ville> villes = villeService.findAllVilles();
        model.addAttribute("villes", villes);

        return "new_aeroport"; // View name for the "add aeroport" form
    }

    // Save the new aeroport
    @RequestMapping("/saveAeroport")
    public String saveAeroport(@ModelAttribute("formAeroport") Aeroport aeroport) {
        aeroportService.addAeroport(aeroport);
        return "redirect:/aeroport/allAeroports"; // Redirect to the list of all airports after saving
    }

    @GetMapping("/aeroport/deleteAeroport/{id}")
    public String deleteAeroportById(@PathVariable("id") Long id) {
        aeroportService.deleteAeroportById(id);
        return "redirect:/allAeroports";
    }

    @GetMapping("/updateAeroport/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Aeroport aeroport = aeroportService.findAeroportById(id);
        model.addAttribute("aeroport", aeroport);
        return "update_aeroport";
    }

    @PostMapping("/updateAeroport/{id}")
    public String updateAerorport(@PathVariable("id") long id, Aeroport aeroport) {

        aeroportService.updateAeroport(aeroport);
        return "redirect:/aeroport/allAeroports";
    }




    @RequestMapping("/allAeroports")
    public String listAllAeroports(Model model) {
        model.addAttribute("aeroports", aeroportService.findAllAeroports());
        return "new_aeroport"; // View name for displaying all Aeroports
    }
}
