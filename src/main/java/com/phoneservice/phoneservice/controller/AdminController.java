package com.phoneservice.phoneservice.controller;

import com.phoneservice.phoneservice.entity.Part;
import com.phoneservice.phoneservice.entity.Phone;
import com.phoneservice.phoneservice.entity.User;
import com.phoneservice.phoneservice.entity.UserRole;
import com.phoneservice.phoneservice.service.PartService;
import com.phoneservice.phoneservice.service.PhoneService;
import com.phoneservice.phoneservice.service.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@PreAuthorize("hasRole('ADMIN')")
@Secured("ADMIN")
@RequestMapping("/admin")
public class AdminController {
    private final PhoneService phoneService;
    private final PartService partService;
    private final UserService userService;

    public AdminController(PhoneService phoneService, PartService partService, UserService userService) {
        this.phoneService = phoneService;
        this.partService = partService;
        this.userService = userService;
    }
    @GetMapping("")
    public String mainMenu(Model model) {
        return "/html/admin_menu";
    }


    @GetMapping("/model/new")
    public String newModel(Model model) {
        Phone phone = new Phone();
        model.addAttribute("phone", phone);
        return "/html/new_phone";
    }

    @PostMapping("/model/new")
    public String newModelPost(Phone phone) {
        phoneService.addNewPhone(phone);
        return "/html/admin_menu";
    }

    @GetMapping("/part/new")
    public String newPart(Model model) {
        Part part = new Part();
        model.addAttribute("part", part);
        return "/html/new_part";
    }

    @PostMapping("/part/new")
    public String newPartPost(Part part) {
        partService.newPart(part);
        return "/html/admin_menu";
    }

    @GetMapping("/part/stock")
    public String partStock(Model model) {
        model.addAttribute("parts", partService.allParts());
        return "/html/part_stock";
    }

    @PostMapping("/part/stock")
    public String partStockPost(@RequestParam("newQuantity")Integer quantity, @RequestParam("partId")Long partId, @RequestParam("newPrice") Double price) {
        partService.updateQuantity(partId, quantity, price);
        return "/html/admin_menu";
    }

    @GetMapping("/users")
    public String usersList(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("users",users);

        return "/html/user_list";
    }

    @PostMapping("/users")
    public String giveTech(@RequestParam("tech") Long id) {
        User user = userService.findClientById(id);
        user.setUserRole(UserRole.TECH);
        userService.updateUser(user);
        return "redirect:/admin";
    }




}
