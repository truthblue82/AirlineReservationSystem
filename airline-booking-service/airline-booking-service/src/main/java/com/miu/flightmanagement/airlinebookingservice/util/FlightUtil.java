package com.miu.flightmanagement.airlinebookingservice.util;

import com.miu.flightmanagement.airlinebookingservice.dto.FlightDTO;
import com.miu.flightmanagement.airlinebookingservice.model.Flight;
import lombok.experimental.UtilityClass;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@UtilityClass
public class FlightUtil {

    public static Flight toFlight(@NonNull final FlightDTO flightDTO) {
        final Flight flight = new Flight();
        flight.setFlightNo(flightDTO.getFlightNo());
        flight.setFlightModel(flightDTO.getFlightModel());
        flight.setCarrierName(flightDTO.getCarrierName());
        flight.setSeatCapacity(flightDTO.getSeatCapacity());
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
        flight.setSeatCapacity(flightDTO.getSeatCapacity());
        flight.setFlightModel(flightDTO.getFlightModel());
        flight.setCarrierName(flightDTO.getCarrierName());

        return flight;
    }

    public static Collection<FlightDTO> flightDTOs(@NonNull final Iterable<Flight> flights) {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(flights.iterator(), Spliterator.ORDERED), false)
                .map(FlightUtil::toFlightDTO)
                .toList();
    }



}
