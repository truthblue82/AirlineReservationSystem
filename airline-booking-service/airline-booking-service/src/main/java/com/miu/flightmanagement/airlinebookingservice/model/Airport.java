package com.miu.flightmanagement.airlinebookingservice.model;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "airport")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Airport
{
	@Id
	private String code;
	private String location;
	private String name;

}
