package com.saw.airport.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saw.airport.entities.Airport;
import com.saw.airport.service.ApiService;

@RestController
@RequestMapping("/api")
public class AirportRestController {

	@Autowired
	private ApiService apiService;

	@GetMapping("/airports")
	public List<Airport> getAirpots() {
		return apiService.getAllAirPorts();
	}

}
