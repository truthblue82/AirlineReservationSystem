package com.miu.flightmanagement.airlinebookingservice.dao;

import com.miu.flightmanagement.airlinebookingservice.model.Airport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportDao extends CrudRepository<Airport, String> {

}
