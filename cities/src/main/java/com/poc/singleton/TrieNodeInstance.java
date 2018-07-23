package com.poc.singleton;

import com.poc.cities.fetch.FetchCities;

public class TrieNodeInstance {

	private static FetchCities cities = null;

	private TrieNodeInstance() {
	}

	public static FetchCities fetchCities(FetchCities instance) {
		cities = instance;
		cities.fetchCities("//resources//cities.txt");
		return cities;
	}
}
