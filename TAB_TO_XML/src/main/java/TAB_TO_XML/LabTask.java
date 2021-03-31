package TAB_TO_XML;
import java.util.*;

public class LabTask {


	public static void main(String[] args) {
		ArrayList<String> tab = new ArrayList<String>();
		tab.add("aaa|-----------0-----|-1-------------9-|");
		tab.add("aaa|---------0---0---|-0---------------|");
		tab.add("aaa|-------1-------1-|-1---------------|");
		tab.add("aaa|-----2-----------|-2---------------|"); 
		tab.add("aaa|---2-------------|-2---------------|");
		tab.add("aaa|-0---------------|-0---------------|");
		App.guitarTabToXML(tab);
		
	}

}


	