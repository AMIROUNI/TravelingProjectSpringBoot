package org.example.travlingprojetsb.Service;

import org.example.travlingprojetsb.Entity.Reservation;
import org.example.travlingprojetsb.Enum.EtatDeReservation;
import org.example.travlingprojetsb.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;


    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public void deleteReservationById(Long id) {
        reservationRepository.deleteById(id);
    }


    public Reservation findReservationById(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isPresent()) {
            return reservation.get();
        } else {
            throw new RuntimeException("Reservation not found with ID: " + id);
        }
    }


    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll();
    }


    public Reservation updateReservationState(Long id, EtatDeReservation etat) {
        Reservation reservation = findReservationById(id);
        reservation.setEtat(etat);
        return reservationRepository.save(reservation);
    }

    public void updateReservation(Reservation reservation) {
        reservationRepository.saveAndFlush(reservation);
    }
}
