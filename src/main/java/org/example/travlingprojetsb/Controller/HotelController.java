package org.example.travlingprojetsb.Controller;

import org.example.travlingprojetsb.Entity.Hotel;
import org.example.travlingprojetsb.Entity.User;
import org.example.travlingprojetsb.Repository.UserRepository;
import org.example.travlingprojetsb.Service.HotelService;
import org.example.travlingprojetsb.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/add")
    public String showAddHotelForm(Model model) {
        model.addAttribute("formHotel", new Hotel());
        List<Hotel> hotels = hotelService.findAllHotels();
        model.addAttribute("hotels", hotels);
        return "new_hotel";
    }

    @PostMapping("/saveHotel")
    public String saveHotel(@RequestParam("image") MultipartFile file,
                            @RequestParam("nomHotel") String nomHotel,
                            @RequestParam("description") String description,
                            @RequestParam("nombreEtoiles") int nombreEtoiles,
                            @RequestParam("latitude") String latitude,
                            @RequestParam("longitude") String longitude) {
        double lat = Double.parseDouble(latitude);
        double lon = Double.parseDouble(longitude);
        hotelService.saveHotelToDB(file, nomHotel, description, nombreEtoiles, lat, lon);
        return "redirect:/hotel/list";
    }

    @GetMapping("/list")
    public String showHotelList(Model model, @AuthenticationPrincipal UserDetails currentuser) {
        try {
            // Ensure the current user is not null and can be fetched from the database
            if (currentuser == null) {
                throw new IllegalArgumentException("Current user is not authenticated.");
            }

            User user = userRepository.findByEmail(currentuser.getUsername());

            if (user == null) {
                throw new IllegalStateException("User not found in the database.");
            }
            System.out.println(user.getEmail() + "/////////////////////////");

            List<Hotel> hotels = hotelService.findAllHotels();
            model.addAttribute("hotels", hotels);
            model.addAttribute("currentUser", user);
            return "new_hotel";
        } catch (IllegalArgumentException | IllegalStateException e) {
            model.addAttribute("error", e.getMessage());
            return "error_page"; // Replace with your actual error page template name
        }
    }
//@GetMapping("/list")
//public String showHotelList(Model model) {
//    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    System.out.println(user.getEmail()+"/////////////////////////");
//    List<Hotel> hotels = hotelService.findAllHotels();
//    model.addAttribute("hotels", hotels);
//    model.addAttribute("currentUser", user.getEmail());
//    return "new_hotel";
//}

    @GetMapping("/deleteHotel/{id}")
    public String deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotelById(id);
        return "redirect:/hotel/list";
    }

    @GetMapping("/updateHotel/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Hotel hotel = hotelService.findHotelById(id);
        model.addAttribute("hotel", hotel);
        return "updateHotel";
    }

    @PostMapping("/updateHotel/{id}")
    public String updateHotel(@PathVariable("id") long id, Hotel hotel, BindingResult result, Model model) {

        hotelService.updateHotel(hotel);
        return "redirect:/hotel/list";
    }

    @GetMapping()
    public String showHotelPage(Model model, @AuthenticationPrincipal UserDetails currentuser) {
        try {
            // Ensure the current user is not null and can be fetched from the database
            if (currentuser == null) {
                throw new IllegalArgumentException("Current user is not authenticated.");
            }

            User user = userRepository.findByEmail(currentuser.getUsername());

            if (user == null) {
                throw new IllegalStateException("User not found in the database.");
            }
            System.out.println(user.getEmail() + "/////////////////////////");
            model.addAttribute("currentUser", user);
            return "new_hotel";  // This returns the new_hotel.html page
        } catch (IllegalArgumentException | IllegalStateException e) {
            model.addAttribute("error", e.getMessage());
            return "error_page"; // Replace with your actual error page template name
        }

        }
    }
