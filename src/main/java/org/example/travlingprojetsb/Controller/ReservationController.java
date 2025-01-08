package org.example.travlingprojetsb.Controller;

import org.example.travlingprojetsb.Entity.*;
import org.example.travlingprojetsb.Enum.EtatDeReservation;
import org.example.travlingprojetsb.Repository.UserRepository;
import org.example.travlingprojetsb.Service.PackeService;
import org.example.travlingprojetsb.Service.PassagerService;
import org.example.travlingprojetsb.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private PackeService packeService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PassagerService passagerService;

    @GetMapping("/all")
    public String listAllReservations(Model model) {
        model.addAttribute("reservations", reservationService.findAllReservations());
        model.addAttribute("clients", userRepository.findAll());
        model.addAttribute("packages", packeService.findAllPackes());
        return "new_reservation"; // Correspond au template HTML pour afficher toutes les réservations
    }

    @PostMapping("/saveReservation")
    public String saveReservation(
            @RequestParam("clientId") Long clientId,
            @RequestParam("packageId") Long packageId,
            @RequestParam("dateReservation") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateReservation,
            @RequestParam("statut") EtatDeReservation statut) {

        // Vérification du client
        Optional<User> optionalUser = userRepository.findById(clientId);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("Le client avec l'ID " + clientId + " n'existe pas.");
        }

        // Vérification du pack
        Packe packe = packeService.findPackeById(packageId);
        if (packe == null) {
            throw new IllegalArgumentException("Le pack avec l'ID " + packageId + " n'existe pas.");
        }

        // Création de la réservation
        Reservation reservation = new Reservation();
        reservation.setUser(optionalUser.get());
        reservation.setPacke(packe);
        reservation.setDateDeResrvation(dateReservation);
        reservation.setEtat(statut);

        // Enregistrement de la réservation
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
        model.addAttribute("clients", userRepository.findAll());
        model.addAttribute("packages", packeService.findAllPackes());
        return "update_reservation"; // Correspond au template HTML pour modifier une réservation
    }

    @PostMapping("/update/{id}")
    public String updateReservation(
            @PathVariable("id") Long id,
            @RequestParam("clientId") Long clientId,
            @RequestParam("packageId") Long packageId,
            @RequestParam("dateReservation") LocalDate dateReservation,
            @RequestParam("statut") EtatDeReservation statut) {

        Optional<User> optionalClient = userRepository.findById(clientId);
        if (optionalClient.isEmpty()) {
            throw new IllegalArgumentException("Le client avec l'ID " + clientId + " n'existe pas.");
        }

        Packe packe = packeService.findPackeById(packageId);
        if (packe == null) {
            throw new IllegalArgumentException("Le pack avec l'ID " + packageId + " n'existe pas.");
        }

        Reservation reservation = reservationService.findReservationById(id);
        reservation.setUser(optionalClient.get());
        reservation.setPacke(packe);
        reservation.setDateDeResrvation(dateReservation);
        reservation.setEtat(statut);

        reservationService.updateReservation(reservation);
        return "redirect:/reservations/all";
    }

    @GetMapping("/add-passager/{id}")
    public String addPassagerFromReservation(@PathVariable("id") Long idReservation, Model model) {
        model.addAttribute("reservation", reservationService.findReservationById(idReservation));
        model.addAttribute("formPassager", new Passager());
        return "new_passager"; // Correspond au template HTML pour ajouter un passager
    }

    @GetMapping("/userDoReseravation/{id}")
    public String reservation(@PathVariable("id") Long idPack, Model model, @AuthenticationPrincipal UserDetails currentUser) {

        if (currentUser == null) {
            return "redirect:/login";
        }

        User user = userRepository.findByEmail(currentUser.getUsername());
        if (user == null) {
            throw new IllegalStateException("Utilisateur non trouvé dans la base de données.");
        }

        Packe packe = packeService.findPackeById(idPack);
        if (packe == null) {
            throw new IllegalStateException("Packe non trouvé dans la base de données.");
        }

        Reservation reservation = new Reservation();
        model.addAttribute("id_client", user.getId());
        model.addAttribute("id_packet", idPack);
        model.addAttribute("Formreservation", reservation);
        return "reservation_client"; // Correspond au template HTML pour les réservations du client
    }


    @PostMapping("/saveClientReservation")
    public String saveClientReservation(
            @ModelAttribute("Formreservation") Reservation reservation,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateDeResrvation,
            @ModelAttribute("passagers") PassagerWrapper passagerWrapper,
            @RequestParam Long id_client,
            @RequestParam Long id_packet) {

        // Vérification du client
        Optional<User> client = userRepository.findById(id_client);
        if (client.isEmpty()) {
            throw new IllegalArgumentException("Le client avec l'ID " + id_client + " n'existe pas.");
        }

        // Vérification du pack
        Packe packe = packeService.findPackeById(id_packet);
        if (packe == null) {
            throw new IllegalArgumentException("Le pack avec l'ID " + id_packet + " n'existe pas.");
        }

        // Configuration des informations de la réservation
        reservation.setUser(client.get());
        reservation.setPacke(packe);
        reservation.setDateDeResrvation(dateDeResrvation);

        // Sauvegarde initiale de la réservation
        reservationService.addReservation(reservation);

        // Ajout des passagers à la réservation
        for (Passager p : passagerWrapper.getPassagers()) {
            p.setReservation(reservation); // Liaison avec la réservation persistée
            passagerService.addPassager(p); // Sauvegarde du passager
        }

        return "redirect:/";
    }

////////////// showing the reservation made by the current user
    @GetMapping("/userReservation")
    public String userReservation(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        if (currentUser == null) {
            return "redirect:/login";
        }
        User user = userRepository.findByEmail(currentUser.getUsername());
        model.addAttribute("id_client", user.getId());
        List<Reservation> reservation = reservationService.findReservationByUserId(user.getId());

        model.addAttribute("reservation", reservation);
        for (Reservation r : reservation) {
            List<Passager> passagers = passagerService.findByIdReservation(r.getId());
            model.addAttribute("passagers", passagers);
        }

        return "client_reservation";
    }


    @GetMapping("/cancel/{id}")
    public String cancelReservation(@PathVariable Long id, @AuthenticationPrincipal UserDetails currentUser) {
        if (currentUser == null) {
            return "redirect:/login";
        }

        // Get the current user
        User user = userRepository.findByEmail(currentUser.getUsername());
        if (user == null) {
            throw new IllegalStateException("Utilisateur non trouvé dans la base de données.");
        }

        // Find the reservation
        Reservation reservation = reservationService.findReservationById(id);
        if (reservation == null || !reservation.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("La réservation n'existe pas ou n'appartient pas à l'utilisateur.");
        }

        // Update the status to "CANCELED" if it's "PENDING"
        if (reservation.getEtat() == EtatDeReservation.EN_ATTENTE) {
            reservation.setEtat(EtatDeReservation.ANNULEE);
            reservationService.updateReservation(reservation);
        }

        return "redirect:/reservations/userReservation";
    }



////////////////////////////////////////////

  /*  @GetMapping("/edit/{id}")
    public String editReservationForm(@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetails currentUser) {
        if (currentUser == null) {
            return "redirect:/login";
        }

        // Find reservation by ID
        Reservation reservation = reservationService.findReservationById(id);

        if (reservation == null) {
            return "redirect:/userReservation"; // Redirect if reservation not found
        }

        // Check if the current user owns the reservation
        User user = userRepository.findByEmail(currentUser.getUsername());
        if (!reservation.getUser().getId().equals(user.getId())) {
            return "redirect:/userReservation"; // Redirect if the user doesn't own the reservation
        }

        // Populate the model
        model.addAttribute("reservation", reservation);
        model.addAttribute("passagers", passagerService.findByIdReservation(id));

        return "update_user_reservation";
    }

    @PostMapping("/updateClientReservation")
    public String updateClientReservation(
            @ModelAttribute("Formreservation") Reservation updatedReservation,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateDeResrvation,
            @ModelAttribute("passagers") PassagerWrapper passagerWrapper) {

        // Récupération de la réservation existante
        Reservation existingReservation = reservationService.findReservationById(updatedReservation.getId());
        if (existingReservation == null) {
            throw new IllegalArgumentException("La réservation avec l'ID " + updatedReservation.getId() + " n'existe pas.");
        }

        // Mise à jour des informations de la réservation (sans modification de Packe)
        existingReservation.setDateDeResrvation(dateDeResrvation);
        existingReservation.setEtat(updatedReservation.getEtat());

        // Mise à jour de la réservation
        reservationService.updateReservation(existingReservation);

        // Mise à jour des passagers
        passagerService.deleteByReservationId(existingReservation.getId()); // Supprimer les passagers existants
        for (Passager p : passagerWrapper.getPassagers()) {
            p.setReservation(existingReservation); // Réassocier les passagers
            passagerService.addPassager(p); // Ajouter les nouveaux passagers
        }

        return "redirect:/userReservation";
    }*/
}





