package com.miu.flightmanagement.airlinebookingservice.dto;

import com.miu.flightmanagement.airlinebookingservice.model.ScheduledFlight;
import lombok.Data;

import java.util.Collection;

@Data
public class ScheduledFlightsDTO {
    private Collection<ScheduledFlight> scheduledFlights;
}
