package com.miu.flightmanagement.airlinebookingservice.util;

import com.miu.flightmanagement.airlinebookingservice.dto.FlightDTO;
import com.miu.flightmanagement.airlinebookingservice.model.Flight;
import lombok.experimental.UtilityClass;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.stream.Stream;

@UtilityClass
public class FlightUtil {

    public static Flight toFlight(@NonNull final FlightDTO flightDTO) {
        final Flight flight = new Flight();
        flight.setFlightNo(flight.getFlightNo());
        flight.setFlightModel(flight.getFlightModel());
        flight.setCarrierName(flight.getCarrierName());
        flight.setSeatCapacity(flight.getSeatCapacity());
        return flight;
    }

    public static FlightDTO toFlightDTO(@NonNull final Flight flight) {
        return FlightDTO.builder()
                .flightModel(flight.getFlightModel())
                .flightNo(flight.getFlightNo())
                .carrierName(flight.getCarrierName())
                .seatCapacity(flight.getSeatCapacity())
                .build();
    }

    public static Flight transferFromDTOtoEntity(@NonNull final FlightDTO flightDTO, @NonNull final Flight flight) {
        flight.setSeatCapacity(flight.getSeatCapacity());
        flight.setFlightModel(flight.getFlightModel());
        flight.setCarrierName(flight.getCarrierName());

        return flight;
    }

    public static Collection<FlightDTO> flightDTOs(@NonNull final Iterable<Flight> flights) {
        return Stream.generate(flights.iterator()::next)
                .map(FlightUtil::toFlightDTO)
                .toList();
    }



}
