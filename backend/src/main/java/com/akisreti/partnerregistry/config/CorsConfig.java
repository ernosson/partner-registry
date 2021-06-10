package com.akisreti.partnerregistry.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CorsConfig.
 *
 * @author kisretia
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings( final CorsRegistry registry) {
        registry.addMapping("/partner/*").allowedOrigins("http://localhost:4200");
        registry.addMapping("/address/*").allowedOrigins("http://localhost:4200");
        registry.addMapping("/partner/*").allowedOrigins("http://localhost:8081");
        registry.addMapping("/address/*").allowedOrigins("http://localhost:8081");
    }
}
