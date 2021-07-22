package com.saw.airport.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.saw.airport.entities.Airport;
import com.saw.airport.entities.Country;
import com.saw.airport.service.ApiService;
import com.saw.airport.service.WikiDataSparqlService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
@RequestMapping("/api")

public class AirportRestController {

	@Autowired
	private ApiService apiService;

	@Autowired
	private WikiDataSparqlService wikidata;

	@GetMapping("/airports")
	public List<Airport> getAirpots() {
		return apiService.getAllAirPorts();
	}

	@GetMapping("/countries/{id}")
	public ResponseEntity<?> getCountry(@PathVariable String id) {
		Map<String, Object> response = new HashMap<>();
		Country country = apiService.getCountryStatus(id);
		if (country == null) {
			response.put("mensaje", "Codigo pais: ".concat(id.toString().concat(" no existe ")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(country, HttpStatus.OK);
	}

	@GetMapping("/wikidata/{id}")
	public String getWikiData(@PathVariable String id) {
		return wikidata.getDataWiki(id);
	}

}
