package com.saw.airport.service;

import java.io.ByteArrayOutputStream;
import java.util.Collections;

import org.eclipse.rdf4j.query.resultio.sparqljson.SPARQLResultsJSONWriter;
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository;
import org.springframework.stereotype.Service;

@Service
public class WikiDataSparqlService {

	public String getDataWiki(String code) {
		 String sparqlEndpoint = "https://query.wikidata.org/sparql";
	        SPARQLRepository repo = new SPARQLRepository(sparqlEndpoint);
	        ByteArrayOutputStream bao = new ByteArrayOutputStream();
	        String result = null;

	        String userAgent = "Wikidata RDF4J Java Example/0.1 (https://query.wikidata.org/)";
	        repo.setAdditionalHttpHeaders( Collections.singletonMap("User-Agent", userAgent ) );

	        String querySelect = "PREFIX wikibase: <http://wikiba.se/ontology#>\n" +
	                "PREFIX wdt: <http://www.wikidata.org/prop/direct/>\n" +
	                "\n" +
	                "SELECT ?item ?itemLabel ?itemDescription ?ciudadLabel ?imagen ?paisLabel ?poblacionPais  WHERE {\n" +
	                "   ?item wdt:P238 \"" + code.toUpperCase() +"\" .\n" +
	                "   ?item wdt:P18 ?imagen .\n" +
	                "  ?item wdt:P17 ?pais .\n" +
	                "  ?pais wdt:P1082 ?poblacionPais .\n" +
	                "  ?item wdt:P131 ?ciudad\n" +
	                "  SERVICE wikibase:label {\n" +
	                "    bd:serviceParam wikibase:language \"es, en\" .\n" +
	                "   }\n" +
	                "}";
	        
	        try{
	          repo.getConnection().prepareTupleQuery(querySelect).evaluate(new SPARQLResultsJSONWriter(bao));
	          result = bao.toString();	             
	         
	        } catch ( Exception exception ) {
	            exception.printStackTrace();
	        }
	        
	        return result;
		
		
	}

}
