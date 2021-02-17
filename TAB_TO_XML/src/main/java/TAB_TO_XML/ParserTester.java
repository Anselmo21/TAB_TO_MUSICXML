package TAB_TO_XML;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.Test;
import java.io.*;
import TAB_TO_XML.Parser;

class ParserTest {

	@Test
	void durationCountTest() {
		Parser p = new Parser();
		ArrayList<String> storeFile = new ArrayList<>();
		File file = new File("C:\\Users\\moham\\git\\EECS2311_GROUP5_TAB_TO_MUSICXML\\TAB_TO_XML\\example.txt");
		Scanner sc = null;
		try {
			sc = new Scanner(file);
			while(sc.hasNextLine()) storeFile.add(sc.nextLine());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(p.durationCount(storeFile));
	}

	@Test
	void longerListTest() {
		Parser p = new Parser();
		ArrayList<String> storeFile = new ArrayList<>();
		storeFile.add("|-----------0-----|-0---------------|");
		//ArrayList<String> store = p.readLineByLine("C:\\Users\\moham\\git\\EECS2311_GROUP5_TAB_TO_MUSICXML\\TAB_TO_XML\\example.txt");
		String n = p.StepCount(storeFile);
		System.out.println(n);
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
