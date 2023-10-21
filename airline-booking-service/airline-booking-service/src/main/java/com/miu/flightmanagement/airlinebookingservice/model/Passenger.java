package com.miu.flightmanagement.airlinebookingservice.model;

import java.math.BigInteger;
import java.util.Date;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Passenger {

	@Id
	private BigInteger pnrNumber;

	private String passengerName;

	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	private Double luggage;

	@ManyToOne(fetch = FetchType.LAZY)
	private Booking booking;
}
