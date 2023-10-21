package com.miu.flightmanagement.airlinebookingservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ScheduleFlightRequest {
    private String flightNo;
    private String departureAirportCode;
    private String arrivalAirportCode;
    private String departureDateTime;
    private String arrivalDateTime;
}
