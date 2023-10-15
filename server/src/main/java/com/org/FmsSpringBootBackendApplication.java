package com.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@ComponentScan("com.org.model")
@ComponentScan("com.org.dao")
@ComponentScan("com.org.service")
@ComponentScan("com.org.controller")
public class FmsSpringBootBackendApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(FmsSpringBootBackendApplication.class, args);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("GET", "POST", "PUT", "DELETE")
				.allowedHeaders("Origin", "Accept", "Content-Type", "Authorization")
				.allowCredentials(true)
				.maxAge(3600);
	}
}
