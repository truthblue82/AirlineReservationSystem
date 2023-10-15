package com.org.dto;

import com.org.model.ScheduledFlight;
import lombok.Data;

import java.util.Collection;

@Data
public class ScheduledFlightDTO {
    private Collection<ScheduledFlight> scheduledFlights;
}
