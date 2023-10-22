package com.miu.flightmanagement.airlinebookingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AirportsDTO {
    private Collection<AirportDTO> airports;
}
