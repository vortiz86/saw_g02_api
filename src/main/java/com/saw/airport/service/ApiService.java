package com.saw.airport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.saw.airport.entities.Airport;
import com.saw.airport.entities.AirportInfoResponse;

@Service
public class ApiService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${URL_API_AIRPORTS}")
	private String URL_API_AIRPORTS;

	@Cacheable(cacheNames = "airports")
	public List<Airport> getAllAirPorts() {
		List<Airport> airports = restTemplate.getForObject(URL_API_AIRPORTS, AirportInfoResponse.class).getAirports();
		airports.removeIf(o -> o.getIata() == "");
		return airports;
	}

}
