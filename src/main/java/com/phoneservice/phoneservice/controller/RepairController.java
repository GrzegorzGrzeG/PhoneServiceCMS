package com.phoneservice.phoneservice.controller;

import com.phoneservice.phoneservice.entity.Phone;
import com.phoneservice.phoneservice.entity.Repair;
import com.phoneservice.phoneservice.entity.User;
import com.phoneservice.phoneservice.service.PhoneService;
import com.phoneservice.phoneservice.service.RepairService;
import com.phoneservice.phoneservice.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@PreAuthorize("hasRole('CLIENT')")
@RequestMapping("/repair")
public class RepairController {

    private final RepairService repairService;
    private final PhoneService phoneService;
    private final UserService userService;

    public RepairController(RepairService repairService, PhoneService phoneService, UserService userService) {
        this.repairService = repairService;
        this.phoneService = phoneService;
        this.userService = userService;
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
    public String processNewForm(@ModelAttribute("newRepair") Repair repair, Principal principal) {
        String email = principal.getName();

        User user = userService.findByEmail(email);
        Long phoneId = repair.getPhoneId();
        Phone phone = phoneService.getPhoneById(phoneId);
        repair.setPhone(phone);
        repair.setUser(user);
        repairService.newRepair(repair);

        return "redirect:/client";
    }


}
