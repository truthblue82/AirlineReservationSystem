package com.miu.flightmanagement.airlinebookingservice.service;

import com.miu.flightmanagement.airlinebookingservice.dao.ScheduleDao;
import com.miu.flightmanagement.airlinebookingservice.dao.ScheduledFlightDao;
import com.miu.flightmanagement.airlinebookingservice.exception.RecordNotFoundException;
import com.miu.flightmanagement.airlinebookingservice.exception.ScheduledFlightNotFoundException;
import com.miu.flightmanagement.airlinebookingservice.model.Schedule;
import com.miu.flightmanagement.airlinebookingservice.model.ScheduledFlight;
import org.springframework.beans.factory.annotation.Autowired;
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
	BookingService bookingService;

	/*
	 * Service method to add new Scheduled flight to database
	 */
	@Override
	public ScheduledFlight addScheduledFlight(ScheduledFlight scheduledFlight) {
		return dao.save(scheduledFlight);
	}

	/*
	 * Service method to modify existing Scheduled flight in database
	 */
	@Override
	public ScheduledFlight modifyScheduledFlight(ScheduledFlight scheduleFlight) {
		ScheduledFlight updateScheduleFlight = dao.findById(scheduleFlight.getScheduleFlightId()).get();
		Schedule updateSchedule = scheduleDao.findById(scheduleFlight.getSchedule().getScheduleId()).get();
		updateScheduleFlight.setAvailableSeats(scheduleFlight.getAvailableSeats());
		updateSchedule.setSrcAirport(scheduleFlight.getSchedule().getSrcAirport());
		updateSchedule.setDstnAirport(scheduleFlight.getSchedule().getDstnAirport());
		updateSchedule.setArrDateTime(scheduleFlight.getSchedule().getArrDateTime());
		updateSchedule.setDeptDateTime(scheduleFlight.getSchedule().getDeptDateTime());
		dao.save(updateScheduleFlight);
		return scheduleFlight;
	}

	/*
	 * Service method to remove existing Scheduled flight from database
	 */
	@Override
	public String removeScheduledFlight(Long flightId) throws RecordNotFoundException {
		if (flightId == null)
			throw new RecordNotFoundException("Enter flight Id");
		Optional<ScheduledFlight> scheduleFlight = dao.findById(flightId);
		if (!scheduleFlight.isPresent())
			throw new RecordNotFoundException("Enter a valid Flight Id");
		else {
			// try {
			// cancelBookings(flightId);
			// } catch (RecordNotFoundException e) {
			// System.out.println("No Bookings Found");
			// }
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
