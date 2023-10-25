package com.miu.flightmanagement.airlinebookingservice.dao;

import com.miu.flightmanagement.airlinebookingservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDao extends JpaRepository<Payment, Long> {

}
