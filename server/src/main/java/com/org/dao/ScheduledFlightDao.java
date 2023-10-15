package com.org.dao;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.org.model.ScheduledFlight;

@Repository
public interface ScheduledFlightDao extends CrudRepository<ScheduledFlight,Long>{

    @Query("SELECT entity FROM ScheduledFlight entity WHERE "
            + "date(entity.schedule.arrDateTime) = date(:arrDateTime) "
            + "AND date(entity.schedule.deptDateTime) = date(:deptDateTime) "
            + "AND entity.schedule.dstnAirport.code = :dstnAirport "
            + "AND entity.schedule.srcAirport.code = :srcAirport"
    )
    List<ScheduledFlight> fetchByTimeAndLocation(
            final LocalDate deptDateTime,
            final LocalDate arrDateTime,
            final String dstnAirport,
            final String srcAirport
    );
}
