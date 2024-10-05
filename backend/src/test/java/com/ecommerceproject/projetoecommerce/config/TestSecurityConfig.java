package com.ecommerceproject.projetoecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Profile("test")
public class TestSecurityConfig {

    @Bean(name = "testSecurityFilterChain")
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(authz -> authz
                                .anyRequest().permitAll() // Allow all requests in tests
                )
                .csrf(csrf -> csrf.disable()); 
        
        return httpSecurity.build();
    }
}