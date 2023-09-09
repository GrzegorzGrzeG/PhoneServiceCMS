package com.phoneservice.phoneservice.controller;

import com.phoneservice.phoneservice.service.RepairService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/client")
public class ClientController {

    private final RepairService repairService;

    public ClientController(RepairService repairService) {
        this.repairService = repairService;
    }

    @GetMapping("/client/{id}")
    public String clientHomePage(@PathVariable Long id, Model model){

        return "/html/client_menu";
    }


}
