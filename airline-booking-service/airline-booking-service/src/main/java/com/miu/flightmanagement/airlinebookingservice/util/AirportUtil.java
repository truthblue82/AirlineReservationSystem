package com.miu.flightmanagement.airlinebookingservice.util;

import com.miu.flightmanagement.airlinebookingservice.dto.AirportDTO;
import com.miu.flightmanagement.airlinebookingservice.model.Airport;
import lombok.experimental.UtilityClass;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.stream.Stream;

import static java.util.stream.Stream.iterate;

@UtilityClass
public class AirportUtil {

    public static AirportDTO toAirportDTO(@NonNull final Airport airport) {
        return AirportDTO.builder()
                .code(airport.getCode())
                .name(airport.getName())
                .location(airport.getLocation())
                .build();
    }

    public static Airport transferFromDTOtoEntity(@NonNull final AirportDTO airportDTO, @NonNull final Airport airport) {
        airport.setName(airportDTO.getName());
        airport.setLocation(airportDTO.getLocation());
        return airport;
    }

    public static Airport toAirport(@NonNull final AirportDTO airportDTO) {
        final Airport airport = new Airport();
        airport.setCode(airport.getCode());
        airport.setName(airport.getName());
        airport.setLocation(airport.getLocation());

        return airport;
    }

    public static Collection<AirportDTO> airportDTOs(@NonNull Iterable<Airport> airports) {
        return Stream.generate(airports.iterator()::next)
                .map(AirportUtil::toAirportDTO)
                .toList();
    }
}
