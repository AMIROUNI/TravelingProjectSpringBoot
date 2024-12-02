package org.example.travlingprojetsb.Controller;

import org.example.travlingprojetsb.Entity.Hotel;
import org.example.travlingprojetsb.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class HotelController {

    @Autowired
    private HotelService hotelService;

    private static String UPLOADED_FOLDER = "src/main/resources/static/images/";

    @RequestMapping("/addHotel")
    public String addHotel(Model model) {
        Hotel hotel = new Hotel();
        model.addAttribute("formHotel", hotel);
        return "new_hotel"; // View name for the "add hotel" form
    }

    @PostMapping("/saveHotel")
    public String saveHotel(@RequestParam("image") MultipartFile file,
                            @RequestParam("nomHotel") String nomHotel,
                            @RequestParam("description") String description,
                            @RequestParam("nombreEtoiles") int nombreEtoiles,
                            @RequestParam("latitude") double latitude,
                            @RequestParam("longitude") double longitude) {
        hotelService.saveHotelToDB(file, nomHotel, description, nombreEtoiles, latitude, longitude);
        return "redirect:/allHotels";
    }

    @RequestMapping("/allHotels")
    public String allhotels(Model model){
        List<Hotel> hotels = hotelService.findAllHotels();
        model.addAttribute("hotels",hotels);
        return "new_hotel";
    }

    @GetMapping("/deleteHotel/{id}")
    public String deleteHotelById(@PathVariable("id") Long id) {
        hotelService.deleteHotelById(id);
        return "redirect:/allHotels";
    }

    @GetMapping("/editHotel/{id}")
    public String showUpdatePage(@PathVariable("id") Long id, Model model) {
        Hotel hotel = hotelService.findHotelById(id);
        model.addAttribute("formUpdateHotel", hotel);
        return "update_hotel"; // View name for the "update hotel" form
    }
}
