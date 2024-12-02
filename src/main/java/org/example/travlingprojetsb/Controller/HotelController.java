package org.example.travlingprojetsb.Controller;

import org.example.travlingprojetsb.Entity.Hotel;
import org.example.travlingprojetsb.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String showHotelList(Model model) {
        List<Hotel> hotels = hotelService.findAllHotels();
        model.addAttribute("hotels", hotels);
        return "new_hotel";
    }

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
}
