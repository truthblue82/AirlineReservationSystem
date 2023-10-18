package com.miu.flightmanagement.authorizationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.miu.flightmanagement.authorizationservice.persistence.repo")
@EntityScan(basePackages = "com.miu.flightmanagement.authorizationservice.persistence.model")
public class AuthourizationServerApplication  {

    public static void main(String[] args) {
        SpringApplication.run(AuthourizationServerApplication.class, args);
    }

}
