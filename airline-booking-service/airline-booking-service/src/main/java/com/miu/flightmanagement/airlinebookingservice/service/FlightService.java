package com.miu.flightmanagement.airlinebookingservice.service;


import com.miu.flightmanagement.airlinebookingservice.dto.FlightDTO;
import com.miu.flightmanagement.airlinebookingservice.model.Flight;

public interface FlightService {
	public void addFlight(FlightDTO flight);

	public Iterable<Flight> viewAllFlight();

	public Flight viewFlight(String flightNumber);

	public Flight modifyFlight(FlightDTO flight);

	public void removeFlight(String flightNumber);

}
