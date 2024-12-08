package org.example.travlingprojetsb.Controller;

import org.example.travlingprojetsb.Entity.Escale;
import org.example.travlingprojetsb.Entity.Ville;
import org.example.travlingprojetsb.Entity.Vol;
import org.example.travlingprojetsb.Entity.Aeroport;
import org.example.travlingprojetsb.Service.EscaleService;
import org.example.travlingprojetsb.Service.VolService;
import org.example.travlingprojetsb.Service.AeroportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/vols")
public class VolController {

    @Autowired
    private VolService volService;

    @Autowired
    private AeroportService aeroportService;

    @Autowired
    private EscaleService escaleService;

    // Liste des vols
    @RequestMapping("/allVol")
    public String listVols(Model model) {
        model.addAttribute("vols", volService.findAllVols());

          List<Aeroport> aeroports = aeroportService.findAllAeroports();
           model.addAttribute("aeroports", aeroports);
        return "new_vol"; // Vue pour afficher les vols
    }

    // Formulaire pour ajouter un vol
    @RequestMapping("/add")
    public String addVol(Model model) {
        Vol vol = new Vol();
        model.addAttribute("formVol", vol);
        List<Aeroport> aeroports = aeroportService.findAllAeroports();
        model.addAttribute("aeroports", aeroports);
        return "new_vol"; // Vue pour ajouter un vol
    }

    // Enregistrer un nouveau vol
    @RequestMapping("/saveVol")
    public String saveVol(@ModelAttribute("formVol") Vol vol, @RequestParam("aeroportDepart") Long departAeroportId, @RequestParam("aeroportArrivee") Long arriveeAeroportId) {
        // Affecter les aéroports de départ et d'arrivée
        Aeroport departAeroport = aeroportService.findAeroportById(departAeroportId);
        Aeroport arriveeAeroport = aeroportService.findAeroportById(arriveeAeroportId);
        vol.setAeroportDepart(departAeroport);
        vol.setAeroportArrivee(arriveeAeroport);

        volService.saveVol(vol); // Enregistrer le vol
        return "redirect:/vols/allVol"; // Rediriger vers la liste des vols
    }

    // Supprimer un vol
    @RequestMapping("/deleteVol/{id}")
    public String deleteVol(@PathVariable Long id) {
        volService.deleteVol(id);
        return "redirect:/vols/allVol"; // Rediriger après suppression
    }

    // Formulaire pour mettre à jour un vol
    @GetMapping("/updateVol/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Vol vol = volService.findVolById(id);
        model.addAttribute("formVol", vol);
        List<Aeroport> aeroports = aeroportService.findAllAeroports();
        model.addAttribute("aeroports", aeroports);
        return "update_vol"; // Vue pour la mise à jour d’un vol
    }

    // Mettre à jour un vol
    @PostMapping("/updateVol/{id}")
    public String updateVol(@PathVariable("id") long id, Vol vol, @RequestParam("departAeroport") Long departAeroportId, @RequestParam("arriveeAeroport") Long arriveeAeroportId) {
        // Affecter les nouveaux aéroports de départ et d'arrivée
        Aeroport departAeroport = aeroportService.findAeroportById(departAeroportId);
        Aeroport arriveeAeroport = aeroportService.findAeroportById(arriveeAeroportId);
        vol.setAeroportDepart(departAeroport);
        vol.setAeroportArrivee(arriveeAeroport);

        volService.updateVol(vol); // Mettre à jour le vol
        return "redirect:/vols/allVol"; // Rediriger vers la liste des vols
    }




/*
    @GetMapping("/escales/form/{volId}")
    public String showEscaleForm(@PathVariable("volId") Long volId, Model model) {
        // Récupérer la liste des aéroports
        List<Aeroport> aeroports = aeroportService.findAllAeroports();

        // Ajouter le vol et les aéroports au modèle
        model.addAttribute("volId", volId);
        model.addAttribute("aeroports", aeroports);

        return "escaleForm"; // Nom de la vue qui affichera le formulaire (par exemple, "escaleForm.html")
    }
    @PostMapping("/escales/save/{volId}")
    public String saveEscale(
            @PathVariable("volId") Long volId,
            @RequestParam("aeroportId") Long aeroportId,
            @RequestParam("heureArrivee") String heureArrivee,
            @RequestParam("heureDepart") String heureDepart) {

        // Récupérer le vol et l'aéroport
        Vol vol = volService.findVolById(volId);
        Aeroport aeroport = aeroportService.findAeroportById(aeroportId);

        // Créer une nouvelle escale
        Escale escale = new Escale();
        escale.setVol(vol);
        escale.setAeroport(aeroport);
        escale.setHeureArrivee(java.time.LocalTime.parse(heureArrivee));
        escale.setHeureDepart(java.time.LocalTime.parse(heureDepart));

        // Enregistrer l'escale
        escaleService.addEscale(escale);

        return "redirect:/vols/allVol"; // Rediriger vers la liste des vols
    }*/



    @PostMapping("/escales/save")
    public String saveEscale(
            @RequestParam("volId") Long volId,
            @RequestParam("aeroportId") Long aeroportId,
            @RequestParam("heureArrivee") String heureArrivee,
            @RequestParam("heureDepart") String heureDepart) {

        // Retrieve the flight and airport
        Vol vol = volService.findVolById(volId);
        Aeroport aeroport = aeroportService.findAeroportById(aeroportId);

        // Create a new escale
        Escale escale = new Escale();
        escale.setVol(vol);
        escale.setAeroport(aeroport);
        escale.setHeureArrivee(java.time.LocalTime.parse(heureArrivee));
        escale.setHeureDepart(java.time.LocalTime.parse(heureDepart));

        // Save the escale
        escaleService.addEscale(escale);

        return "redirect:/vols/allVol"; // Redirect to the list of flights
    }


    @GetMapping(value = "/escales/{volId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public List<Escale> getEscalesByVolId(@PathVariable("volId") Long volId) {
             List<Escale> escaleList=escaleService.findEscalesByVolId(volId);

        return escaleList;
    }


    @DeleteMapping("/escales/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteEscale(@PathVariable("id") Long id) {
        System.out.println("ID reçu pour suppression : " + id);
        try {
            escaleService.deleteEscale(id);
            return ResponseEntity.ok("Escale supprimée avec succès");
        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la suppression de l'escale : " + e.getMessage());
        }
    }dsklfjmdlskfjqdsklmjfmijezoijzoeiajremoizjkvnosdjlkjfsqiodujfoizeajoidsjfcoijdmklfjaeziomfjaoezljfiosjdcajkfnvcqlkjgpoazjrmoiazjnfiosdjqlkjfmoi

}
