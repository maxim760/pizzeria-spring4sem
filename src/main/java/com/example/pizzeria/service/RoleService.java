package com.example.pizzeria.service;

import com.example.pizzeria.entity.RoleEntity;
import com.example.pizzeria.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class RoleService {
    @Autowired
    private RoleRepo roleRepo;

    public RoleEntity findOrCreateRole(String name) {
        RoleEntity role = roleRepo.findByName(name);
        if(role == null) {
            RoleEntity newRole = new RoleEntity();
            newRole.setName(name);
            roleRepo.save(newRole);
            return newRole;
        }
        return role;

    }
}
