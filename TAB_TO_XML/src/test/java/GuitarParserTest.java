import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class GuitarParserTest {
	
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
	
/*******************************************************************/
	
	@Test
	public void test_stepCount_01() {
		String b = GuitarParser.stepCount(2, 2);
		String c = "A";
		assertEquals(b, c);
	}
	
	@Test
	public void test_stepCount_02() {
		String b = GuitarParser.stepCount(5, 4);
		String c = "G#";
		assertEquals(b, c);
	}
	
/*******************************************************************/
	
	@Test
	public void test_octaveCount_01() {
		String b = GuitarParser.octaveCount(0, 8);
		String c = "5";
		assertEquals(b, c);
	}
	
	@Test
	public void test_octaveCount_02() {
		String b = GuitarParser.octaveCount(3, 2);
		String c = "3";
		assertEquals(b, c);
	}
	
/*******************************************************************/
	
	@Test
	public void test_parseAlter_01() {
		String b = GuitarParser.parseAlter("A#");
		String c = "1";
		assertEquals(b, c);
	}
	
	@Test
	public void test_parseAlter_02() {
		String b = GuitarParser.parseAlter("G");
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
		a.add("|---2-------------|-2---------------|");
		a.add("|-0---------------|-0---------------|");
		for (int i = 0; i < a.size(); i++) {
			b.add(a.get(i));
		}
		b.add(" ");
		c.add(b);
		d = method1(a);
		
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
		a.add("|---2-------------|-2---------------|");
		a.add("|-0---------------|-0---------------|");
		b.add("-----------0-----");
		b.add("---------0---0---");
		b.add("-------1-------1-");
		b.add("-----2-----------");
		b.add("---2-------------");
		b.add("-0---------------");
		c.add(b);
		b = new ArrayList<String>();
		b.add("-0---------------");
		b.add("-0---------------");
		b.add("-1---------------");
		b.add("-2---------------");
		b.add("-2---------------");
		b.add("-0---------------");
		c.add(b);
		d = method2(a);
		
		assertEquals(c.get(0).get(0), d.get(0).get(0));
	}
	
/*******************************************************************/

	
}
