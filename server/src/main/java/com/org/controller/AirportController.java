package com.org.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.org.model.Airport;
import com.org.model.Flight;
import com.org.service.AirportService;
import com.org.service.AirportServiceImpl;
//@CrossOrigin("http://localhost:4200")
@CrossOrigin
@RestController
@RequestMapping("/api/airport")
public class AirportController {
	@Autowired(required = true)
	AirportService airportService;

	@GetMapping("/{code}")
	public Airport getAirport(@PathVariable String code) {
		return airportService.viewAirport(code);
	}

	@GetMapping("/")
	public Iterable<Airport> viewAllAirport() {
		return airportService.viewAllAirport();
	}

	@PostMapping("/")
	public void addAirport(@RequestBody Airport airport) {
		airportService.addAirport(airport);
	}

	@PutMapping("/{code}")
	public void modifyAirport(@RequestBody Airport airport, @PathVariable String code) {
		airportService.modifyAirport(airport, code);
	}

	@DeleteMapping("/{code}")
	public void removeAirport(@PathVariable String code) {
		airportService.removeAirport(code);
	}
}
