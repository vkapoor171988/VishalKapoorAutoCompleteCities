package com.poc.ds.trie;

import java.util.Collection;

//Implementation of TRIE Data Structure
public interface TrieNodeInterface {
	// adding literals as constants
	public static final String EMPTY = "";
	public static final String NULL_EXCEPTION = "Null values are not allowed";

	// method to add a character in the child node in trie
	public void add(char character);

	// to insert string (city) in trie data structure which calls add method
	// to insert nodes character by character
	public void insert(String value);

	// traversing all the words having same prefix
	public Collection<String> autoComplete(String prefix);

	// fetching all prefixes from trie data structure and used in autoComplete
	// method
	public Collection<String> allPrefixes();

	// traversing all the words having same prefix and atmost values to be shown
	public Collection<String> autoComplete(String prefix, int numberOfAtmostValues);

	// number of Prefixes to be restricted by number given and used in auto
	// complete method
	public Collection<String> restrictPrefixes(int numberOfAtmostValues);

}
