package TAB_TO_XML;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; 


public class FileReader {
	
	public static void main(String[] args) throws FileNotFoundException { 
		
		Scanner first_scan = new Scanner(System.in);
		System.out.println("Enter a file to be scanned: ");
		String file = first_scan.nextLine();
		File scannedFile = new File(file);
		Scanner second_scan = new Scanner(scannedFile);
		
		String fileContent = ""; 
		while(second_scan.hasNextLine()) { 
			fileContent = fileContent.concat(second_scan.nextLine() + "\n");	
		}
		System.out.println(fileContent);
		first_scan.close();
		second_scan.close();
	}

	
	
}
