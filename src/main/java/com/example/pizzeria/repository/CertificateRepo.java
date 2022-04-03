package com.example.pizzeria.repository;

import com.example.pizzeria.entity.CertificateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CertificateRepo extends JpaRepository<CertificateEntity, UUID> {

}
