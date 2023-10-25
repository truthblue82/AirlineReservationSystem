package com.miu.flightmanagement.airlinebookingservice.dao;

import com.miu.flightmanagement.airlinebookingservice.model.Booking;
import com.miu.flightmanagement.airlinebookingservice.model.ScheduledFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingDao extends JpaRepository<Booking, Long> {

    Booking findBookingByBookingCode(final String bookingCode);

    Boolean existsBookingByScheduledFlight(final ScheduledFlight scheduledFlight);
}
