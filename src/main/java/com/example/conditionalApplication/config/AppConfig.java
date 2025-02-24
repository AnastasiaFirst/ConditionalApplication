package com.example.conditionalApplication.config;

import com.example.conditionalApplication.model.DevProfile;
import com.example.conditionalApplication.model.ProductionProfile;
import com.example.conditionalApplication.model.SystemProfile;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    @ConditionalOnProperty(name = "netology.profile.dev", havingValue = "true")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(name = "netology.profile.dev", havingValue = "false", matchIfMissing = true)
    public SystemProfile productionProfile() {
        return new ProductionProfile();
    }
}