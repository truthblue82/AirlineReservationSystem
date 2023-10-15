package com.org.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Airport
/**
 * Class Airport
 */
{
	@Id
	private String code;
	private String location;
	private String name;

	public Airport(String airportName, String airportLocation, String airportCode)
	/**
	 * parameterized Constructor
	 */
	{
		this.name = airportName;
		this.location = airportLocation;
		this.code = airportCode;
	}

	public Airport()
	/**
	 * Unparameterized Constructor
	 */
	{
	}

	public String getAirportName() {
		return name;
	}

	public void setAirportName(String airportName) {
		this.name = airportName;
	}

	public String getAirportLocation() {
		return location;
	}

	public void setAirportLocation(String airportLocation) {
		this.location = airportLocation;
	}

	public String getAirportCode() {
		return code;
	}

	public void setAirportCode(String airportCode) {
		this.code = airportCode;
	}

	@Override
	public String toString() {
		return "Airport{" + "airportName='" + name + '\'' + ", airportLocation='" + location + '\''
				+ ", airportCode='" + code + '\'' + '}';
	}
}
