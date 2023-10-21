package com.miu.flightmanagement.airlinebookingservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlightDTO {
    private String flightNo;
    private String carrierName;
    private String flightModel;
    private int seatCapacity;
}
