package com.miu.flightmanagement.airlinebookingservice.model;

import jakarta.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "airport")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Airport
{
	@Id
	private String code;
	private String location;
	private String name;

	public Airport(String airportName, String airportLocation, String airportCode)
	{
		this.name = airportName;
		this.location = airportLocation;
		this.code = airportCode;
	}

	public Airport(){}

}
