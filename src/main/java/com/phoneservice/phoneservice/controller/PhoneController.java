package com.phoneservice.phoneservice.controller;

import com.phoneservice.phoneservice.entity.Phone;
import com.phoneservice.phoneservice.repository.PhoneRepository;
import com.phoneservice.phoneservice.service.PhoneService;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/phone")
public class PhoneController {

    private final PhoneService phoneService;

    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        Phone phone = new Phone();
        model.addAttribute("phone", phone);
        return "/html/new_phone";
    }

    @PostMapping("/new")
    @ResponseBody
    public String processNewPhone(Phone phone) {
        phoneService.addNewPhone(phone);
        return "success";
    }
}
