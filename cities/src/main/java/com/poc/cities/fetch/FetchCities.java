package com.poc.cities.fetch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collection;
import java.util.logging.Logger;

import com.poc.ds.trie.TrieNode;
import com.poc.singleton.TrieNodeInstance;

public class FetchCities {

	public static Logger log = Logger.getLogger(FetchCitiesInterface.FetchCities);

	// root node of trie data algorithm
	static TrieNode node = new TrieNode();

	public static FetchCities instance = null;

	private FetchCities() {
	}

	// fetch all the cities from the file mentioned
	// adding resources in the try block
	// and adding node in TRIE Data Structure
	public static void fetchCities(String filename) {
		log.info("Enter:FetchCities:fetchCities:input=" + filename);
		// adding input stream and buffered reader to read data from file
		// kept in server classpath
		if (filename != null) {
			try (InputStream inputStream = FetchCities.class.getResourceAsStream(filename);
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));) {
				String line = "";
				while ((line = bufferedReader.readLine()) != null) {
					if (line != null && !line.isEmpty()
							&& !FetchCitiesInterface.NA.equalsIgnoreCase(line.toLowerCase())) {
						node.insert(FetchUtility.format(line));
					}
				}
				log.info("Exit:FetchCities:fetchCities");
			} catch (IOException e) {
				log.info("Exception While reading data from cities" + e);
			}
		}
	}

	public static FetchCities getInstance() {
		// Bill Pugh Singleton for providing instance of FetchCities Utility
		if (instance == null) {
			synchronized (FetchCities.class) {
				if (instance == null) {
					instance = new FetchCities();
					instance = TrieNodeInstance.fetchCities(instance);
				}
			}
		}
		return instance;
	}

	public TrieNode getNode() {
		return node;
	}

	// utility for testing purposes
	public static void printTheValues(String prefix) {
		Collection<String> values = node.autoComplete(prefix);
		for (String s : values) {
			System.out.println(s);
		}

	}

	// utility for testing purposes
	public static void printTheValues(String prefix, int numberOfAtmostVariables) {
		Collection<String> values = node.autoComplete(prefix, numberOfAtmostVariables);
		for (String s : values) {
			System.out.println(s);
		}

	}
}
