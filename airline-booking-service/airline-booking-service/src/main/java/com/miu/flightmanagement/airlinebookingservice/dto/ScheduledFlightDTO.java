package com.miu.flightmanagement.airlinebookingservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScheduledFlightDTO {
    private Long scheduleFlightId;

    private FlightDTO flight;

    private Integer availableSeats;

    private ScheduleDTO schedule;
}
