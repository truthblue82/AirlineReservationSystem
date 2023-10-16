package com.org.controller;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import com.org.dto.ScheduledFlightDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.exceptions.RecordNotFoundException;
import com.org.exceptions.ScheduledFlightNotFoundException;
import com.org.model.Schedule;
import com.org.model.ScheduledFlight;
import com.org.service.AirportService;
import com.org.service.FlightService;
import com.org.service.ScheduledFlightService;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@CrossOrigin
@RequestMapping("/scheduledFlight")
public class ScheduledFlightController {
	/*
	 * Creating Service object
	 */
	@Autowired
	ScheduledFlightService scheduleFlightService;

	@Autowired
	AirportService airportService;

	@Autowired
	FlightService flightService;



	/*
	 * Controller for adding Scheduled Flights
	 */
	@PostMapping("/add")
	public ResponseEntity<ScheduledFlight> addSF(@RequestParam("flightNo") String flightNo,
			@RequestParam(name = "srcAirport") String source, @RequestParam(name = "dstnAirport") String destination,
			@RequestParam(name = "deptDateTime") String departureTime, @RequestParam(name = "arrDateTime") String arrivalTime) {
		final Schedule schedule = new Schedule();
		final ScheduledFlight scheduledFlight = new ScheduledFlight();
//		schedule.setScheduleId(scheduledFlight.getScheduleFlightId());
		try {
			schedule.setSrcAirport(airportService.viewAirport(source));
		} catch (RecordNotFoundException e) {
			return new ResponseEntity("Airport Not Found", HttpStatus.BAD_REQUEST);
		}
		try {
			schedule.setDstnAirport(airportService.viewAirport(destination));
		} catch (RecordNotFoundException e) {
			return new ResponseEntity("Airport Not Found", HttpStatus.BAD_REQUEST);
		}
		schedule.setDeptDateTime(LocalDateTime.parse(departureTime).atOffset(ZoneOffset.UTC));
		schedule.setArrDateTime(LocalDateTime.parse(arrivalTime).atOffset(ZoneOffset.UTC));
		try {
			scheduledFlight.setFlight(flightService.viewFlight(flightNo));
		} catch (RecordNotFoundException e1) {
			return new ResponseEntity("Flight Not Found", HttpStatus.BAD_REQUEST);
		}
		scheduledFlight.setSchedule(schedule);
		scheduledFlight.setAvailableSeats(scheduledFlight.getFlight().getSeatCapacity());
		try {
			return new ResponseEntity<ScheduledFlight>(scheduleFlightService.addScheduledFlight(scheduledFlight),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity("Error adding Flight." + e, HttpStatus.BAD_REQUEST);
		}
	}

	/*
	 * Controller for modifying existing Scheduled Flights
	 */
	@PutMapping("/modify")
	public ResponseEntity<ScheduledFlight> modifyScheduleFlight(@ModelAttribute ScheduledFlight scheduleFlight) {
		ScheduledFlight modifySFlight = scheduleFlightService.modifyScheduledFlight(scheduleFlight);
		if (modifySFlight == null) {
			return new ResponseEntity("Flight not modified", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<ScheduledFlight>(modifySFlight, HttpStatus.OK);
		}

	}

	/*
	 * Controller for deleting existing Scheduled Flights
	 */
	@DeleteMapping("/delete")
	public String deleteSF(@RequestParam Long flightId) throws RecordNotFoundException {
		return scheduleFlightService.removeScheduledFlight(flightId);
	}

	/*
	 * Controller for viewing a Scheduled Flight by ID
	 */
	@GetMapping("/search")
	public ResponseEntity<?> viewSF(
			@RequestParam(name = "srcAirport") String source, @RequestParam(name = "dstnAirport") String destination,
			@RequestParam(name = "deptDate") String departureDate
	)  {
		try {
			Collection<ScheduledFlight> searchSFlight = scheduleFlightService.viewScheduledFlights(
					LocalDateTime.parse(departureDate).atOffset(ZoneOffset.UTC).toLocalDate(),
					source,
					destination
			);

			final ScheduledFlightDTO scheduledFlightDTO = new ScheduledFlightDTO();
			scheduledFlightDTO.setScheduledFlights(searchSFlight);
			return new ResponseEntity<>(scheduledFlightDTO, HttpStatus.OK);
		}catch (ScheduledFlightNotFoundException ex) {
			return new ResponseEntity("Flight not present", HttpStatus.BAD_REQUEST);
		}
	}

	/*
	 * Controller for viewing all Scheduled Flights
	 */
	@GetMapping("/viewAll")
	public Iterable<ScheduledFlight> viewAllSF() {
		return scheduleFlightService.viewAllScheduledFlights();
	}
	

}
