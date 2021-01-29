package TAB_TO_XML;

import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
	
	public static void main(String[] args) throws FileNotFoundException { 
		
		
		
		File file = new File("/Users/rafae/OneDrive/Desktop/Capricho Arabe.txt");
		Scanner scan = new Scanner(file);
		
		while (scan.hasNextLine()) {
		
		
		System.out.println(scan.nextLine());
			
		}
	
}
	
}
