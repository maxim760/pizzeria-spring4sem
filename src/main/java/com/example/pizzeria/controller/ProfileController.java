package com.example.pizzeria.controller;

import com.example.pizzeria.entity.AddressEntity;
import com.example.pizzeria.entity.UserEntity;
import com.example.pizzeria.repository.UserRepo;
import com.example.pizzeria.service.ProfileService;
import com.example.pizzeria.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    @PostMapping("/main")
    String updateProfileMainInfo(@AuthenticationPrincipal UserEntity user, UserEntity updatedUser, RedirectAttributes attrs) {
        profileService.updateMainInfo(user, updatedUser);
        attrs.addFlashAttribute("message", "Основная информация обновлена");
        return "redirect:/home";
    }
    @PostMapping("/cash")
    String updateProfileCash(@AuthenticationPrincipal UserEntity user, UserEntity updatedUser, RedirectAttributes attrs) {
        profileService.updateCash(user, updatedUser);
        attrs.addFlashAttribute("message", updatedUser.getCash() > 0 ? "Баланс пополнен" : "Баланс изменился");
        return "redirect:/home";
    }
    @PostMapping("/address")
    String updateAddress(@AuthenticationPrincipal UserEntity user, AddressEntity newAddress, RedirectAttributes attrs) {
        profileService.updateAddress(user, newAddress);
        attrs.addFlashAttribute("message", "Адрес обновлен");
        return "redirect:/home";
    }
}
