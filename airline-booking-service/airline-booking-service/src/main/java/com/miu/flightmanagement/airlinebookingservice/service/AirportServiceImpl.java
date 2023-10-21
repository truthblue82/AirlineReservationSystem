package com.miu.flightmanagement.airlinebookingservice.service;

import com.miu.flightmanagement.airlinebookingservice.dao.AirportDao;
import com.miu.flightmanagement.airlinebookingservice.dto.AirportDTO;
import com.miu.flightmanagement.airlinebookingservice.exception.RecordAlreadyPresentException;
import com.miu.flightmanagement.airlinebookingservice.exception.RecordNotFoundException;
import com.miu.flightmanagement.airlinebookingservice.model.Airport;
import com.miu.flightmanagement.airlinebookingservice.util.AirportUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AirportServiceImpl implements AirportService {

	private final AirportDao airportDao;

	public Collection<AirportDTO> viewAllAirport() {
		return AirportUtil.airportDTOs(airportDao.findAll());
	}

	public Airport viewAirport(String airportCode) {
		Optional<Airport> findById = airportDao.findById(airportCode);
		if (findById.isPresent()) {
			return findById.get();
		} else
			throw new RecordNotFoundException("Airport with airport code: " + airportCode + "not exists");
	}

	@Override
	public void addAirport(AirportDTO airportDTO) {
		Optional<Airport> maybeAirport = airportDao.findById(airportDTO.getCode());
		if (maybeAirport.isPresent()) {
			throw new RecordAlreadyPresentException(
					"Airport with code : " + airportDTO.getCode() + " already present");
		} else {
			airportDao.save(AirportUtil.toAirport(airportDTO));
		}
	}

	@Override
	public Airport modifyAirport(AirportDTO airportDTO, String code) {
		Optional<Airport> maybeAirport = airportDao.findById(airportDTO.getCode());
		return maybeAirport.map(airport -> airportDao.save(AirportUtil.transferFromDTOtoEntity(airportDTO, airport)))
				.orElseThrow(() -> new RecordNotFoundException("Airport with code: " + airportDTO.getCode() + " not exists"));
	}

	public void removeAirport(String airportCode) {
		Optional<Airport> findById = airportDao.findById(airportCode);
		if (findById.isPresent()) {
			airportDao.deleteById(airportCode);
		} else
			throw new RecordNotFoundException("Airport with code: " + airportCode + " not exists");

	}
}
