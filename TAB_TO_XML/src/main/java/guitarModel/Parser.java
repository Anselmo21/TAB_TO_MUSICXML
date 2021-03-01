package guitarModel;

import java.util.*;
import java.io.*;
/**
 * 
 * @author Mohammed Fulwala and Rafael Dolores
 *
 */

public class Parser {

	ArrayList<String> content = new ArrayList<>(); //To store the contents of an Array 
	File tablatureText;

	// Default
	public Parser(){}

	//Reads content of the file, line by line and Stores that content into an ArrayList
	public Parser(String pathName){
		tablatureText = new File(pathName);
		//readFile();

	} // end of Constructor 

	//reads content of file and stores it in arraylist of characters.
	private void readFile() {
		Scanner sc = null; 
		try {
			sc = new Scanner(tablatureText);	
			while(sc.hasNextLine()){
				content.add(sc.nextLine() + "\n");
			}      
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			sc.close();
		}		
	} //end of readFile method


	//	  method to find division
	//    method to find key 
	//    method to find time (beats and beat-type)
	//    method to find clef
	//    method staff-details...stafftune and stafftuning(Tuning-Step and Tuning-Octave)

	//    -----------------------------------------------

	//     method to find pitch(step,octabe, and alter...usually not here) 
	//     method to find duration 
	//     method to find voice 
	//     method to find 
	//     method to find notations(Contains -> Technical -> String & Fret)
//    public int findString() { 
//        for int (i)
//
//    } 




}
