package org.example.travlingprojetsb.Controller;

import org.example.travlingprojetsb.Entity.Hotel;
import org.example.travlingprojetsb.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HotelController {

    @Autowired
    private HotelService hotelService;


    @RequestMapping("/addHotel")
    public String addHotel(Model model) {
        Hotel hotel = new Hotel();
        model.addAttribute("formHotel", hotel);
        return "new_hotel"; // View name for the "add hotel" form
    }


    @RequestMapping("/saveHotel")
    public String saveHotel(@ModelAttribute("formHotel") Hotel hotel) {
        hotelService.addHotel(hotel);
        return "redirect:/allHotels";
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


    @RequestMapping("/allHotels")
    public String listAllHotels(Model model) {
        model.addAttribute("hotels", hotelService.findAllHotels());
        return "list_hotels"; // View name for displaying all Hotels
    }
}
