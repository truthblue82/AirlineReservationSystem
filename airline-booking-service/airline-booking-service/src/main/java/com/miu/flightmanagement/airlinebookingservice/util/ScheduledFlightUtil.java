package com.miu.flightmanagement.airlinebookingservice.util;

import com.miu.flightmanagement.airlinebookingservice.dto.FlightDTO;
import com.miu.flightmanagement.airlinebookingservice.dto.ScheduledFlightDTO;
import com.miu.flightmanagement.airlinebookingservice.model.ScheduledFlight;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

@UtilityClass
public class ScheduledFlightUtil {

    public static ScheduledFlightDTO toScheduledFlightDTO(final ScheduledFlight scheduledFlight) {
        return ScheduledFlightDTO.builder()
                .scheduleFlightId(scheduledFlight.getScheduleFlightId())
                .availableSeats(scheduledFlight.getTemporaryAvailableSeats())
                .schedule(ScheduleUtil.toScheduleDTO(scheduledFlight.getSchedule()))
                .economicPrice(scheduledFlight.getEconomicPrice().toString())
                .flight(FlightDTO.builder()
                        .flightModel(scheduledFlight.getFlight().getFlightModel())
                        .flightNo(scheduledFlight.getFlight().getFlightNo())
                        .build()
                )
                .build();
    }

    public static Collection<ScheduledFlightDTO> toScheduledFlightDTOs(final Iterable<ScheduledFlight> scheduledFlights) {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(scheduledFlights.iterator(), Spliterator.ORDERED), false)
                .map(ScheduledFlightUtil::toScheduledFlightDTO)
                .toList();
    }

    public static ScheduledFlight transferDtoToEntity(@NonNull final ScheduledFlightDTO scheduledFlightDTO, @NonNull final ScheduledFlight scheduledFlight) {
        scheduledFlight.getSchedule().setArrDateTime(scheduledFlightDTO.getSchedule().getArrDateTime());
        scheduledFlight.getSchedule().setDeptDateTime(scheduledFlightDTO.getSchedule().getDeptDateTime());
//        scheduledFlight.getSchedule().getSrcAirport().setCode(scheduledFlightDTO.getSchedule().getSrcAirport().getCode());
//        scheduledFlight.getSchedule().getDstnAirport().setCode(scheduledFlightDTO.getSchedule().getSrcAirport().getCode());

//        scheduledFlight.getFlight().setFlightNo(scheduledFlightDTO.getFlight().getFlightNo());

        scheduledFlight.setEconomicPrice(NumberUtils.createBigDecimal(scheduledFlightDTO.getEconomicPrice()));

        return scheduledFlight;
    }
}
