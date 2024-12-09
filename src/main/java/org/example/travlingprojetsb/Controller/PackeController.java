package org.example.travlingprojetsb.Controller;

import org.example.travlingprojetsb.Entity.Packe;
import org.example.travlingprojetsb.Service.HotelService;
import org.example.travlingprojetsb.Service.PackeService;
import org.example.travlingprojetsb.Service.VolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/packe")
public class PackeController {

    @Autowired
    private PackeService packeService;
    @Autowired
    private VolService volService;
    @Autowired
    private HotelService hotelService;

    // Show form to add a new Packe
    @GetMapping("/add")
    public String showAddPackeForm(Model model) {
        model.addAttribute("formPacke", new Packe());
        model.addAttribute("vols", volService.findAllVols());
        model.addAttribute("hotels", hotelService.findAllHotels());
        return "new_packe";
    }

    // Save Packe with image
    @PostMapping("/save")
    public String savePacke(@RequestParam("image") MultipartFile file,
                            @RequestParam("nomPacke") String nomPacke,
                            @RequestParam("description") String description,
                            @RequestParam("prix") float prix,
                            @RequestParam("vols") List<Long> volsIds,
                            @RequestParam("hotels") List<Long> hotelsIds) {

        packeService.savePackeToDB(file, nomPacke, description, prix, volsIds, hotelsIds);
        return "redirect:/packe/list";
    }


    // List all Packes
    @GetMapping("/list")
    public String listAllPackes(Model model) {
        model.addAttribute("packes", packeService.findAllPackes());
        return "new_packe"; // Ensure this view exists
    }

    // Delete a Packe
    @GetMapping("/delete/{id}")
    public String deletePacke(@PathVariable("id") Long id) {
        packeService.deletePackeById(id);


        return "redirect:/packe/list";
    }

    // List all Packes
    @GetMapping("/list")
    public String listAllPackes(Model model) {
        model.addAttribute("packes", packeService.findAllPackes());
        model.addAttribute("vols", volService.findAllVols());
        model.addAttribute("hotels", hotelService.findAllHotels());
        return "new_packe"; // Ensure this view exists
    }


    // Show form to update a Packe
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Packe packe = packeService.findPackeById(id);
        model.addAttribute("formPacke", packe);
        model.addAttribute("vols", volService.findAllVols());
        model.addAttribute("hotels", hotelService.findAllHotels());
        return "update_packe";
    }


    // Update Packe
    @PostMapping("/update/{id}")
    public String updatePacke(@PathVariable("id") Long id,
                              @RequestParam("image") MultipartFile file,
                              @RequestParam("nomPacke") String nomPacke,
                              @RequestParam("description") String description,
                              @RequestParam("prix") float prix,
                              @RequestParam("vols") List<Long> volsIds,
                              @RequestParam("hotels") List<Long> hotelsIds) {
        packeService.updatePacke(id, file, nomPacke, description, prix, volsIds, hotelsIds);
        return "redirect:/packe/list";
    }
}
