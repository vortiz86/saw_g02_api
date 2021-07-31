package com.saw.airport.service;

import java.io.ByteArrayOutputStream;
import java.util.Collections;

import org.eclipse.rdf4j.query.resultio.sparqljson.SPARQLResultsJSONWriter;
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class WikiDataSparqlService {
	
	@Value("${URL_API_WIKIDATA}")
	private String sparqlEndpoint;

	@Value("${WIKIDATA_QUERY_PART1}")
	private String queryPart1;
	
	@Value("${WIKIDATA_QUERY_PART2}")
	private String queryPart2;
	
	@Value("${WIKIDATA_RDF_QUERY}")
	private String WIKIDATA_RDF_QUERY;
	
	@Value("${WIKIDATA_RDF_EXT}")
	private String WIKIDATA_RDF_EXT;
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Object getDataWiki(String code) {
		
		SPARQLRepository repo = new SPARQLRepository(sparqlEndpoint);
		ByteArrayOutputStream bao = new ByteArrayOutputStream();

		String userAgent = "Wikidata RDF4J Java Example/0.1 (https://query.wikidata.org/)";
		repo.setAdditionalHttpHeaders(Collections.singletonMap("User-Agent", userAgent));

		ObjectMapper mapper = new ObjectMapper();

		Object object = null;
		String querySelect = queryPart1+" \""+code+"\" "+queryPart2;

		try {
			repo.getConnection().prepareTupleQuery(querySelect).evaluate(new SPARQLResultsJSONWriter(bao));			
			object = mapper.readValue(new String(bao.toString()), Object.class);
					} catch (Exception exception) {
			exception.printStackTrace();
		}
		

		return object;

	}
	
	
	public Object getRdfResponse(String code) {		
	//	ArrayList al1 = (ArrayList) getDataWiki(code);
		Object rdf = new Object();
		rdf = restTemplate.getForEntity(WIKIDATA_RDF_QUERY+code+WIKIDATA_RDF_EXT, Object.class);
		return rdf;
	}

}
