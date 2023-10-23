package com.miu.flightmanagement.airlinebookingservice.model;

import java.math.BigInteger;
import java.util.Date;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
