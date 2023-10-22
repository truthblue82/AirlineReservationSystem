package com.miu.flightmanagement.airlinebookingservice.dao;

import com.miu.flightmanagement.airlinebookingservice.model.ScheduledFlight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduledFlightDao extends CrudRepository<ScheduledFlight,Long>{

    @Query("SELECT entity FROM ScheduledFlight entity WHERE "
            + "date(entity.schedule.deptDateTime) = date(:deptDateTime) "
            + "AND entity.schedule.dstnAirport.code = :dstnAirport "
            + "AND entity.schedule.srcAirport.code = :srcAirport "
            + "AND entity.temporaryAvailableSeats >= :noOfPassengers"
    )
    List<ScheduledFlight> fetchByTimeAndLocation(
            final LocalDate deptDateTime,
            final String dstnAirport,
            final String srcAirport,
            final Short noOfPassengers
    );
}
