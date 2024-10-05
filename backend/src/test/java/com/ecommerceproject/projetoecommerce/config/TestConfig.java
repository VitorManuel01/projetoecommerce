package com.ecommerceproject.projetoecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@Configuration
public class TestConfig {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("test");
    }
}