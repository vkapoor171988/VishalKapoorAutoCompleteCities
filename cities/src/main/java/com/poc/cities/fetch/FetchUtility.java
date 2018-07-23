package com.poc.cities.fetch;

import java.util.logging.Logger;

public class FetchUtility {

	public static Logger log = Logger.getLogger("FetchUtility");

	// utility to convert text to Camel Case and removing unwanted spaces
	public static String camelCase(String letter) {
		// log.info("Entry for Camel Case:"+letter);
		StringBuilder build = new StringBuilder("");
		if (letter != null && !letter.isEmpty()) {
			try {
				String[] valueArr = letter.split(" ");
				for (int i = 0; i < valueArr.length; i++) {
					build.append(valueArr[i].substring(0, 1).toUpperCase() + valueArr[i].substring(1));
					if (i != valueArr.length - 1 && valueArr.length > 1) {
						build.append(" ");
					}
				}
			} catch (Exception e) {
				log.info("Exception for letter " + letter + " is=" + e);
			}
		}
		// log.info("Exit for Camel Case:"+build.toString());
		return build.toString();
	}

	// utility to lower the case and remove unwanted spaces
	public static String format(String letter) {
		if (letter != null && !letter.isEmpty()) {
			try {
				letter = letter.toLowerCase().replaceAll("\\s{2,}", " ").trim();
			} catch (Exception e) {
				log.info("Exception for letter " + letter + " is=" + e);
			}
		}
		return letter;
	}

}
