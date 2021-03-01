package TAB_TO_XML;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class Tester {


	
	/*
	 * This method is executed before each test method is executed.
	 */
	@Test
	public void test_durationCount_01() {
		ArrayList<String> a = new ArrayList<>();
		a.add("-----------0-----");
		a.add("---------0---0---");
		a.add("-------1-------1-");
		a.add("-----2-----------");
		a.add("---2-------------");
		a.add("-0---------------");
		int b = GuitarParser.durationCount(a, 2, 15);
		int c = 1;
		assertEquals(b, c);
		
	}
	@Test
	public void test_durationCount_02() {
		ArrayList<String> a = new ArrayList<>();
		a.add("-----------0-----");
		a.add("---------0---0---");
		a.add("-------1-------1-");
		a.add("-----2-----------");
		a.add("---2-------------");
		a.add("-0---------------");
		int b = GuitarParser.durationCount(a, 5, 1);
		int c = 2;
		assertEquals(b, c);
		
	}
	@Test
	public void test_durationCount_03() {
		ArrayList<String> a = new ArrayList<>();
		a.add("-0---------------");
		a.add("-0---------------");
		a.add("-1---------------");
		a.add("-2---------------");
		a.add("-2---------------");
		a.add("-0---------------");
		int b = GuitarParser.durationCount(a, 2, 1);
		int c = 8;
		assertEquals(b, c);
		
	}
	
	@Test
	public void test_durationCount_04() {
		ArrayList<String> a = new ArrayList<>();
		a.add("-0---------------");
		a.add("-0---------------");
		a.add("-1---------------");
		a.add("-2---------------");
		a.add("-2---------------");
		a.add("-0---------------");
		int b = GuitarParser.durationCount(a, 4, 1);
		int c = 8;
		assertEquals(b, c);
		
	}
	
	@Test
	public void test_durationCount_05() {
		ArrayList<String> a = new ArrayList<>();
		a.add("x---------------");
		a.add("--x-x-x-x-x-x-x-");
		a.add("----o-------o---");
		a.add("----------------");
		a.add("----------------");
		a.add("o-------o-------");
		int b = DParser.durationCount(a, 0);
		int c = 2;
		assertEquals(b, c);
		
	}
	
	@Test
	public void test_durationCount_06() {
		ArrayList<String> a = new ArrayList<>();
		a.add("--------x-------");
		a.add("----------------");
		a.add("oooo------------");
		a.add("----oo----------");
		a.add("------oo--------");
		a.add("o-------o-------");
		int b = DParser.durationCount(a, 2);
		int c = 1;
		assertEquals(b, c);
		
	}
	
	/*******************************************************************/
	
	@Test
	public void test_divisionCount_01() {
		ArrayList<String> a = new ArrayList<>();
		a.add("-0---------------");
		a.add("-0---------------");
		a.add("-1---------------");
		a.add("-2---------------");
		a.add("-2---------------");
		a.add("-0---------------");
		int b = GuitarParser.divisionCount(a);
		int c = 2;
		assertEquals(b, c);
		
	}
	
	@Test
	public void test_divisionCount_02() {
		ArrayList<String> a = new ArrayList<>();
		a.add("-----------0-----");
		a.add("---------0---0---");
		a.add("-------1-------1-");
		a.add("-----2-----------");
		a.add("---2-------------");
		a.add("-0---------------");
		int b = GuitarParser.divisionCount(a);
		int c = 2;
		assertEquals(b, c);
		
	}
	
	@Test
	public void test_divisionCount_03() {
		ArrayList<String> a = new ArrayList<>();
		a.add("x---------------");
		a.add("--x-x-x-x-x-x-x-");
		a.add("----o-------o---");
		a.add("----------------");
		a.add("----------------");
		a.add("o-------o-------");
		int b = DParser.divisionCount(a);
		int c = 4;
		assertEquals(b, c);
		
	}
	
	/*******************************************************************/
	
	@Test
	public void test_stepCount_01() {
		String b = GuitarParser.stepCount(2, 2);
		String c = "A";
		assertEquals(b, c);
	}
	
	@Test
	public void test_stepCount_02() {
		String b = BParser.stepCount(2, 1);
		String c = "A#";
		assertEquals(b, c);
	}
	
	@Test
	public void test_stepCount_03() {
		String b = DParser.stepCount(4);
		String c = "D";
		assertEquals(b, c);
	}
	
	/*******************************************************************/
	
	
}
