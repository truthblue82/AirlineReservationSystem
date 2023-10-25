package com.miu.flightmanagement.airlinebookingservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Collection;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Booking {
	@Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name = "booking_code", unique = true, nullable = false)
	private String bookingCode;
	private OffsetDateTime bookingDate;
	private int noOfPassengers;

	@OneToMany(mappedBy = "booking", cascade = {CascadeType.PERSIST})
	private Collection<Passenger> passengers;

	@ManyToOne
	private ScheduledFlight scheduledFlight;

}
