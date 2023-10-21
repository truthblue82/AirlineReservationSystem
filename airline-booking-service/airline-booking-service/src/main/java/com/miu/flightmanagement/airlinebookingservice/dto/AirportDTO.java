package com.miu.flightmanagement.airlinebookingservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirportDTO {
    private String code;
    private String name;
    private String location;
}
