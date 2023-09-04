package com.phoneservice.phoneservice.controller;

import com.phoneservice.phoneservice.entity.Part;
import com.phoneservice.phoneservice.entity.Phone;
import com.phoneservice.phoneservice.service.PartService;
import com.phoneservice.phoneservice.service.PhoneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PhoneService phoneService;
    private final PartService partService;

    public AdminController(PhoneService phoneService, PartService partService) {
        this.phoneService = phoneService;
        this.partService = partService;
    }

    @GetMapping("/model/new")
    public String newModel(Model model) {
        Phone phone = new Phone();
        model.addAttribute("phone", phone);
        return "/html/new_phone";
    }

    @PostMapping("/model/new")
    public String newModelPost(Phone phone) {
        phoneService.addNewPhone(phone);
        //sprawdzanie duplikatów lub dodanie nowego formularza uzupełniania zapasów
        return "index";
    }

    @GetMapping("/part/new")
    public String newPart(Model model) {
        Part part = new Part();
        model.addAttribute("part", part);
        return "/html/new_part";
    }

    @PostMapping("/part/new")
    public String newPartPost(Part part) {
        partService.newPart(part);
        return "index";
    }

    @GetMapping("/part/stock")
    public String partStock(Model model) {
        model.addAttribute("parts", partService.allParts());
        return "/html/part_stock";
    }
}
