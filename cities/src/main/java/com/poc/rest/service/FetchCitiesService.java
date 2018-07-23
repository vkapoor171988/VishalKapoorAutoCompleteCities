package com.poc.rest.service;

import java.util.List;

import com.poc.cities.fetch.FetchUtility;
import com.poc.ds.trie.TrieNode;
import com.poc.rest.controller.RestInterface;

public class FetchCitiesService {

	private static String fetchFromList(List<String> list) {
		StringBuilder build = new StringBuilder();
		for (String s : list) {
			build.append(FetchUtility.camelCase(s));
			build.append("\n");
		}
		return build.toString();
	}

	public static String autoComplete(String start, Integer atmost, TrieNode node, List<String> list) {
		if (atmost == null && start != null && !start.isEmpty()) {
			list = (List<String>) node.autoComplete(start);
		} else if (atmost != null && start != null && !start.isEmpty() && atmost.intValue() > 0) {
			list = (List<String>) node.autoComplete(start, atmost);
		} else {
			return RestInterface.WRONG_VAL_HANDLE;
		}
		return FetchCitiesService.fetchFromList(list);
	}

}
