package com.miu.flightmanagement.airlinebookingservice.service;


import com.miu.flightmanagement.airlinebookingservice.dto.AirportDTO;
import com.miu.flightmanagement.airlinebookingservice.model.Airport;

import java.util.Collection;

public interface AirportService {
	public Collection<AirportDTO> viewAllAirport();

	public Airport viewAirport(String airportCode);

	public void addAirport(AirportDTO airport);

	public Airport modifyAirport(AirportDTO airport, String code);

	public void removeAirport(String airportCode);
}
