import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class BassParserTest {

	@Test
	void test() {
		ArrayList<String> bassTab = new ArrayList<>();
		bassTab.add("G |--------7-----------|");
		bassTab.add("D |--------------------|");
		bassTab.add("A |--------------------|");
		bassTab.add("E |-/5--7-----7--5--7--|");
	}

}
