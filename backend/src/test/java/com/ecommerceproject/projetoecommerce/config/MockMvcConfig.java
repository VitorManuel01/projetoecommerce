package com.ecommerceproject.projetoecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class MockMvcConfig {

    @Bean
    public MockMvc mockMvc(WebApplicationContext webApplicationContext) {
        return MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity()) // Apply Spring Security
                .build();
    }
}