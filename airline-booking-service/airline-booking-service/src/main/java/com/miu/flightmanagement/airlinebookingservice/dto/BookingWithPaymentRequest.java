package com.miu.flightmanagement.airlinebookingservice.dto;

import lombok.*;

@Getter
@Setter
@ToString
public class BookingWithPaymentRequest {

    private BookingDTO booking;
    private PaymentDTO payment;
}
