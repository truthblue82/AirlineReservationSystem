package com.miu.flightmanagement.airlinebookingservice.controller;

import com.miu.flightmanagement.airlinebookingservice.dto.FlightDTO;
import com.miu.flightmanagement.airlinebookingservice.dto.FlightsDTO;
import com.miu.flightmanagement.airlinebookingservice.service.FlightService;
import com.miu.flightmanagement.airlinebookingservice.util.FlightUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("api/flight")
@AllArgsConstructor
public class FlightController {
	private final FlightService flightService;

	@PostMapping("/addFlight")
	public ResponseEntity<?> addFlight(@RequestBody FlightDTO flight) {
		flightService.addFlight(flight);
		return ResponseEntity.ok(flight);
	}

	@RequestMapping("/allFlight")
	public ResponseEntity<?> viewAllFlight() {
		return ResponseEntity.ok(FlightsDTO.builder().flights(FlightUtil.flightDTOs(flightService.viewAllFlight())).build());
	}

	@RequestMapping("/viewFlight/{id}")
	public ResponseEntity<?> viewFlight(@PathVariable("id") String flightNo) {
		return ResponseEntity.ok(FlightUtil.toFlightDTO(flightService.viewFlight(flightNo)));
	}

	@PutMapping("/updateFlight")
	public ResponseEntity<?> modifyFlight(@RequestBody FlightDTO flight) {
		return ResponseEntity.ok(FlightUtil.toFlightDTO(flightService.modifyFlight(flight)));
	}

	@DeleteMapping("/deleteFlight/{id}")
	public void removeFlight(@PathVariable("id") String flightNo) {
		flightService.removeFlight(flightNo);
	}
}
