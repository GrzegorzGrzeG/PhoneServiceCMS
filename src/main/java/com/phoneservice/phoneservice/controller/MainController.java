package com.phoneservice.phoneservice.controller;

import com.phoneservice.phoneservice.entity.User;
import com.phoneservice.phoneservice.exception.UserAlreadyExistException;
import com.phoneservice.phoneservice.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class MainController {
    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String mainSite(Model model) {
        return "/html/index";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "/html/register_form";
    }

    @PostMapping("/register")
    public String processRegister(User user) throws UserAlreadyExistException {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userService.newClient(user);
        return "/html/register_success";
    }

    @GetMapping("/my")
    @ResponseBody
    public String my(Principal principal) {
        return principal.getName();
    }

//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
}
