package com.saw.airport.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.saw.airport.entities.Airport;
import com.saw.airport.entities.AirportInfoResponse;
import com.saw.airport.entities.Country;

@Service
public class ApiService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${URL_API_AIRPORTS}")
	private String URL_API_AIRPORTS;

	@Value("${URL_API_COUNTRIES}")
	private String URL_API_COUNTRIES;

	private List<Airport> airports;
	private List<Country> countriesList;

	@Cacheable(cacheNames = "airports")
	public List<Airport> getAllAirPorts() {
		if (airports == null) {
			airports = restTemplate.getForObject(URL_API_AIRPORTS, AirportInfoResponse.class).getAirports();
			airports.removeIf(o -> o.getIata() == "");
			airports.removeIf(o -> o.getIata() == null);
		}
		return airports;
	}

	public Country getCountryStatus(String code) {
		if (countriesList == null) {
			ResponseEntity<Country[]> countries = restTemplate.getForEntity(URL_API_COUNTRIES, Country[].class);
			Country[] countriesArray = countries.getBody();
			countriesList = Arrays.asList(countriesArray);
		}
		for (Country country : countriesList) {
			if (country.getCountryCode().contentEquals(code.toUpperCase())) {
				return country;
			}
		}
		return null;
	}

	public Airport getAirPortById(String id) {
		if (airports == null) {
			airports = getAllAirPorts();
		}
		for (Airport airport : airports) {
			if (airport.getIata() != null && airport.getIata().equalsIgnoreCase(id.toUpperCase())) {
				return airport;
			}
		}
		return null;
	}
}
