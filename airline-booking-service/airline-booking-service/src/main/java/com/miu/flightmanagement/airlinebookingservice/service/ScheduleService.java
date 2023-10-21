package com.miu.flightmanagement.airlinebookingservice.service;

import com.miu.flightmanagement.airlinebookingservice.dao.ScheduleDao;
import com.miu.flightmanagement.airlinebookingservice.model.Airport;
import com.miu.flightmanagement.airlinebookingservice.model.Schedule;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class ScheduleService {

    private final ScheduleDao scheduleDao;

    public Schedule fetchByTimeAndLocation(
            final OffsetDateTime deptDateTime,
            final OffsetDateTime arrDateTime,
            final String dstnAirport,
            final String srcAirport
    ) {
        final Airport destAirport = new Airport();
        destAirport.setCode(dstnAirport);
        final Airport arrAirport = new Airport();
        arrAirport.setCode(srcAirport);
       return scheduleDao.findByDeptDateTimeAndArrDateTimeAndDstnAirportAndSrcAirport(deptDateTime, arrDateTime, destAirport, arrAirport).stream()
               .findFirst().orElse(null);
    }
}
