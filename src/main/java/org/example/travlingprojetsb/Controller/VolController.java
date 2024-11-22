package org.example.travlingprojetsb.Controller;

import org.example.travlingprojetsb.Entity.Vol;
import org.example.travlingprojetsb.Service.VolService;
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



}
