package com.phoneservice.phoneservice.controller;

import com.phoneservice.phoneservice.entity.Repair;
import com.phoneservice.phoneservice.service.RepairService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/repair")
public class RepairController {

    private final RepairService repairService;

    public RepairController(RepairService repairService) {
        this.repairService = repairService;
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("newRepair", new Repair());
        return "/html/new_repair";
    }
    @PostMapping("/new")
    public String processNewForm(Repair repair) {
        repairService.newRepair(repair);
        //dodać stronę z potwierdzeniem danych naprawy;
        return "";
    }


}
