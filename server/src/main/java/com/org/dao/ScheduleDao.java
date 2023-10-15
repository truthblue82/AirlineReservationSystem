package com.org.dao;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.org.model.Schedule;

@Repository
public interface ScheduleDao extends CrudRepository<Schedule, Long> {

    List<Schedule> findByDeptDateTimeAndArrDateTimeAndDstnAirportAndSrcAirport(
            final OffsetDateTime deptDateTime,
            final OffsetDateTime arrDateTime,
            final String dstnAirport,
            final String srcAirport
            );

}
