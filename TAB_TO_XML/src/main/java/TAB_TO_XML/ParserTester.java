package TAB_TO_XML;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.Test;
import java.io.*;
import TAB_TO_XML.Parser;

class ParserTester {

	@Test
	void extractStringsTest() {
		ArrayList<String> storeFile = new ArrayList<>();
		storeFile = Parser.readLineByLine("C:\\Users\\Rober\\git\\EECS2311_GROUP5_TAB_TO_MUSICXML\\TAB_TO_XML\\example");
		ArrayList<String> testArr = new ArrayList<>();
		testArr = Parser.extractStrings(storeFile);
		assertEquals("|-----------0-----|-0---------------|", testArr.get(0));
	}
	
	@Test
	void reduceRootTest() {
		ArrayList<String> storeFile = new ArrayList<>();
		storeFile = Parser.readLineByLine("C:\\Users\\Rober\\git\\EECS2311_GROUP5_TAB_TO_MUSICXML\\TAB_TO_XML\\example");
		storeFile = Parser.reduceRoot(storeFile);
		assertEquals(0, storeFile.size());
	}
	
	@Test
	void listReductionTest() {
		ArrayList<String> storeFile = new ArrayList<>();
		storeFile = Parser.readLineByLine("C:\\Users\\Rober\\git\\EECS2311_GROUP5_TAB_TO_MUSICXML\\TAB_TO_XML\\example");
		ArrayList<String> testArr = new ArrayList<>();
		testArr = Parser.extractStrings(storeFile);
		testArr = Parser.listReduction(testArr);
		assertEquals("---------0-----|-0---------------|", testArr.get(0));
	}
	
	@Test
	void findLongerListTest() {
		ArrayList<String> storeFile = new ArrayList<>();
		storeFile = Parser.readLineByLine("C:\\Users\\Rober\\git\\EECS2311_GROUP5_TAB_TO_MUSICXML\\TAB_TO_XML\\example");
		ArrayList<String> testArr = new ArrayList<>();
		testArr = Parser.extractStrings(storeFile);
		int a = Parser.findLongerList(testArr);
		assertEquals(0, a);
	}
	
	@Test
	void durationCountTest() {
		ArrayList<String> storeFile = new ArrayList<>();
		storeFile = Parser.readLineByLine("C:\\Users\\Rober\\git\\EECS2311_GROUP5_TAB_TO_MUSICXML\\TAB_TO_XML\\example");
		ArrayList<String> testArr = new ArrayList<>();
		testArr = Parser.extractStrings(storeFile);
		testArr = Parser.listReduction(testArr);
		String a = Parser.durationCount(testArr);
		assertEquals("2", a);
	}
	
	@Test
	void divisionCountTest() {
		ArrayList<String> storeFile = new ArrayList<>();
		storeFile = Parser.readLineByLine("C:\\Users\\Rober\\git\\EECS2311_GROUP5_TAB_TO_MUSICXML\\TAB_TO_XML\\example");
		ArrayList<String> testArr = new ArrayList<>();
		testArr = Parser.extractStrings(storeFile);
		int a = Parser.divisionCount(testArr);
		assertEquals(4, a);
	}
	
	@Test
	void fretCountTest() {
		ArrayList<String> storeFile = new ArrayList<>();
		storeFile = Parser.readLineByLine("C:\\Users\\Rober\\git\\EECS2311_GROUP5_TAB_TO_MUSICXML\\TAB_TO_XML\\example");
		ArrayList<String> testArr = new ArrayList<>();
		testArr = Parser.extractStrings(storeFile);
		testArr = Parser.listReduction(testArr);
		testArr = Parser.listReduction(testArr);
		String a = Parser.fretCount(testArr);
		assertEquals("2", a);
	}
	
	@Test
	void stepCountTest() {
		ArrayList<String> storeFile = new ArrayList<>();
		storeFile = Parser.readLineByLine("C:\\Users\\Rober\\git\\EECS2311_GROUP5_TAB_TO_MUSICXML\\TAB_TO_XML\\example");
		ArrayList<String> testArr = new ArrayList<>();
		testArr = Parser.extractStrings(storeFile);
		testArr = Parser.listReduction(testArr);
		String a = Parser.StepCount(testArr);
		assertEquals("E", a);
	}
}
//
//storeFile.add("|-----------0-----|-0---------------|");
//storeFile.add("|---------0---0---|-0---------------|");
//storeFile.add("|-------1-------1-|-1---------------|");
//storeFile.add("|-----2-----------|-2---------------|");
//storeFile.add("|---2-------------|-2---------------|");
//storeFile.add("|-0---------------|-0---------------|");
//int n = p.divisionCount(storeFile);
//System.out.println(n);
