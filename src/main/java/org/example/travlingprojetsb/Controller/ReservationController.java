package org.example.travlingprojetsb.Controller;

import org.example.travlingprojetsb.Entity.Client;
import org.example.travlingprojetsb.Entity.Packe;
import org.example.travlingprojetsb.Entity.Passager;
import org.example.travlingprojetsb.Entity.Reservation;
import org.example.travlingprojetsb.Enum.EtatDeReservation;
import org.example.travlingprojetsb.Service.ClientService;
import org.example.travlingprojetsb.Service.PackeService;
import org.example.travlingprojetsb.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private PackeService packeService;

    @GetMapping("/all")
    public String listAllReservations(Model model) {
        model.addAttribute("reservations", reservationService.findAllReservations());
        model.addAttribute("clients", clientService.findAllClients());
        model.addAttribute("packages", packeService.findAllPackes());
        return "new_reservation"; // Matches the provided template
    }

    @PostMapping("/saveReservation")
    public String saveReservation(
            @RequestParam("clientId") Long clientId,
            @RequestParam("packageId") Long packageId,
            @RequestParam("dateReservation") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateReservation,
            @RequestParam("statut") EtatDeReservation statut) {

        Reservation reservation = new Reservation();
        reservation.setClient(clientService.findClientById(clientId));
        reservation.setPacke(packeService.findPackeById(packageId));
        reservation.setDateDeResrvation(dateReservation); // Use proper field name from entity
        reservation.setEtat(statut);
        reservationService.addReservation(reservation);
        return "redirect:/reservations/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteReservationById(@PathVariable("id") Long id) {
        reservationService.deleteReservationById(id);
        return "redirect:/reservations/all";
    }

    @GetMapping("/update/{id}")
    public String showUpdatePage(@PathVariable("id") Long id, Model model) {
        Reservation reservation = reservationService.findReservationById(id);
        model.addAttribute("formUpdateReservation", reservation);
        model.addAttribute("clients", clientService.findAllClients());
        model.addAttribute("packages", packeService.findAllPackes());
        return "update_reservation"; // Requires an additional template for editing
    }

    @PostMapping("/update/{id}")
    public String updateReservation(
            @PathVariable("id") Long id,
            @RequestParam("clientId") Long clientId,
            @RequestParam("packageId") Long packageId,
            @RequestParam("dateReservation") LocalDate dateReservation,
            @RequestParam("statut") EtatDeReservation statut) {

        Reservation reservation = reservationService.findReservationById(id);
        reservation.setClient(clientService.findClientById(clientId));
        reservation.setPacke(packeService.findPackeById(packageId));
        reservation.setDateDeResrvation(dateReservation);
        reservation.setEtat(statut);
        reservationService.updateReservation(reservation);
        return "redirect:/reservations/all";
    }
    @GetMapping("/add-passager/{id}")
    public String addPassagerFromReservation(@PathVariable("id") Long id_reservation, Model model) {
        model.addAttribute("reservation", reservationService.findReservationById(id_reservation));
        model.addAttribute("formPassager", new Passager());
        return "new_passager"; // Redirect to the new_passager.html page
    }
}
