package com.phoneservice.phoneservice.controller;

import com.phoneservice.phoneservice.entity.Part;
import com.phoneservice.phoneservice.entity.Repair;
import com.phoneservice.phoneservice.entity.RepairStatus;
import com.phoneservice.phoneservice.service.PartService;
import com.phoneservice.phoneservice.service.RepairService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/tech")
public class TechnicianController {
//    private final TechnicianService technicianService;
    private final RepairService repairService;
    private final PartService partService;

    public TechnicianController(RepairService repairService, PartService partService) {
        this.repairService = repairService;
        this.partService = partService;
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
        List<Part> parts = partService.allParts();
        if (Objects.isNull(repair)) {
            return "/html/repair_list";
        }
        model.addAttribute("repair", repair);
        model.addAttribute("parts", parts);
        return "/html/repair_details";
    }

    @PostMapping("/repair/{id}")
    @ResponseBody
    public String repairDetailsProcess(@RequestParam("start") String start, @ModelAttribute("repair")Repair repair) {
        if(start.equals("start")){
            repair.setStatus(RepairStatus.IN_REPAIR);
//            repairService.update(repair);

        }
        return "succs";
    }


}
