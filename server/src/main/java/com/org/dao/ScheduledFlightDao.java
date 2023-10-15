package com.org.dao;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.org.model.ScheduledFlight;

@Repository
public interface ScheduledFlightDao extends CrudRepository<ScheduledFlight,Long>{

    @Query("SELECT entity FROM ScheduledFlight entity WHERE "
            + "entity.schedule.arrDateTime = :arrDateTime "
            + "AND entity.schedule.deptDateTime = :deptDateTime "
            + "AND entity.schedule.dstnAirport.code = :dstnAirport "
            + "AND entity.schedule.srcAirport.code = :srcAirport"
    )
    List<ScheduledFlight> fetchByTimeAndLocation(
            final OffsetDateTime deptDateTime,
            final OffsetDateTime arrDateTime,
            final String dstnAirport,
            final String srcAirport
    );
}
