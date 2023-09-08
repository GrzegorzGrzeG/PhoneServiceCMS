package com.phoneservice.phoneservice.controller;

import com.phoneservice.phoneservice.entity.Part;
import com.phoneservice.phoneservice.entity.Phone;
import com.phoneservice.phoneservice.service.PartService;
import com.phoneservice.phoneservice.service.PhoneService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PhoneService phoneService;
    private final PartService partService;

    public AdminController(PhoneService phoneService, PartService partService) {
        this.phoneService = phoneService;
        this.partService = partService;
    }

    @GetMapping("")
    public String mainMenu(Model model) {
        return "/html/admin_menu";
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
        return "/html/admin_menu";
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
        return "/html/admin_menu";
    }

    @GetMapping("/part/stock")
    public String partStock(Model model) {
        model.addAttribute("parts", partService.allParts());
        return "/html/part_stock";
    }

    @PostMapping("/part/stock")
    public String partStockPost(@RequestParam("newQuantity")Integer quantity, @RequestParam("partId")Long partId, @RequestParam("newPrice") Double price) {
        partService.updateQuantity(partId, quantity, price);
        return "/html/admin_menu";
    }




}
