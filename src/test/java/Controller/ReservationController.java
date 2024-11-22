package Controller;

import Entity.Reservation;
import Enum.EtatDeReservation;
import Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @RequestMapping("/addReservation")
    public String addReservation(Model model) {
        Reservation reservation = new Reservation();
        model.addAttribute("formReservation", reservation);
        return "new_reservation"; // View name for the "add reservation" form
    }

    @RequestMapping("/saveReservation")
    public String saveReservation(@ModelAttribute("formReservation") Reservation reservation) {
        reservationService.addReservation(reservation);
        return "redirect:/allReservations";
    }

    @GetMapping("/deleteReservation/{id}")
    public String deleteReservationById(@PathVariable("id") Long id) {
        reservationService.deleteReservationById(id);
        return "redirect:/allReservations";
    }

    @GetMapping("/editReservation/{id}")
    public String showUpdatePage(@PathVariable("id") Long id, Model model) {
        Reservation reservation = reservationService.findReservationById(id);
        model.addAttribute("formUpdateReservation", reservation);
        return "update_reservation"; // View name for the "update reservation" form
    }

    @RequestMapping("/allReservations")
    public String listAllReservations(Model model) {
        model.addAttribute("reservations", reservationService.findAllReservations());
        return "list_reservations"; // View name for displaying all reservations
    }

    @PostMapping("/updateReservationState/{id}")
    public String updateReservationState(@PathVariable("id") Long id, @RequestParam("etat") EtatDeReservation etat) {
        reservationService.updateReservationState(id, etat);
        return "redirect:/allReservations";
    }
}
