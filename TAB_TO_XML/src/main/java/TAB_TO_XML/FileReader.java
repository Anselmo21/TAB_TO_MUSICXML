package TAB_TO_XML;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; 


public class FileReader {
	
	public static void main(String[] args) throws FileNotFoundException { 
		
		File file = new File("\"C:\\Users\\rafae\\OneDrive\\Desktop\\Map.java\"");
		Scanner scan = new Scanner(file);
		while(scan.hasNextLine()) { 
			System.out.println(scan.hasNextLine());
		}
		scan.close();
	}

	
	
}
