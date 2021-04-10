import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import TAB_TO_XML.App;
import TAB_TO_XML.BParser;

class MohammedTester {

//	@Test
//	public void identifyInstrumentTest() {
//		ArrayList<String> drumTab = new ArrayList<>();
//		drumTab.add("CC|x---------------|--------x-------|");
//		drumTab.add("HH|--x-x-x-x-x-x-x-|----------------|");
//		drumTab.add("SD|----o-------o---|oooo------------|");
//		drumTab.add("HT|----------------|----oo----------|");
//		drumTab.add("MT|----------------|------oo--------|");
//		drumTab.add("BD|o-------o-------|o-------o-------|");
//		String actual = App.identifyInstrument(drumTab);
//		String expected = "Drums";
//		assertEquals(expected, actual);
//	}
//	
//	@Test
//	public void octaveTest() {
//		ArrayList<String> a = new ArrayList<>();
//		a.add("-----------0-----");
//		a.add("---------0---0---");
//		a.add("-------1-------1-");
//		a.add("-----2-----------");
//		String b = BParser.octaveCount(2, Character.getNumericValue(a.get(2).charAt(7)));
//		String c = "1";
//		assertEquals(b, c);
//	}
//	
//	@Test
//	public void stepCountTest() {
//		ArrayList<String> a = new ArrayList<>();
//		a.add("-----------0-----");
//		a.add("---------0---0---");
//		a.add("-------1-------1-");
//		a.add("-----2-----------");
//		String b = BParser.stepCount(2, Character.getNumericValue(a.get(2).charAt(7)));
//		String c = "A#";
//		assertEquals(b, c);
//	}
	public static void main(String[]args) { 
		String s = new StringBuilder("").append(0).append(5).append("").toString();
	
		int i = Integer.parseInt(s);
		String lol = String.valueOf(i);
		
		System.out.println(lol);
		char c = (char) i;
		System.out.println(c);
		if (Character.isDigit(c)) { 
			System.out.println(c);
		}
	}

}
