package org.example.travlingprojetsb.Controller;

import org.example.travlingprojetsb.Entity.Packe;
import Service.PackeService;
import org.example.travlingprojetsb.Entity.Packe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PackeController {

    @Autowired
    private PackeService packeService;


    @RequestMapping("/addPacke")
    public String addPacke(Model model) {
        Packe packe = new Packe();
        model.addAttribute("formPacke", packe);
        return "new_packe"; // View name for the "add Packe" form
    }


    @RequestMapping("/savePacke")
    public String savePacke(@ModelAttribute("formPacke") Packe packe) {
        packeService.addPacke(packe);
        return "redirect:/allPackes";
    }


    @GetMapping("/deletePacke/{id}")
    public String deletePackeById(@PathVariable("id") Long id) {
        packeService.deletePackeById(id);
        return "redirect:/allPackes";
    }


    @GetMapping("/editPacke/{id}")
    public String showUpdatePage(@PathVariable("id") Long id, Model model) {
        Packe packe = packeService.findPackeById(id);
        model.addAttribute("formUpdatePacke", packe);
        return "update_packe"; // View name for the "update Packe" form
    }


    @RequestMapping("/allPackes")
    public String listAllPackes(Model model) {
        model.addAttribute("packes", packeService.findAllPackes());
        return "list_packes"; // View name for displaying all Packes
    }
}
