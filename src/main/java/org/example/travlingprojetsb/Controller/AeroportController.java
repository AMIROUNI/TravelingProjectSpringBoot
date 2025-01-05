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

    @RequestMapping("/saveAeroport")
    public String saveAeroport(@ModelAttribute("formAeroport") Aeroport aeroport, @RequestParam("ville") Long villeId) {
        // Fetch the Ville entity using the ID
        Ville ville = villeService.findVilleById(villeId);
        aeroport.setVille(ville); // Set the Ville in the Aeroport object

        // Save the Aeroport
        aeroportService.addAeroport(aeroport);

        return "redirect:/aeroport"; // Redirect to the list of all airports after saving
    }


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
//    @RequestMapping("/saveAeroport")
//    public String saveAeroport(@ModelAttribute("formAeroport") Aeroport aeroport) {
//        //aeroport.setVille(ville);
//        aeroportService.addAeroport(aeroport);
//        return "redirect:/aeroport/allAeroports"; // Redirect to the list of all airports after saving
//    }

    @GetMapping("/deleteAeroport/{id}")
    public String deleteAeroportById(@PathVariable("id") Long id) {
        aeroportService.deleteAeroportById(id);
        return "redirect:/aeroport";
    }

    @GetMapping("/updateAeroport/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Aeroport aeroport = aeroportService.findAeroportById(id);
        model.addAttribute("aeroport", aeroport);
        List<Ville> villes =villeService.findAllVilles();
        model.addAttribute("villes",villes);
        return "update_aeroport";
    }

    @PostMapping("/updateAeroport/{id}")
    public String updateAerorport(@PathVariable("id") long id, Aeroport aeroport,@RequestParam("ville") Long villeId) {
        Ville ville = villeService.findVilleById(villeId);
        aeroport.setVille(ville);
        aeroportService.updateAeroport(aeroport);        return "redirect:/aeroport";
    }




    @RequestMapping()
    public String listAllAeroports(Model model) {
        model.addAttribute("aeroports", aeroportService.findAllAeroports());


        //List<Ville> villes = villeService.findAllVilles();
        //model.addAttribute("villes", villes);
        return "new_aeroport"; // View name for displaying all Aeroports


    }
}
