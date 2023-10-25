package com.miu.flightmanagement.airlinebookingservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToOne(fetch = FetchType.LAZY)
    private Booking booking;
}
