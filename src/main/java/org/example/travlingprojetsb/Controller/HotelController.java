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

    @RequestMapping("/saveHotel")
    public String saveHotel(@ModelAttribute("formHotel") Hotel hotel, @RequestParam("image") MultipartFile image) {
        if (!image.isEmpty()) {
            try {
                byte[] bytes = image.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + image.getOriginalFilename());
                Files.write(path, bytes);
                hotel.setImage(image.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        hotelService.addHotel(hotel);
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
