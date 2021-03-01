import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import TAB_TO_XML.App;

class AppTest {

	@Test
	public void identifyInstrumentTest1() {
		ArrayList<String> guitarTab = new ArrayList<>();
		guitarTab.add("|-----------0-----|-0---------------|");
		guitarTab.add("|---------0---0---|-0---------------|");
		guitarTab.add("|-------1-------1-|-1---------------|");
		guitarTab.add("|-----2-----------|-2---------------|");
		guitarTab.add("|---2-------------|-2---------------|");
		guitarTab.add("|-0---------------|-0---------------|");
		String actual = App.identifyInstrument(guitarTab);
		String expected = "Guitar";
		assertEquals(actual,expected);
	}
	
	@Test
	public void identifyInstrumentTest2() {
		ArrayList<String> drumTab = new ArrayList<>();
		drumTab.add("CC|x---------------|--------x-------|");
		drumTab.add("HH|--x-x-x-x-x-x-x-|----------------|");
		drumTab.add("SD|----o-------o---|oooo------------|");
		drumTab.add("HT|----------------|----oo----------|");
		drumTab.add("MT|----------------|------oo--------|");
		drumTab.add("BD|o-------o-------|o-------o-------|");
		String actual = App.identifyInstrument(drumTab);
		String expected = "Drums";
		assertEquals(actual,expected);
	}
	
	@Test
	public void identifyInstrumentTest3() {
		ArrayList<String> bassTab = new ArrayList<>();
		bassTab.add("G |--------7-----------|");
		bassTab.add("D |--------------------|");
		bassTab.add("A |--------------------|");
		bassTab.add("E |-/5--7-----7--5--7--|");
		String actual = App.identifyInstrument(bassTab);
		String expected = "Bass";
		assertEquals(actual,expected);
	}

}
