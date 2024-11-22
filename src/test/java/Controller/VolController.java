package Controller;

import Entity.Vol;
import Service.VolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VolController {
    @Autowired
    VolService volService;
    @RequestMapping("/addVol")
    public  String addVol(Model model){
        Vol vol =new Vol();
        model.addAttribute("FromVol",vol);
        return  "new_vol";

    }

    @RequestMapping("/save")
    public String saveVol(@ModelAttribute("FromVol") Vol vol)
    {
        volService.addVol(vol);
        return "redirect:/allVol";

    }



    @GetMapping("/delete/{id}")
    public  String deleteVolById(@PathVariable("id") Long id){
        volService.deleteVolById(id);
        return "redirect:/allVol";
    }


    @GetMapping("/edit/{id}")
    public  String ShowUpdatePage(@PathVariable("id") Long id,Model model ){
        Vol vol =volService.findVolById(id);
        model.addAttribute("FromUpdateVol",vol);
        return  "update_vol";
    }




}
