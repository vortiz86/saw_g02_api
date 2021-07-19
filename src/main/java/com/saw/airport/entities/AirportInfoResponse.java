package com.saw.airport.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AirportInfoResponse {

	@JsonProperty("airports")
	private List<Airport> airports;

	public List<Airport> getAirports() {
		return airports;
	}

	public void setAirports(List<Airport> airports) {
		this.airports = airports;
	}

}
