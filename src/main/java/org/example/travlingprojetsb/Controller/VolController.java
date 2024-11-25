package org.example.travlingprojetsb.Controller;

import org.example.travlingprojetsb.Entity.Vol;
import org.example.travlingprojetsb.Service.VolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vols")
public class VolController {

    @Autowired
    private VolService volService;

    /**
     * Displays the page for adding a new flight and the list of all flights.
     */
    @GetMapping
    public String showVolsPage(Model model) {
        model.addAttribute("formVol", new Vol()); // Empty object for the form
        List<Vol> vols = volService.findAllVols(); // Fetch the list of flights
        model.addAttribute("vols", vols); // Add the list to the model
        return "new_vol"; // Name of the view for this page
    }






    /**
     * Saves a new flight.
     */
    @PostMapping("/saveVol")
    public String saveVol(@ModelAttribute("formVol") Vol vol) {
        volService.addVol(vol); // Save the flight
        return "redirect:/vols"; // Redirect to the main page
    }

    /**
     * Deletes a flight by its ID.
     */
    @GetMapping("/deleteVol/{id}")
    public String deleteVolById(@PathVariable("id") Long id) {
        volService.deleteVolById(id); // Delete the flight
        return "redirect:/vols"; // Redirect to the main page
    }

    /**
     * Displays the form to update an existing flight.
     */
    @GetMapping("/editVol/{id}")
    public String showEditVolPage(@PathVariable("id") Long id, Model model) {
        Vol vol = volService.findVolById(id); // Fetch the flight by ID
        model.addAttribute("updateVol", vol); // Add the flight to the model
        return "update_vol"; // Name of the view for editing
    }

    /**
     * Updates an existing flight.
     */
    @PostMapping("/updateVol/{id}")
    public String updateVol(@PathVariable("id") Long id, @ModelAttribute("updateVol") Vol vol) {
        volService.updateVol(vol); // Update the flight
        return "redirect:/vols"; // Redirect to the main page
    }
}
