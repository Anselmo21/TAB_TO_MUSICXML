package TAB_TO_XML;
import java.util.*;

public class LabTask {


	public static void main(String[] args) {
		ArrayList<String> a = new ArrayList<String>();
		a.add("-----------0-----"); //First Row 
		a.add("---------0---0---");
		a.add("-------1-------1-");
		a.add("-----2-----------");
		a.add("---2h3-----------"); //Last Row
		
		
		//iterate through each column
		for (int y = 0; y < a.get(0).length(); y++) {
			int nextCol = 0;
			if (y + 1 < a.get(0).length()) { 
				nextCol = y + 1;
			}
			//iterate through each row starting from the last row
			for (int x = a.size() - 1; x >= 0; x--) {
				
				if (a.get(x).charAt(nextCol) == 'h') {
			
						System.out.println(a.get(x).charAt(nextCol));
						
					}
				
			}
			
			}
			
		
	}

}


	