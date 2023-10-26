package com.miu.flightmanagement.airlinebookingservice.controller;

import com.miu.flightmanagement.airlinebookingservice.dto.ScheduleFlightRequest;
import com.miu.flightmanagement.airlinebookingservice.dto.ScheduleFlightSearchRequest;
import com.miu.flightmanagement.airlinebookingservice.dto.ScheduledFlightDTO;
import com.miu.flightmanagement.airlinebookingservice.dto.ScheduledFlightsDTO;
import com.miu.flightmanagement.airlinebookingservice.exception.RecordNotFoundException;
import com.miu.flightmanagement.airlinebookingservice.exception.ScheduledFlightAlreadyBookedException;
import com.miu.flightmanagement.airlinebookingservice.exception.ScheduledFlightNotFoundException;
import com.miu.flightmanagement.airlinebookingservice.model.Schedule;
import com.miu.flightmanagement.airlinebookingservice.model.ScheduledFlight;
import com.miu.flightmanagement.airlinebookingservice.service.AirportService;
import com.miu.flightmanagement.airlinebookingservice.service.FlightService;
import com.miu.flightmanagement.airlinebookingservice.service.ScheduledFlightService;
import com.miu.flightmanagement.airlinebookingservice.util.ScheduledFlightUtil;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@RestController
@CrossOrigin
@RequestMapping("/api/scheduled-flight")
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
	@PostMapping()
	public ResponseEntity<ScheduledFlight> addSF(@RequestBody ScheduleFlightRequest scheduleFlightRequest) {
		final Schedule schedule = new Schedule();
		final ScheduledFlight scheduledFlight = new ScheduledFlight();
		try {
			schedule.setSrcAirport(airportService.viewAirport(scheduleFlightRequest.getDepartureAirportCode()));
		} catch (RecordNotFoundException e) {
			return new ResponseEntity("Airport Not Found", HttpStatus.BAD_REQUEST);
		}
		try {
			schedule.setDstnAirport(airportService.viewAirport(scheduleFlightRequest.getArrivalAirportCode()));
		} catch (RecordNotFoundException e) {
			return new ResponseEntity("Airport Not Found", HttpStatus.BAD_REQUEST);
		}
		schedule.setDeptDateTime(LocalDateTime.parse(scheduleFlightRequest.getDepartureDateTime()).atOffset(ZoneOffset.UTC));
		schedule.setArrDateTime(LocalDateTime.parse(scheduleFlightRequest.getArrivalDateTime()).atOffset(ZoneOffset.UTC));
		try {
			scheduledFlight.setFlight(flightService.viewFlight(scheduleFlightRequest.getFlightNo()));
		} catch (RecordNotFoundException e1) {
			return new ResponseEntity("Flight Not Found", HttpStatus.BAD_REQUEST);
		}
		scheduledFlight.setSchedule(schedule);
		scheduledFlight.setAvailableSeats(scheduledFlight.getFlight().getSeatCapacity());
		scheduledFlight.setTemporaryAvailableSeats(scheduledFlight.getFlight().getSeatCapacity());
		scheduledFlight.setEconomicPrice(NumberUtils.createBigDecimal(scheduleFlightRequest.getEconomicPrice()));
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
	@PutMapping()
	public ResponseEntity<ScheduledFlightDTO> modifyScheduleFlight(@RequestBody ScheduledFlightDTO scheduledFlightDTO) {
		ScheduledFlight modifySFlight = scheduleFlightService.modifyScheduledFlight(scheduledFlightDTO);
		if (modifySFlight == null) {
			return new ResponseEntity("Flight not modified", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return ResponseEntity.ok(scheduledFlightDTO);
		}

	}

	/*
	 * Controller for deleting existing Scheduled Flights
	 */
	@DeleteMapping("/{sfId}")
	public String deleteSF(@PathVariable("sfId") Long scheduledFlightId) throws RecordNotFoundException {
		return scheduleFlightService.removeScheduledFlight(scheduledFlightId);
	}

	@GetMapping("/search")
	public ResponseEntity<?> viewSF(
			@RequestParam("deptAirport") final String deptAirport,
			@RequestParam("arrAirport") final String arrAirport,
			@RequestParam("deptDate") final String deptDate,
			@RequestParam("noOfPassengers") final Short onOfPassengers
	)  {
		try {
			Collection<ScheduledFlight> searchSFlight = scheduleFlightService.viewScheduledFlights(
					LocalDateTime.parse(deptDate, DateTimeFormatter.ISO_DATE_TIME).atOffset(ZoneOffset.UTC).toLocalDate(),
					deptAirport,
					arrAirport,
					onOfPassengers
			);

			final ScheduledFlightsDTO scheduledFlightDTO = ScheduledFlightsDTO.builder()
					.scheduledFlights(ScheduledFlightUtil.toScheduledFlightDTOs(searchSFlight))
					.build();
			return new ResponseEntity<>(scheduledFlightDTO, HttpStatus.OK);
		}catch (ScheduledFlightNotFoundException ex) {
			return ResponseEntity.noContent().build();
		}
	}

	/*
	 * Controller for viewing all Scheduled Flights
	 */
	@GetMapping("/viewall")
	public ResponseEntity<?> viewAllSF() {
		return ResponseEntity.ok(ScheduledFlightsDTO.builder()
				.scheduledFlights(ScheduledFlightUtil.toScheduledFlightDTOs(scheduleFlightService.viewAllScheduledFlights()))
				.build());
	}

	

}
