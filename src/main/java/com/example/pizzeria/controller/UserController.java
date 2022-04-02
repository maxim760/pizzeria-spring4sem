package com.example.pizzeria.controller;

import com.example.pizzeria.entity.UserEntity;
import com.example.pizzeria.model.User;
import com.example.pizzeria.repository.UserRepo;
import com.example.pizzeria.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    List<User> getAll() {
        return User.toModel(userService.getAll());
    }

    @PostMapping
    User add(@RequestBody UserEntity userEntity) {
        return User.toModel(userService.addUser(userEntity));
    }


}
