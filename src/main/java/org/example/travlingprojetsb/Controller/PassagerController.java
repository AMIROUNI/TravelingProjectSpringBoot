package org.example.travlingprojetsb.Controller;

import org.example.travlingprojetsb.Entity.Passager;
import org.example.travlingprojetsb.Entity.Reservation;
import org.example.travlingprojetsb.Service.PassagerService;
import org.example.travlingprojetsb.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/passagers")
public class PassagerController {

    @Autowired
    private PassagerService passagerService;

    @Autowired
    private ReservationService reservationService;

    /**
     * Displays the form for adding a new passenger and the list of all passengers on the same page.
     */
//    @GetMapping("/add/{id}")
//    public String showPassagersPage(Model model,@PathVariable("id")long id_reservation) {
//
//        model.addAttribute("formPassager", new Passager()); // Empty object for the form
//        List<Passager> passagers = passagerService.findAllPassagers(); // List of passengers
//        model.addAttribute("passagers", passagers); // Add the list to the model
//        model.addAttribute("reservation",reservationService.findReservationById(id_reservation));
//        return "new_passager"; // Name of the HTML view
//    }

    /**
     * Saves a new passenger.
     */
//    @PostMapping("/savePassager")
//    public String savePassager(@RequestParam("reservationId") Long reservationId, @ModelAttribute("formPassager") Passager passager) {
//        Reservation reservation = reservationService.findReservationById(reservationId);
//        passager.setReservation(reservation); // Set the associated reservation
//        passagerService.addPassager(passager); // Save the passenger
//        return "redirect:/passagers"; // Redirect to the list of passengers or to another page
//    }
    @PostMapping("/savePassager")
    public String savePassager(@RequestParam(value = "reservationId", required = false) Long reservationId,
                               @ModelAttribute("formPassager") Passager passager) {
        passager.setReservation(reservationService.findReservationById(reservationId));
        passagerService.addPassager(passager); // Save the passenger
        return "redirect:/passagers/all/" + reservationId; // Redirect to the list of passengers or to another page
    }



//    @RequestMapping("/all/{id}")
//    public String allPassager(Model model, @PathVariable("id") Long reservationId) {
//        model.addAttribute("passagers", passagerService.findByIdReservation(reservationId));
//        model.addAttribute("reservationId", reservationId); // Include the reservation ID in the model
//        return "new_passager"; // Redirect to a dedicated page for displaying the passenger list
//    }

    @RequestMapping("/all/{id}")
    public String allPassager(Model model, @PathVariable("id") Long reservationId) {
        // Retrieve passengers for the specific reservation
        List<Passager> passagers = passagerService.findByIdReservation(reservationId);

        // Populate the model with passengers, reservation, and an empty form
        model.addAttribute("passagers", passagers);
        model.addAttribute("reservation", reservationService.findReservationById(reservationId));
        model.addAttribute("formPassager", new Passager()); // Form for adding a new passenger

        // Use the same page for displaying the form and the list
        return "new_passager";
    }

    /**
     * Deletes a passenger by their ID.
     */
    @GetMapping("/deletePassager/{reservationId}/{id}")
    public String deletePassager(@PathVariable("reservationId")Long reservationId, @PathVariable("id") Long id) {
        passagerService.deletePassagerById(id); // Delete the passenger
        return "redirect:/passagers/all/"+ reservationId; // Redirect to the main page
    }

    /**
     * Pre-fills the form to edit an existing passenger.
     */
    @GetMapping("/editPassager/{reservationId}/{id}")
    public String showEditPassagerPage(
            @PathVariable("reservationId") Long reservationId,
            @PathVariable("id") Long id,
            Model model) {
        Passager passager = passagerService.findPassagerById(id);
        Reservation reservation = reservationService.findReservationById(reservationId);

        model.addAttribute("updatePassager", passager);
        model.addAttribute("reservation", reservation); // Add reservation to the model

        return "update_passager"; // Name of the Thymeleaf template
    }


    /**
     * Updates an existing passenger.
     */
    @PostMapping("/updatePassager/{reservationId}/{id}")
    public String updatePassager(
            @PathVariable("reservationId") Long reservationId,
            @PathVariable("id") Long id,
            @ModelAttribute("updatePassager") Passager passager) {
        passager.setId(id); // Ensure the ID is set
        passager.setReservation(reservationService.findReservationById(reservationId));
        passagerService.updatePassager(passager); // Update the passenger using the service

        return "redirect:/passagers/all/" + reservationId; // Redirect to the main page
    }

}
