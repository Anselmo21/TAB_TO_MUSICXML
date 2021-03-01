import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class GuitarParserTest {

	@Test
	public void test() {
		ArrayList<String> guitarTab = new ArrayList<>();
		guitarTab.add("|-----------0-----|-0---------------|");
		guitarTab.add("|---------0---0---|-0---------------|");
		guitarTab.add("|-------1-------1-|-1---------------|");
		guitarTab.add("|-----2-----------|-2---------------|");
		guitarTab.add("|---2-------------|-2---------------|");
		guitarTab.add("|-0---------------|-0---------------|");
	}

}
