package org.example.travlingprojetsb.Controller;

import org.example.travlingprojetsb.Entity.Client;
import org.example.travlingprojetsb.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    /**
     * Affiche le formulaire d'ajout et la liste des clients dans la même page.
     */
    @GetMapping
    public String showClientsPage(Model model) {
        model.addAttribute("formClient", new Client()); // Objet vide pour le formulaire
        List<Client> clients = clientService.findAllClients(); // Liste des clients
        model.addAttribute("clients", clients); // Ajout de la liste au modèle
        return "new_client"; // Nom de la vue HTML
    }

    /**
     * Enregistre un nouveau client.
     */
    @PostMapping("/saveClient")
    public String saveClient(@ModelAttribute("formClient") Client client) {
        clientService.addClient(client); // Sauvegarde le client
        return "redirect:/clients"; // Redirection vers la page principale
    }

    /**
     * Supprime un client par son ID.
     */
    @GetMapping("/deleteClient/{id}")
    public String deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClientById(id); // Supprime le client
        return "redirect:/clients"; // Redirection vers la page principale
    }

    /**
     * Préremplit le formulaire pour modifier un client existant.
     */
    @GetMapping("/editClient/{id}")
    public String showEditClientPage(@PathVariable("id") Long id, Model model) {
        Client client = clientService.findClientById(id); // Recherche du
        model.addAttribute("updateClient",client);
        return  "update_client";
    }


    @PostMapping("/updateClient/{id}")
    public String update(@PathVariable("id") Long id,@ModelAttribute("updateClient") Client client) {
      clientService.updateClinet(client);
      return  "redirect:/clients";


    }



}
