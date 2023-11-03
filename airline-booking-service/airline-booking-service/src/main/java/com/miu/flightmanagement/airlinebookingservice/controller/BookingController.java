package com.miu.flightmanagement.airlinebookingservice.controller;

import com.miu.flightmanagement.airlinebookingservice.dto.BookingWithPaymentRequest;
import com.miu.flightmanagement.airlinebookingservice.dto.SeatReservation;
import com.miu.flightmanagement.airlinebookingservice.exception.RecordNotFoundException;
import com.miu.flightmanagement.airlinebookingservice.service.BookingService;
import com.miu.flightmanagement.airlinebookingservice.service.ScheduledFlightService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/bookings")
@AllArgsConstructor
public class BookingController {

	private final BookingService bookingService;
	private final ScheduledFlightService scheduledFlightService;
	@PostMapping
	public ResponseEntity<?> addBooking(@RequestBody BookingWithPaymentRequest newBooking) {
		return ResponseEntity.ok(bookingService.bookTicketsWithPayment(newBooking));
	}

	@GetMapping("/search")
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<?> searchBookingByCode(@RequestParam("code") String bookingCode) {
		return ResponseEntity.ok(bookingService.findBooking(bookingCode));
	}

	@PostMapping("/reserveSeats")
	public ResponseEntity<?> reserveSeats(@RequestBody SeatReservation seatReservation) {
		scheduledFlightService.reserveSeats(seatReservation.getScheduledFlightId(), seatReservation.getNumberOfSeats());
		return ResponseEntity.ok().build();
	}

}
