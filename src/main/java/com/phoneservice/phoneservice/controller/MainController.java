package com.phoneservice.phoneservice.controller;

import com.phoneservice.phoneservice.entity.Client;
import com.phoneservice.phoneservice.service.ClientService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    private final ClientService clientService;

    public MainController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/")
    public String mainSite(Model model) {
        return "/html/index";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("client", new Client());
        return "/html/register_form";
    }

    @PostMapping("/register")
    public String processRegister(Client client) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(client.getPassword());
        client.setPassword(encodedPassword);

        clientService.newClient(client);
        return "/html/register_success";
    }
}
