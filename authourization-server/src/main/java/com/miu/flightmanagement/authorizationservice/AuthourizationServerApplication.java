package com.miu.flightmanagement.authorizationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.miu.flightmanagement.authorizationservice.persistence.repo")
@EntityScan(basePackages = "com.miu.flightmanagement.authorizationservice.persistence.model")
public class AuthourizationServerApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(AuthourizationServerApplication.class, args);
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("/localhost**")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("Origin", "Accept", "Content-Type", "Authorization")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
