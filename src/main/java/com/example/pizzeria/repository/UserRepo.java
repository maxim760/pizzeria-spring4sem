package com.example.pizzeria.repository;

import com.example.pizzeria.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, UUID> {
    UserEntity findByUsername(String username);
}
