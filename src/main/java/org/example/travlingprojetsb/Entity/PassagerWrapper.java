package org.example.travlingprojetsb.Entity;

import org.example.travlingprojetsb.Entity.Passager;

import java.util.ArrayList;
import java.util.List;

public class PassagerWrapper {
    private List<Passager> passagers = new ArrayList<>();

    // Getter et Setter
    public List<Passager> getPassagers() {
        return passagers;
    }

    public void setPassagers(List<Passager> passagers) {
        this.passagers = passagers;
    }
}
