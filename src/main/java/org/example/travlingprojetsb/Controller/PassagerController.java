package Controller;

import Entity.Passager;
import Service.PassagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PassagerController {

    @Autowired
    private PassagerService passagerService;


    @RequestMapping("/addPassager")
    public String addPassager(Model model) {
        Passager passager = new Passager();
        model.addAttribute("formPassager", passager);
        return "new_passager"; // View name for the "add Passager" form
    }


    @RequestMapping("/savePassager")
    public String savePassager(@ModelAttribute("formPassager") Passager passager) {
        passagerService.addPassager(passager);
        return "redirect:/allPassagers";
    }

    @GetMapping("/deletePassager/{id}")
    public String deletePassagerById(@PathVariable("id") Long id) {
        passagerService.deletePassagerById(id);
        return "redirect:/allPassagers";
    }


    @GetMapping("/editPassager/{id}")
    public String showUpdatePage(@PathVariable("id") Long id, Model model) {
        Passager passager = passagerService.findPassagerById(id);
        model.addAttribute("formUpdatePassager", passager);
        return "update_passager"; // View name for the "update Passager" form
    }


    @RequestMapping("/allPassagers")
    public String listAllPassagers(Model model) {
        model.addAttribute("passagers", passagerService.findAllPassagers());
        return "list_passagers"; // View name for displaying all Passagers
    }
}
