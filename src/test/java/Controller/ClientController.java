package Controller;

import Entity.Client;
import Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClientController {
    @Autowired
    ClientService clientService;
    @RequestMapping("/addClient")
    public  String addClient(Model model){
        Client client =new Client();
       model.addAttribute("FromClient",client);
       return  "new_client";

    }

    @RequestMapping("/save")
    public String saveClient(@ModelAttribute("FromClient") Client client)
    {
        clientService.addClient(client);
        return "redirect:/allClient";

    }



    @GetMapping("/delete/{id}")
    public  String deleteClientById(@PathVariable("id") Long id){
        clientService.deleteClientById(id);
        return "redirect:/allCient";
    }


    @GetMapping("/edit/{id}")
    public  String ShowUpdatePage(@PathVariable("id") Long id,Model model ){
        Client client =clientService.findClientById(id);
        model.addAttribute("FromUpdateCliet",client);
        return  "update_client";
    }




}
