package TAB_TO_XML;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
		 
	/**
	 * Read the text tablature line by line 
	 * @param path is the file's path that we want to read
	 * @return an ArrayList filled with contents of the ArrayList
	 * <p> each line is an element in our array list 
	 */
	 public static ArrayList<String> readLineByLine(String path)  {
			ArrayList<String> content = new ArrayList<String>();
			Scanner scan = null;
			try {
			 scan = new Scanner(new File(path));
			
			while (scan.hasNextLine()) { 
				
				content.add(scan.nextLine());
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally { 
			scan.close(); 
			}
			return content; 
		}
	 
	 /**
		 * Find the longest string that's closest to the top
		 * @param parse is the array list of strings that contains a whole line of notes
		 * @return an integer representing the position of string where the very next note is
		 */
	private static int findLongerList(ArrayList<String> parse) {
		int count = parse.get(0).length();
		int position = 0;
		for (int i = 0; i < parse.size(); i++) {
			if (count < parse.get(i).length()) {
				position = i;
				break;
			}
		}
		return position;
	}
	
	/**
	 * Counts duration of a note.
	 * @param parse is the array list of strings that contains a whole line of notes
	 * @return an integer representing the duration of the note.
	 */
	public static int durationCount(ArrayList<String> parse) {
		int count = 1;
		for (int i = 0; i < parse.get(findLongerList(parse)).length(); i++) {
			if (i == 0) {
				for (int j = findLongerList(parse); j < parse.size(); j++) {
					if (parse.get(j).charAt(i) == '-' && parse.get(j).charAt(i+1) == '|') {
						break;
					}
					else {
						if (j == parse.size() - 1) {
							count++;
						}
					}
				}
			}	
			else {
				for (int j = 0; j < parse.size(); j++) {
					if (j < findLongerList(parse)) {
						int k = i - 1;
						if (parse.get(j).charAt(k) == '-' && parse.get(j).charAt(k+1) == '|') {
							break;
						}	
						else if (Character.isDigit(parse.get(j).charAt(k))) {
							break;
						}	
						else {
							if (j == parse.size() - 1) {
								count++;
							}
						}
					}	
					else {
						if (parse.get(j).charAt(i) == '-' && parse.get(j).charAt(i+1) == '|') {
							break;
						}	
						else if (Character.isDigit(parse.get(j).charAt(i))) {
							break;
						}	
						else {
							if (j == parse.size() - 1) {
								count++;
							}
						}
					}
				}
			}
		}
		return count;
	}

	/**
	 * Helper method on reducing the strings in the array correctly every time you print out a note
	 * @param parse is the array list of strings that contains a whole line of notes
	 * @return an Array list parse that's been reduced
	 */
	public static ArrayList<String> listReduction(ArrayList<String> parse) {

		for (int i = 0; i < parse.get(findLongerList(parse)).length()-1; i++) {
			if (i == 0) {
				for (int j = findLongerList(parse); j < parse.size(); j++) {
					if (Character.isDigit(parse.get(j).charAt(i))) {
						break;
					}	
					else {
						parse.set(i, parse.get(i).substring(i+1));
					}
				}
			}	
			else {
				for (int j = 0; j < parse.size(); j++) {
					if (j < findLongerList(parse)) {
						int k = i - 1;
						if (Character.isDigit(parse.get(j).charAt(k))) {
							break;
						}	
						else {
							parse.set(k, parse.get(k).substring(k+1));
						}
					}	
					else {
						if (Character.isDigit(parse.get(j).charAt(i))) {
							break;
						}	
						else {
							parse.set(i, parse.get(i).substring(i+1));
						}
					}
				}
			}
		}
		return parse;
	}

	/**
	 * Counts the division of the whole tablature
	 * @param parse is the array list of strings that contains a whole line of notes
	 * @return an integer that represents the division of the whole tablature.
	 */
	public static int divisionCount(ArrayList<String> parse) {
		int k = 0;
		// loop used to count the divisions of the tablature
		for (int j = 0; j < findLongerList(parse); j++) {
			if (parse.get(0).charAt(j) == '|') {
				if (parse.get(0).charAt(j - 1) == '-' && parse.get(0).charAt(j + 1) == '-') {
					break;
				}
			}	
			else { 
				k++;
			}
		}
		k = k - 2;
		k = k / 4;
		return k;
	}

	/**
	 * Counts the fret of the note.
	 * @param parse is the array list of strings that contains a whole line of notes
	 * @return an integer that represents the fret of the note.
	 */
	public static int fretCount(ArrayList<String> parse) {
		int fret = parse.get(findLongerList(parse)).charAt(0);
		return fret;
	}
	//Counts the step of the note
	public static String stepCount(ArrayList<String> parse) {
		String step = "";
		if (findLongerList(parse) == 0) {

		}	
		else if (findLongerList(parse) == 1) {

		}	
		else if (findLongerList(parse) == 2) {

		}	
		else if (findLongerList(parse) == 3) {

		}	
		else if (findLongerList(parse) == 4) {

		}	
		else  {

		}
		return step;
	}
	
}
