package org.example.travlingprojetsb.Service;

import org.example.travlingprojetsb.Entity.Passager;
import org.example.travlingprojetsb.Repository.PassagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassagerService {

    @Autowired
    private PassagerRepository passagerRepository;

    public Passager addPassager(Passager passager) {
        return passagerRepository.save(passager);
    }

    public void deletePassagerById(Long id) {
        passagerRepository.deleteById(id);
    }


    public Passager findPassagerById(Long id) {
        Optional<Passager> passager = passagerRepository.findById(id);
        if (passager.isPresent()) {
            return passager.get();
        } else {
            throw new RuntimeException("Passager not found with ID: " + id);
        }
    }
public List<Passager> findByIdReservation(Long id_reservation){
        return passagerRepository.findByIdReservation(id_reservation);
}

    public List<Passager> findAllPassagers() {
        return passagerRepository.findAll();
    }


    public Passager updatePassager(Passager passager) {
        if (passagerRepository.existsById(passager.getId())) {
            return passagerRepository.save(passager);
        } else {
            throw new RuntimeException("Cannot update Passager with ID: " + passager.getId() + ", it does not exist.");
        }
    }

    public void deleteByReservationId(Long id) {
        passagerRepository.deleteByReservationId(id);
    }
}
