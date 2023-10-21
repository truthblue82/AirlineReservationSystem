package com.miu.flightmanagement.airlinebookingservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class ScheduleDTO {

    private AirportDTO srcAirport;

    private AirportDTO dstnAirport;

    private OffsetDateTime deptDateTime;

    private OffsetDateTime arrDateTime;
}
