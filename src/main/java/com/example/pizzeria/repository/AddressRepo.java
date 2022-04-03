package com.example.pizzeria.repository;

import com.example.pizzeria.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressRepo extends JpaRepository<AddressEntity, UUID> {
    AddressEntity findByUserId(UUID id);
}
