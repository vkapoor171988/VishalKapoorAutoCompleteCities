package com.poc.rest.controller;

public interface RestInterface {

	public static final String WRONG_VAL_HANDLE = "Please enter valid search criterion!";

	public static final String START="start";

	public static final String ATMOST="atmost";
	
	public static final String SUGGEST_CITIES="suggest_cities";
	
	public static final String NEXT_LINE="\n";
	

	public String fetchCitiesAPI(String start, Integer atmost);

}
