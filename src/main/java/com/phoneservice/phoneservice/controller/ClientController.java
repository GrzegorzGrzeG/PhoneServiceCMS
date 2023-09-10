package com.phoneservice.phoneservice.controller;

import com.phoneservice.phoneservice.entity.Repair;
import com.phoneservice.phoneservice.entity.User;
import com.phoneservice.phoneservice.service.RepairService;
import com.phoneservice.phoneservice.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RolesAllowed("CLIENT")
@RequestMapping("/client")
public class ClientController {

    private final RepairService repairService;
    private final UserService userService;

    public ClientController(RepairService repairService,
                            UserService userService) {
        this.repairService = repairService;
        this.userService = userService;

    }

    @GetMapping("")
    public String clientHomePage(Model model, Principal principal) {
        String email = principal.getName();
        User user = userService.findByEmail(email);
        List<Repair> repairList = repairService.getByUserObj(user);
        model.addAttribute("repairList", repairList);
        return "/html/repair_finished";
    }


}
