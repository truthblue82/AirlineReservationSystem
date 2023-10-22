package com.miu.flightmanagement.airlinebookingservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "schedule")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long scheduleId;

	@ManyToOne
	private Airport srcAirport;

	@ManyToOne
	private Airport dstnAirport;

	@Column(name = "departure_date")
	private OffsetDateTime deptDateTime;

	@Column(name = "arrival_date")
	private OffsetDateTime arrDateTime;
}
