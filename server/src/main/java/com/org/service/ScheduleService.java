package com.org.service;

import com.org.dao.ScheduleDao;
import com.org.model.Schedule;
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
       return scheduleDao.findByDeptDateTimeAndArrDateTimeAndDstnAirportAndSrcAirport(deptDateTime, arrDateTime, dstnAirport, srcAirport).stream()
               .findFirst().orElse(null);
    }
}
