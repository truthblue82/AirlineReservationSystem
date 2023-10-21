package com.miu.flightmanagement.airlinebookingservice.dao;

import com.miu.flightmanagement.airlinebookingservice.model.Airport;
import com.miu.flightmanagement.airlinebookingservice.model.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface ScheduleDao extends CrudRepository<Schedule, Long> {

    List<Schedule> findByDeptDateTimeAndArrDateTimeAndDstnAirportAndSrcAirport(
            final OffsetDateTime deptDateTime,
            final OffsetDateTime arrDateTime,
            final Airport dstnAirport,
            final Airport srcAirport
            );

}
