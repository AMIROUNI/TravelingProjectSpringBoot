package org.example.travlingprojetsb.Controller;

import org.example.travlingprojetsb.Entity.Packe;
import org.example.travlingprojetsb.Service.PackeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    PackeService packeService;
    @GetMapping("/")
    public String showIndex(Model model) {
        List<Packe> packes = packeService.findAllPackes(); // Fetch all Packes from the service
        model.addAttribute("packes", packes); // Add the list to the model
        return "index"; // The view name for your index page
    }
}
