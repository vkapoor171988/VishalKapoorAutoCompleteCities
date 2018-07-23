package Cities.cities;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.poc.ds.trie.TrieNode;
import com.poc.rest.service.FetchCitiesService;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

	TrieNode node=new TrieNode();
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
		node.insert("chebrole");
		node.insert("chechapani");
		node.insert("cheepurupalle");
		node.insert("chegam");
		node.insert("chehra Kalan");
		node.insert("chejerla");
		node.insert("chennai");
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testForAddingNoValue() {
		List<String> list = new ArrayList<String>();
		String val = FetchCitiesService.autoComplete(null, null, node, list);
		assertTrue("Please enter valid search criterion!".equalsIgnoreCase(val));
	}

	public void testForAddingOnlyStartValue() {
		List<String> list = new ArrayList<String>();
		String val = FetchCitiesService.autoComplete("che", null, node, list);
		List<String> lines = new BufferedReader(new StringReader(val)).lines().collect(Collectors.toList());
		for (String s : lines) {
			System.out.print(s);
			System.out.print(" ");
		}
		assertTrue(lines != null && !lines.isEmpty());
	}

	public void testForAddingStartValueAndAtmostValue() {
		List<String> list = new ArrayList<String>();
		String val = FetchCitiesService.autoComplete("che", 5, node, list);
		List<String> lines = new BufferedReader(new StringReader(val)).lines().collect(Collectors.toList());
		for (String s : lines) {
			System.out.print(s);
			System.out.print(" ");
		}
		assertTrue(lines != null && !lines.isEmpty() && lines.size() <= 5);
	}

	public void testForAddingStartNullAndAtmostZero() {
		List<String> list = new ArrayList<String>();
		String val = FetchCitiesService.autoComplete(null, 0, node, list);
		assertTrue("Please enter valid search criterion!".equalsIgnoreCase(val));
	}

	public void testForAddingStartNull() {
		List<String> list = new ArrayList<String>();
		String val = FetchCitiesService.autoComplete(null, 6, node, list);
		assertTrue("Please enter valid search criterion!".equalsIgnoreCase(val));
	}

	public void testForAddingStartNullAndAtmostNegative() {
		List<String> list = new ArrayList<String>();
		String val = FetchCitiesService.autoComplete(null, -8, node, list);
		assertTrue("Please enter valid search criterion!".equalsIgnoreCase(val));
	}

	public void testForAddingStartValAndAtmostNegative() {
		List<String> list = new ArrayList<String>();
		String val = FetchCitiesService.autoComplete("che", -8, node, list);
		assertTrue("Please enter valid search criterion!".equalsIgnoreCase(val));
	}

}
