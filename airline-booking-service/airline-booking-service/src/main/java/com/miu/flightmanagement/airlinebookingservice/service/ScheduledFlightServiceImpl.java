package com.miu.flightmanagement.airlinebookingservice.service;

import com.miu.flightmanagement.airlinebookingservice.dao.AirportDao;
import com.miu.flightmanagement.airlinebookingservice.dao.FlightDao;
import com.miu.flightmanagement.airlinebookingservice.dao.ScheduleDao;
import com.miu.flightmanagement.airlinebookingservice.dao.ScheduledFlightDao;
import com.miu.flightmanagement.airlinebookingservice.dto.ScheduledFlightDTO;
import com.miu.flightmanagement.airlinebookingservice.exception.RecordNotFoundException;
import com.miu.flightmanagement.airlinebookingservice.exception.ScheduledFlightAlreadyBookedException;
import com.miu.flightmanagement.airlinebookingservice.exception.ScheduledFlightNotFoundException;
import com.miu.flightmanagement.airlinebookingservice.model.ScheduledFlight;
import com.miu.flightmanagement.airlinebookingservice.util.ScheduledFlightUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Service
public class ScheduledFlightServiceImpl implements ScheduledFlightService {

	/*
	 * Creating DAO object
	 */
	@Autowired
	ScheduledFlightDao dao;

	@Autowired
	ScheduleDao scheduleDao;

	@Autowired
	FlightDao flightDao;

	@Autowired
	AirportDao airportDao;

	@Autowired
	BookingService bookingService;

	/*
	 * Service method to add new Scheduled flight to database
	 */
	@Override
	public ScheduledFlight addScheduledFlight(ScheduledFlight scheduledFlight) {
		return dao.save(scheduledFlight);
	}


	@Override
	@Transactional
	public ScheduledFlight modifyScheduledFlight(@NonNull final ScheduledFlightDTO scheduledFlightDto) {
		if (this.bookingService.hasBooking(scheduledFlightDto.getScheduleFlightId())) {
			throw new ScheduledFlightAlreadyBookedException("Scheduled flight has been booked");
		}
		if (!this.scheduleDao.existsById(scheduledFlightDto.getScheduleFlightId())) {
			throw new ScheduledFlightNotFoundException("No scheduled flight found for modification");
		}
		return dao.findById(scheduledFlightDto.getScheduleFlightId())
				.map(scheduledFlight -> {
					ScheduledFlightUtil.transferDtoToEntity(scheduledFlightDto, scheduledFlight);
					scheduledFlight.setFlight(flightDao.findById(scheduledFlightDto.getFlight().getFlightNo()).orElse(null));
					scheduledFlight.getSchedule().setSrcAirport(airportDao.findById(scheduledFlightDto.getSchedule().getSrcAirport().getCode()).orElse(null));
					scheduledFlight.getSchedule().setDstnAirport(airportDao.findById(scheduledFlightDto.getSchedule().getDstnAirport().getCode()).orElse(null));
					scheduledFlight.setAvailableSeats(scheduledFlight.getFlight().getSeatCapacity());
					scheduledFlight.setTemporaryAvailableSeats(scheduledFlight.getAvailableSeats());

					return dao.save(scheduledFlight);
				}).orElse(null);

	}

	/*
	 * Service method to remove existing Scheduled flight from database
	 */
	@Override
	public String removeScheduledFlight(Long flightId) throws RecordNotFoundException {
		if (flightId == null)
			throw new RecordNotFoundException("Flight not found with ID="+ flightId);
		Optional<ScheduledFlight> scheduleFlight = dao.findById(flightId);
		if (!scheduleFlight.isPresent())
			throw new RecordNotFoundException("Flight not found with ID="+ flightId);
		else {
			dao.deleteById(flightId);
		}
		return "Scheduled flight with ID " + flightId + " is not found";
	}

	// @Override
	// public boolean cancelBookings(BigInteger flightId) throws
	// RecordNotFoundException {
	// Iterable<Booking> bookingList = bookingService.displayAllBooking();
	// for (Booking booking : bookingList) {
	// if (booking.getScheduleFlight().getScheduleFlightId().equals(flightId)) {
	// bookingService.deleteBooking(booking.getBookingId());
	// }
	// }
	// return true;
	// }

	/*
	 * Service method to view all Scheduled flights in database
	 */
	@Override
	public Iterable<ScheduledFlight> viewAllScheduledFlights() {
		return dao.findAll();
	}


	@Override
	public Collection<ScheduledFlight> viewScheduledFlights(
			LocalDate deptDateTime, String srcAirport, String dstnAirport, Short noOfPassengers) throws ScheduledFlightNotFoundException {

		Collection<ScheduledFlight> scheduledFlights = dao.fetchByTimeAndLocation(
				deptDateTime,
				dstnAirport,
				srcAirport,
				noOfPassengers

		);
		if (scheduledFlights == null || scheduledFlights.isEmpty()) {
			throw new ScheduledFlightNotFoundException("No flights being scheduled.");
		}
		return scheduledFlights;
	}

}
