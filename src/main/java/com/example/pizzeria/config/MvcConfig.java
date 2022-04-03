package com.example.pizzeria.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/error").setViewName("error");
        registry.addViewController("/order").setViewName("order");
        registry.addViewController("/history").setViewName("history");
        registry.addViewController("/registration").setViewName("registration");
        registry.addViewController("/home").setViewName("home");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
            "/img/**",
            "/css/**",
            "/js/**",
            "/static/**"
        ).addResourceLocations(
            "classpath:/static/img/",
            "classpath:/static/css/",
            "classpath:/static/js/",
            "classpath:/static/"
        );
    }
}
