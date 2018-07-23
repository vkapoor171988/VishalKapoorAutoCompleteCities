package com.poc.rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.poc.cities.fetch.FetchCities;
import com.poc.ds.trie.TrieNode;
import com.poc.rest.service.FetchCitiesService;

@Path("/")
public class FetchCitiesController implements RestInterface {

	@GET
	@Path(RestInterface.SUGGEST_CITIES)
	@Produces(MediaType.TEXT_PLAIN)
	public String fetchCitiesAPI(@QueryParam(RestInterface.START) String start, @QueryParam(RestInterface.ATMOST) Integer atmost) {
		FetchCities instance = FetchCities.getInstance();
		TrieNode node = instance.getNode();
		List<String> list = new ArrayList<String>();
		return FetchCitiesService.autoComplete(start, atmost, node, list);
	}
}
