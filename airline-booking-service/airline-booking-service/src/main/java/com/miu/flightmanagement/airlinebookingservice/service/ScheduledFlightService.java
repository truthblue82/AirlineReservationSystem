package com.miu.flightmanagement.airlinebookingservice.service;


import com.miu.flightmanagement.airlinebookingservice.exception.RecordNotFoundException;
import com.miu.flightmanagement.airlinebookingservice.exception.ScheduledFlightNotFoundException;
import com.miu.flightmanagement.airlinebookingservice.model.ScheduledFlight;

import java.time.LocalDate;
import java.util.Collection;

public interface ScheduledFlightService {
	public ScheduledFlight addScheduledFlight(ScheduledFlight scheduledFlight);

	public ScheduledFlight modifyScheduledFlight(ScheduledFlight scheduledFlight);

	public String removeScheduledFlight(Long id) throws RecordNotFoundException;

	public Iterable<ScheduledFlight> viewAllScheduledFlights();

	Collection<ScheduledFlight> viewScheduledFlights(
			final LocalDate deptDateTime,
			final String srcAirport,
			final String dstnAirport,
			final Short noOfPassengers
	) throws ScheduledFlightNotFoundException;
	// boolean cancelBookings(BigInteger flightId) throws RecordNotFoundException;

}
