package com.example.pizzeria.controller;

import com.example.pizzeria.entity.UserEntity;
import com.example.pizzeria.model.User;
import com.example.pizzeria.repository.UserRepo;
import com.example.pizzeria.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAll(Model model) {
        val users = User.toModel(userService.getAll());
        model.addAttribute("users", users);
        return "home";
    }

    @PostMapping
    @RolesAllowed({"ADMIN"})
    Object add(UserEntity userEntity, Model model, RedirectAttributes redirectAttributes) {
        try {
            userService.addUser(userEntity);
            model.addAttribute("message", "Пользователь добавлен");
            return "redirect:/home";
        } catch (Exception e) {
            System.out.println("error");
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("errorTitle", "Пользователь не добавлен");
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/error";
        }
    }


}
