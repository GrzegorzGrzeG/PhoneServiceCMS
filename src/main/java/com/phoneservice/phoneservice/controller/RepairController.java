package com.phoneservice.phoneservice.controller;

import com.phoneservice.phoneservice.entity.Phone;
import com.phoneservice.phoneservice.entity.Repair;
import com.phoneservice.phoneservice.entity.RepairStatus;
import com.phoneservice.phoneservice.service.PhoneService;
import com.phoneservice.phoneservice.service.RepairService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/repair")
public class RepairController {

    private final RepairService repairService;
    private final PhoneService phoneService;

    public RepairController(RepairService repairService, PhoneService phoneService) {
        this.repairService = repairService;
        this.phoneService = phoneService;
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        Repair repair = new Repair();
        model.addAttribute("newRepair", repair);
        List<Phone> phones = phoneService.getAll();
        model.addAttribute("phones", phones);
        return "/html/new_repair";
    }
    @PostMapping("/new")
    @ResponseBody
    public String processNewForm(@ModelAttribute("newRepair") Repair repair) {
        Long phoneId = repair.getPhoneId();
        Phone phone = phoneService.getPhoneById(phoneId);
        repair.setPhone(phone);
        repairService.newRepair(repair);
        //dodać stronę z potwierdzeniem danych naprawy;
        return "succes" + phoneId;
    }


}
