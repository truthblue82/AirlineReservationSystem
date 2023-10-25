package com.miu.flightmanagement.airlinebookingservice.service;


import com.miu.flightmanagement.airlinebookingservice.dto.BookingDTO;
import com.miu.flightmanagement.airlinebookingservice.dto.BookingWithPaymentRequest;

public interface BookingService {

	BookingDTO bookTicketsWithPayment(final BookingWithPaymentRequest bookingRequest);

	BookingDTO findBooking(final String bookingCode);

	boolean hasBooking(final Long scheduledFlightId);

}
