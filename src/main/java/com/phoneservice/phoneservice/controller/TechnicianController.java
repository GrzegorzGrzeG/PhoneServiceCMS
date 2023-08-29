package com.phoneservice.phoneservice.controller;

import com.phoneservice.phoneservice.entity.Repair;
import com.phoneservice.phoneservice.service.RepairService;
import com.phoneservice.phoneservice.service.TechnicianService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/tech")
public class TechnicianController {
    private final TechnicianService technicianService;
    private final RepairService repairService;

    public TechnicianController(TechnicianService technicianService, RepairService repairService) {
        this.technicianService = technicianService;
        this.repairService = repairService;
    }

    @GetMapping("/repair")
    public String form(Model model) {
        model.addAttribute("repairList", repairService.getAll());
        return "/html/repair_list";
    }

    @PostMapping("/repair")
    public String processForm(@RequestParam("repairId") Long repairId) {
        return "redirect:/tech/repair/" + repairId;
    }


    @GetMapping("/repair/{id}")
    public String repairDetailsForm(@PathVariable Long id, Model model) {
        Repair repair = repairService.repairDetails(id);
        if(Objects.isNull(repair)) {
            return "/html/repair_list";
        }
        model.addAttribute("repair", repair);
       return "/html/repair_details";
    }
}
