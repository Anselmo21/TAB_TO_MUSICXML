import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import TAB_TO_XML.App;
import TAB_TO_XML.BParser;

class BParserTest {
	
	@Test
	public void test_durationCount_01() {
		ArrayList<String> a = new ArrayList<>();
		a.add("-----------0-----");
		a.add("---------0---0---");
		a.add("-------1-------1-");
		a.add("-----2-----------");
		int b = BParser.durationCount(a, 2, 15);
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
		int b = BParser.durationCount(a, 3, 5);
		int c = 1;
		assertEquals(b, c);
		
	}
	@Test
	public void test_durationCount_03() {
		ArrayList<String> a = new ArrayList<>();
		a.add("-0---------------");
		a.add("-0---------------");
		a.add("-1---------------");
		a.add("-2---------------");
		int b = BParser.durationCount(a, 2, 1);
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
		int b = BParser.durationCount(a, 3, 1);
		int c = 8;
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
		int b = BParser.divisionCount(a);
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
		int b = BParser.divisionCount(a);
		int c = 2;
		assertEquals(b, c);
		
	}
	
/*******************************************************************/
	
	@Test
	public void test_stepCount_01() {
		ArrayList<String> a = new ArrayList<>();
		a.add("-----------0-----");
		a.add("---------0---0---");
		a.add("-------1-------1-");
		a.add("-----2-----------");
		String b = BParser.stepCount(2, a.get(2).charAt(7));
		String c = "A#";
		assertEquals(b, c);
	}
	
	@Test
	public void test_stepCount_02() {
		ArrayList<String> a = new ArrayList<>();
		a.add("-----------0-----");
		a.add("---------0---0---");
		a.add("-------1-------1-");
		a.add("-----2-----------");
		String b = BParser.stepCount(3, a.get(3).charAt(5));
		String c = "F#";
		assertEquals(b, c);
	}
	
/*******************************************************************/
	
	@Test
	public void test_octaveCount_01() {
		ArrayList<String> a = new ArrayList<>();
		a.add("-----------0-----");
		a.add("---------0---0---");
		a.add("-------1-------1-");
		a.add("-----2-----------");
		String b = BParser.octaveCount(0, a.get(0).charAt(11));
		String c = "2";
		assertEquals(b, c);
	}
	
	@Test
	public void test_octaveCount_02() {
		ArrayList<String> a = new ArrayList<>();
		a.add("-----------0-----");
		a.add("---------0---0---");
		a.add("-------1-------1-");
		a.add("-----2-----------");
		String b = BParser.octaveCount(2, a.get(0).charAt(15));
		String c = "1";
		assertEquals(b, c);
	}
	
/*******************************************************************/
	
	@Test
	public void test_parseAlter_01() {
		ArrayList<String> a = new ArrayList<>();
		a.add("-----------0-----");
		a.add("---------0---0---");
		a.add("-------1-------1-");
		a.add("-----2-----------");
		String b = BParser.parseAlter(BParser.stepCount(2, a.get(2).charAt(7)));
		String c = "1";
		assertEquals(b, c);
	}
	
	@Test
	public void test_parseAlter_02() {
		ArrayList<String> a = new ArrayList<>();
		a.add("-----------0-----");
		a.add("---------0---0---");
		a.add("-------1-------1-");
		a.add("-----2-----------");
		String b = BParser.parseAlter(BParser.stepCount(0, a.get(0).charAt(15)));
		String c = "0";
		assertEquals(b, c);
	}
	
/*******************************************************************/
	
	@Test
	public void test_method1_01() {
		ArrayList<String> a = new ArrayList<>();
		ArrayList<String> b = new ArrayList<>();
		ArrayList<ArrayList<String>> c = new ArrayList<>();
		ArrayList<ArrayList<String>> d = new ArrayList<>();
		a.add("|-----------0-----|-0---------------|");
		a.add("|---------0---0---|-0---------------|");
		a.add("|-------1-------1-|-1---------------|");
		a.add("|-----2-----------|-2---------------|");
		for (int i = 0; i < a.size(); i++) {
			b.add(a.get(i));
		}
		b.add(" ");
		c.add(b);
		d = BParser.tabToCollection(a);
		
		assertEquals(c.get(0).get(0), d.get(0).get(0));
		
	}
	
	@Test
	public void test_method2_01() {
		ArrayList<String> a = new ArrayList<>();
		ArrayList<String> b = new ArrayList<>();
		ArrayList<ArrayList<String>> c = new ArrayList<>();
		ArrayList<ArrayList<String>> d = new ArrayList<>();
		a.add("|-----------0-----|-0---------------|");
		a.add("|---------0---0---|-0---------------|");
		a.add("|-------1-------1-|-1---------------|");
		a.add("|-----2-----------|-2---------------|");
		b.add("-----------0-----");
		b.add("---------0---0---");
		b.add("-------1-------1-");
		b.add("-----2-----------");
		c.add(b);
		b = new ArrayList<String>();
		b.add("-0---------------");
		b.add("-0---------------");
		b.add("-1---------------");
		b.add("-2---------------");
		c.add(b);
		d = BParser.collectionToMeasure(a);
		
		assertEquals(c.get(0).get(0), d.get(0).get(0));
	}
	
/*******************************************************************/

	
}
