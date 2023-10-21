package com.miu.flightmanagement.airlinebookingservice.model;

import jakarta.persistence.*;

@Entity
public class Booking {
	@Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String bookingDate;
	private int noOfPassengers;

	public Long getBookingId() {
		return id;
	}

	public void setBookingId(Long bookingId) {
		this.id = bookingId;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public int getNoOfPassengers() {
		return noOfPassengers;
	}

	public void setNoOfPassengers(int noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}
}
