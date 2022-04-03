package com.example.pizzeria.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


@Configuration
@EnableJpaRepositories("com.example.pizzeria.repository")
@EnableGlobalMethodSecurity(jsr250Enabled=true)
public class AppConfig { }
