package com.miu.flightmanagement.airlinebookingservice.dto;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.Collection;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

    private String bookingCode;
    private String bookingDate;
    private Integer noOfPassengers;
    private Collection<PassengerDTO> passengers;
    private ScheduledFlightDTO scheduledFlight;
}
