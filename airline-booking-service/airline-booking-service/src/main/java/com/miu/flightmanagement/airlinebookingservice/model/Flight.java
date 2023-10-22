package com.miu.flightmanagement.airlinebookingservice.model;

import lombok.*;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight {

	@Id
	private String flightNo;
	private String carrierName;
	private String flightModel;
	private int seatCapacity;

	/*
	 * @OneToOne(mappedBy = "flightObj", cascade = CascadeType.ALL) private
	 * ScheduledFlight sfObj;
	 */


}
