package com.miu.flightmanagement.airlinebookingservice.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Collection;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Booking {
	@Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name = "booking_code", unique = true, nullable = false)
	private String bookingCode;
	private OffsetDateTime bookingDate;
	private int noOfPassengers;

	@OneToMany(mappedBy = "booking")
	private Collection<Passenger> passengers;

}
