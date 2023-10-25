package com.miu.flightmanagement.airlinebookingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeatReservation {
    private Long scheduledFlightId;
    private Short numberOfSeats;
}
