package com.miu.flightmanagement.airlinebookingservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class AirportsDTO {
    private Collection<AirportDTO> airports;
}
