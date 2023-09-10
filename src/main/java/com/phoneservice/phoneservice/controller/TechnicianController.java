package com.phoneservice.phoneservice.controller;

import com.phoneservice.phoneservice.entity.Part;
import com.phoneservice.phoneservice.entity.Repair;
import com.phoneservice.phoneservice.entity.RepairStatus;
import com.phoneservice.phoneservice.service.PartService;
import com.phoneservice.phoneservice.service.RepairService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RolesAllowed("TECH")
@RequestMapping("/tech")
public class TechnicianController {
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
    public String repairDetailsProcess(@RequestParam(name = "partId") List<Long> partsId, @RequestParam("repairId") Long id) {
        Repair repair = repairService.getById(id);
        List<Part> list = new ArrayList<>();
        Double sum = .0;
        for (Long partId : partsId) {
            Part part = partService.findById(partId);
            list.add(part);
            sum += part.getPrice();
            part.setQuantity(part.getQuantity() - 1);
            partService.newPart(part);
        }
        repair.setParts(list);
        repair.setSum(sum);
        repair.setStatus(RepairStatus.FINISHED);
        repair.setEndRepair(LocalDate.now().atStartOfDay());
        repairService.updateRepair(repair);
        return "redirect:/tech/repair/finished";
    }


    @GetMapping("/repair/finished")
    public String finished(Model model) {
        List<Repair> finished = repairService.finished();
        model.addAttribute("repairList", finished);
        return "/html/repair_finished";
    }


}
