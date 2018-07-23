package com.poc.ds.trie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieNode implements TrieNodeInterface {

	// saving all the characters and the combinations in trie data
	// structure(PREFIX TREE)
	// each node will act as a Trie Node
	HashMap<Character, TrieNode> trieNodeChildren;

	// content data of each node
	String content = EMPTY;

	// if the whole String word is complete at this node, boolean check to
	// identify the complete
	// word
	boolean isWordEnd;

	// constructor to add content value in trie node
	private TrieNode(String content) {
		this.content = content;
		trieNodeChildren = new HashMap<Character, TrieNode>();
	}

	// Initial node setup constructor
	public TrieNode() {
		this.content = null;
		trieNodeChildren = new HashMap<Character, TrieNode>();
	}

	// adding a character in the TRIE Data Structure
	// if the content of the node is null- add the character as string in node
	// else append with the already present content
	// and add this node with the character passed as key and node as value in
	// children hashmap
	public void add(char character) {
		// variable - content of the node
		String build;
		if (this.content == null) {
			build = Character.toString(character);
		} else {
			build = this.content + character;
		}
		trieNodeChildren.put(character, new TrieNode(build));
	}

	// add a string in trie data structure
	// conversion of string value to character array and
	// if the char is already prent in children map just get the node
	// else add the character in node map
	public void insert(String value) {
		if (value == null) {
			throw new IllegalArgumentException(NULL_EXCEPTION);
		}
		TrieNode node = this;
		for (char c : value.toCharArray()) {
			if (!node.trieNodeChildren.containsKey(c)) {
				node.add(c);
			}
			node = node.trieNodeChildren.get(c);
		}
		node.isWordEnd = true;
	}

	// fetching all the words as per the prefix given from trie
	// convert prefix to char array and check if the trie node map contains the
	// key or not
	// else return empty list
	// if it is present then add childNode to your node and move ahead with the
	// char array
	public Collection<String> autoComplete(String prefix) {
		if (prefix != null && !prefix.isEmpty()) {
			TrieNode trienode = this;
			for (char c : prefix.toCharArray()) {
				if (!trienode.trieNodeChildren.containsKey(c)) {
					return Collections.emptyList();
				}
				trienode = trienode.trieNodeChildren.get(c);
			}
			return trienode.allPrefixes();
		} else {
			return Collections.emptyList();
		}
	}

	// fetch all the prefix matching words related to the node calling this
	// method
	public Collection<String> allPrefixes() {
		List<String> result = new ArrayList<String>();
		if (this.isWordEnd) {
			result.add(this.content);
		}
		for (Map.Entry<Character, TrieNode> entry : trieNodeChildren.entrySet()) {
			TrieNode child = entry.getValue();
			Collection<String> childPrefixes = child.allPrefixes();
			result.addAll(childPrefixes);
		}
		return result;
	}

	// fetching all the words as per the prefix given from trie
	// convert prefix to char array and check if the trie node map contains the
	// key or not
	// else return empty list
	// if it is present then add childNode to your node and move ahead with the
	// char array
	// formatting the result upto max of numberOfAtmostValues
	public synchronized Collection<String> autoComplete(String prefix, int numberOfAtmostValues) {
		if (prefix != null && !prefix.isEmpty()) {
			TrieNode trienode = this;
			TrieNode.Counter.counter = numberOfAtmostValues;
			for (char c : prefix.toCharArray()) {
				if (!trienode.trieNodeChildren.containsKey(c)) {
					return Collections.emptyList();
				}
				trienode = trienode.trieNodeChildren.get(c);
			}
			List<String> result = trienode.restrictPrefixes(numberOfAtmostValues);
			TrieNode.Counter.counter = 0;
			return result;
		} else {
			return Collections.emptyList();
		}
	}

	// fetch all the prefix matching words related to the node calling this
	// method
	// with restriction of number of threads mentioned
	@Override
	public synchronized List<String> restrictPrefixes(int numberOfAtmostValues) {
		List<String> result = new ArrayList<String>();
		if (this.isWordEnd) {
			result.add(this.content);
			--TrieNode.Counter.counter;

		}
		if (TrieNode.Counter.counter > 0) {
			for (Map.Entry<Character, TrieNode> entry : trieNodeChildren.entrySet()) {
				TrieNode child = entry.getValue();
				Collection<String> childPrefixes = child.restrictPrefixes(numberOfAtmostValues);
				result.addAll(childPrefixes);
			}
		}
		return result;
	}

	// counter in inner class to restrict the number of results
	static class Counter {
		static int counter = 0;
	}
}
