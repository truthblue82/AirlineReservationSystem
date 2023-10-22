package com.miu.flightmanagement.airlinebookingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightsDTO {
    private Collection<FlightDTO> flights;
}
