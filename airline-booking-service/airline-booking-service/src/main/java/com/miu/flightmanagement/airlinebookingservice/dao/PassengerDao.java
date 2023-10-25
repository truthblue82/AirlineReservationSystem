package com.miu.flightmanagement.airlinebookingservice.dao;

import com.miu.flightmanagement.airlinebookingservice.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerDao extends JpaRepository<Passenger, Long> {
}
