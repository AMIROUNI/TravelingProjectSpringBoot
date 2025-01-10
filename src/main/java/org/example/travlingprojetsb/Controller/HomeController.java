package org.example.travlingprojetsb.Controller;

import org.example.travlingprojetsb.Entity.Packe;
import org.example.travlingprojetsb.Entity.Role;
import org.example.travlingprojetsb.Entity.User;
import org.example.travlingprojetsb.Repository.UserRepository;
import org.example.travlingprojetsb.Service.PackeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    PackeService packeService;
    @Autowired
    UserRepository userRepository;
    @GetMapping("/")
    public String showIndex( Model model) {

//        if (currentUser == null) {
//            return "redirect:/login";
//        }
//
//        User user = userRepository.findByEmail(currentUser.getUsername());
//        List<Role> roles = user.getRoles();
//        for (Role role : roles) {System.out.println("//////////////"+role.toString());}
        List<Packe> packes = packeService.findAllPackes();
//        model.addAttribute("role",roles);
        model.addAttribute("packes", packes); // Add the list to the model
        return "index"; // The view name for your index page
    }
    @GetMapping("/home")
    public String showIndexAfterLogin(Model model, @AuthenticationPrincipal UserDetails currentUser) {

        if (currentUser == null) {
            return "redirect:/login";
        }

        User user = userRepository.findByEmail(currentUser.getUsername());
        List<Role> roles = user.getRoles();
        List<Packe> packes = packeService.findAllPackes();

        model.addAttribute("role", roles);
        model.addAttribute("packes", packes); // Add the list to the model
        model.addAttribute("userName", user.getName()); // Pass the user's first name

        return "index"; // The view name for your index page
    }

}
