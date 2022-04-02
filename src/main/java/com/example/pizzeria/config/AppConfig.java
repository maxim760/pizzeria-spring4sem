package com.example.pizzeria.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableJpaRepositories("com.example.pizzeria.repository")
public class AppConfig { }
