package com.saw.airport.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.saw.airport.entities.Airport;
import com.saw.airport.entities.Country;
import com.saw.airport.model.entities.Comentario;
import com.saw.airport.service.ApiService;
import com.saw.airport.service.IComentarioServiceImpl;
import com.saw.airport.service.WikiDataSparqlService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
@RequestMapping("/api")

public class AirportRestController {

	@Autowired
	private ApiService apiService;

	@Autowired
	private WikiDataSparqlService wikidata;

	@Autowired
	private IComentarioServiceImpl comentarioService;

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
	public Object getWikiData(@PathVariable String id) {
		return wikidata.getDataWiki(id);
	}

	@GetMapping("/comment/{id}")
	public Object getComments(@PathVariable String id) {
		return comentarioService.getComentarios(id);
	}

	@PostMapping("/comment")
	public ResponseEntity<?> createComment(@Valid @RequestBody Comentario comentario, BindingResult result) {
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			comentarioService.save(comentario);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Comentario creado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

}
