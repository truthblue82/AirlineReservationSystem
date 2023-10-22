package com.miu.flightmanagement.airlinebookingservice.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_code", unique = true, nullable = false)
    private String paymentCode;

    private String last4DigitPaymentCard;

    private String bookingUserFullname;

    private String bookingUserEmail;

    private BigDecimal totalPrice;
}
