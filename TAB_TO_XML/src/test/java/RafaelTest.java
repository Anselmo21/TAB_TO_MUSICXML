import TAB_TO_XML.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;


import org.junit.jupiter.api.Test;

class RafaelTest {

////	@Test
////	//uses the help me method which counts how the maximum number of lines 
////	//per collection 
////	public void countNumOfLinestest() {
////		ArrayList<String> content = new ArrayList<String>(); 
////		content.add("-0---------------");
////		content.add("-0---------------");
////		content.add("-1---------------");
////		content.add("-2---------------");
////		content.add("-2---------------");
////		content.add("-0---------------");
////		content.add("");
////		content.add("-0---------------");
////		content.add("-0---------------");
////		content.add("-1---------------");
////		content.add("-2---------------");
////		content.add("-2---------------");
////		content.add("-0---------------");
////		int expected = 6; 
////		int actual = App.helpMe(content);
////		assertEquals(expected,actual);
////		
////	}
////	@Test
////	public void test_divisionCount_02() {
////		ArrayList<String> a = new ArrayList<>();
////		a.add("-----------0-----");
////		a.add("---------0---0---");
////		a.add("-------1-------1-");
////		a.add("-----2-----------");
////		a.add("---2-------------");
////		a.add("-0---------------");
////		int b = GuitarParser.divisionCount(a);
////		int c = 2;
////		assertEquals(b, c);
////		
////	
////	}
////	
////	
////	@Test
////	public void identifyInstrumentTest2() {
////	
////		
////		drumTab.add("CC|x---------------|--------x-------|");
////		drumTab.add("HH|--x-x-x-x-x-x-x-|----------------|");
////		drumTab.add("SD|----o-------o---|oooo------------|");
////		drumTab.add("HT|----------------|----oo----------|");
////		drumTab.add("MT|----------------|------oo--------|");
////		drumTab.add("BD|o-------o-------|o-------o-------|");
////		String actual = App.identifyInstrument(drumTab);
////		String expected = "Drums";
////		assertEquals(expected, actual);
////		
////		
//	}
	public void labTaskDebug() {
		
	ArrayList<String> tab = new ArrayList<>();
	tab.add("aaa|-----------0-----|-1-------------9-|");
	tab.add("aaa|---------0---0---|-0---------------|");
	tab.add("aaa|-------1-------1-|-1---------------|");
	tab.add("aaa|-----2-----------|-2---------------|"); 
	tab.add("aaa|---2-------------|-2---------------|");
	tab.add("aaa|-0---------------|-0---------------|");
	String App.guitarTabToXML(tab);
	}
}
