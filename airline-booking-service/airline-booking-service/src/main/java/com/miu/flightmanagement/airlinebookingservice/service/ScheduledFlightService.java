package com.miu.flightmanagement.airlinebookingservice.service;


import com.miu.flightmanagement.airlinebookingservice.dto.ScheduledFlightDTO;
import com.miu.flightmanagement.airlinebookingservice.exception.RecordNotFoundException;
import com.miu.flightmanagement.airlinebookingservice.exception.ScheduledFlightNotFoundException;
import com.miu.flightmanagement.airlinebookingservice.model.ScheduledFlight;

import java.time.LocalDate;
import java.util.Collection;

public interface ScheduledFlightService {
	ScheduledFlight addScheduledFlight(ScheduledFlight scheduledFlight);

	ScheduledFlight modifyScheduledFlight(Long id, ScheduledFlightDTO scheduledFlightDto);

	String removeScheduledFlight(Long id) throws RecordNotFoundException;

	Iterable<ScheduledFlight> viewAllScheduledFlights();

	Collection<ScheduledFlight> viewScheduledFlights(
			final LocalDate deptDateTime,
			final String srcAirport,
			final String dstnAirport,
			final Short noOfPassengers
	) throws ScheduledFlightNotFoundException;

	void reserveSeats(final Long scheduledFlightId, final Short numberOfSeats);

}
