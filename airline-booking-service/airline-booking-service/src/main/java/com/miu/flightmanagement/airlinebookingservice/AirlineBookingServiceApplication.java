package com.miu.flightmanagement.airlinebookingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.miu.flightmanagement.airlinebookingservice.dao")
@EntityScan(basePackages = "com.miu.flightmanagement.airlinebookingservice.model")
public class AirlineBookingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirlineBookingServiceApplication.class, args);
    }

}
