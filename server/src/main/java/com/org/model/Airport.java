package com.org.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "airport")
@Getter
@Setter
@ToString
@EqualsAndHashCode
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
}
