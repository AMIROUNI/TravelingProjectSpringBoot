package org.example.travlingprojetsb.Controller;

import org.example.travlingprojetsb.Entity.Aeroport;
import org.example.travlingprojetsb.Service.AeroportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AeroportController {

    @Autowired
    private AeroportService aeroportService;


    @RequestMapping("/addAeroport")
    public String addAeroport(Model model) {
        Aeroport aeroport = new Aeroport();
        model.addAttribute("formAeroport", aeroport);
        return "new_aeroport"; // View name for the "add aeroport" form
    }


    @RequestMapping("/saveAeroport")
    public String saveAeroport(@ModelAttribute("formAeroport") Aeroport aeroport) {
        aeroportService.addAeroport(aeroport);
        return "redirect:/allAeroports";
    }


    @GetMapping("/deleteAeroport/{id}")
    public String deleteAeroportById(@PathVariable("id") Long id) {
        aeroportService.deleteAeroportById(id);
        return "redirect:/allAeroports";
    }


    @GetMapping("/editAeroport/{id}")
    public String showUpdatePage(@PathVariable("id") Long id, Model model) {
        Aeroport aeroport = aeroportService.findAeroportById(id);
        model.addAttribute("formUpdateAeroport", aeroport);
        return "update_aeroport"; // View name for the "update aeroport" form
    }


    @RequestMapping("/allAeroports")
    public String listAllAeroports(Model model) {
        model.addAttribute("aeroports", aeroportService.findAllAeroports());
        return "list_aeroports"; // View name for displaying all Aeroports
    }
}
