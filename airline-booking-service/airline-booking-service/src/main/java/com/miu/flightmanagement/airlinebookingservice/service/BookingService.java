package com.miu.flightmanagement.airlinebookingservice.service;

import com.miu.flightmanagement.airlinebookingservice.model.Booking;
import org.springframework.http.ResponseEntity;

public interface BookingService {

	public ResponseEntity<?> createBooking(Booking newBooking);

	public Booking updateBooking(Booking newBooking);

	public String deleteBooking(Long bookingId);

	public Iterable<Booking> displayAllBooking();

	public ResponseEntity<?> findBookingById(Long bookingId);
}
