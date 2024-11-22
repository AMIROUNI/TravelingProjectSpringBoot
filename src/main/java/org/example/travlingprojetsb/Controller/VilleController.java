package Controller;

import Entity.Ville;
import Service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VilleController {

    @Autowired
    private VilleService villeService;

    @RequestMapping("/addVille")
    public String addVille(Model model) {
        Ville ville = new Ville();
        model.addAttribute("formVille", ville);
        return "new_ville"; // View name for the "add ville" form
    }

    @RequestMapping("/saveVille")
    public String saveVille(@ModelAttribute("formVille") Ville ville) {
        villeService.addVille(ville);
        return "redirect:/allVilles";
    }

    @GetMapping("/deleteVille/{id}")
    public String deleteVilleById(@PathVariable("id") Long id) {
        villeService.deleteVilleById(id);
        return "redirect:/allVilles";
    }

    @GetMapping("/editVille/{id}")
    public String showUpdatePage(@PathVariable("id") Long id, Model model) {
        Ville ville = villeService.findVilleById(id);
        model.addAttribute("formUpdateVille", ville);
        return "update_ville"; // View name for the "update ville" form
    }

    @RequestMapping("/allVilles")
    public String listAllVilles(Model model) {
        model.addAttribute("villes", villeService.findAllVilles());
        return "list_villes"; // View name for displaying all villes
    }

    @RequestMapping("/updateVille/{id}")
    public String updateVille(@PathVariable("id") Long id, @ModelAttribute("formUpdateVille") Ville ville) {
        villeService.updateVille(id, ville);
        return "redirect:/allVilles";
    }
}
