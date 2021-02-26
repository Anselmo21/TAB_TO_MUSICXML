package TAB_TO_XML;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import Interface.Controller;

public class Parser {

	private static String pathName;

	/**
	 * Setter method for path name
	 * 
	 * @param path
	 */

	public static void setPath(String path) {
		pathName = path;
	}

	/**
	 * Getter method for path name.
	 * 
	 * @return
	 */

	public static String getPath() {
		return pathName;
	}

	/**
	 * This method will figure out which instrument the tab is meant to be 
	 * played for.
	 *  
	 *  <p> In this method, we assume that a guitar has 6 lines of strings and 
	 *  a bass has four lines of string <p/>.
	 *  
	 * @param content is what the tab contains but stored in an array list
	 * @return the name of the instrument as a string
	 */
	public static String identifyInstrument(ArrayList<String> content) { 

		for (int i = 0; i < content.size(); i++) { 
			if (content.get(i).contains("x") || content.get(i).contains("o")) {
				return "Drums";
			}
		}
		if (content.size() == 4) {

			return "Bass";

		}

		return "Guitar";

	}

	/**
	 * Read the text tablature line by line
	 * 
	 * @param path is the file's path that we want to read
	 * @return an ArrayList filled with contents of the ArrayList
	 *         <p>
	 *         each line is an element in our array list
	 */
	public static ArrayList<String> readLineByLine(String path) {
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
	 * Store 6 lines of string (line of notes) from the very first line of notes
	 * from the root.
	 * 
	 * @param root is the array list of strings that contains the strings from the
	 *             file.
	 * @return array parse which stores the very first line of notes that is within
	 *         the root.
	 */
	public static ArrayList<String> extractStrings(ArrayList<String> root) {
		ArrayList<String> parse = new ArrayList<String>();
		for (int i = 0; i < root.size(); i++) {
			if (root.get(i).contains("|-")) {
				for (int j = i; j < i + 6; j++) {
					parse.add(root.get(j));
				}
				break;
			}
		}
		return parse;
	}

	/**
	 * Remove 6 lines of string (a line of notes) from the root array list.
	 * 
	 * @param root is the array list of strings that contains the strings from the
	 *             file.
	 * @return root array after removing the very first line of notes that is within
	 *         the root.
	 */
	public static ArrayList<String> reduceRoot(ArrayList<String> root) {
		for (int i = 0; i < root.size(); i++) {
			if (root.get(i).contains("|-")) {
				for (int j = i; j < i + 6; j++) {
					root.remove(i);
				}
				break;
			}
		}
		return root;
	}
	
	/**
	 * Find the x coordinate of the next note to print
	 * 
	 * @param parse is the array list of strings that contains a whole line of notes, notenum is the number of the next note to print.
	 * @return an integer representing the x position of string where the very next
	 *         note is
	 */
	public static int findnotexposition(ArrayList<String> newParse, int notenum) {
		int position = 0;
		int a = 0;
		outerloop: 
			for (int i = 0; i < newParse.get(0).length() - 1; i++) {
				for (int j = 0; j < newParse.size(); j++) {
					if (Character.isDigit(newParse.get(j).charAt(i))) {
						if (a == notenum) {
							position = i;
							break outerloop;
						}	else {
							a++;
						}
					} 
				}
				} 
		return position;
	}
	
	/**
	 * Find the y coordinate of the next note to print
	 * 
	 * @param parse is the array list of strings that contains a whole line of notes, notenum is the number of the next note to print.
	 * @return an integer representing the y position of string where the very next
	 *         note is
	 */
	public static int findnoteyposition(ArrayList<String> newParse, int notenum) {
		int position = 0;
		int a = 0;
		outerloop: 
			for (int i = 0; i < newParse.get(0).length() - 1; i++) {
				for (int j = 0; j < newParse.size(); j++) {
					if (Character.isDigit(newParse.get(j).charAt(i))) {
						if (a == notenum) {
							position = j;
							break outerloop;
						}	else {
							a++;
						}
					} 
				}
				} 
		return position;
	}

	/**
	 * Find the longest string that's closest to the top The returned string is
	 * reduced by 1 and must be added by 1 for printing purposes.
	 * 
	 * @param parse is the array list of strings that contains a whole line of notes
	 * @return an integer representing the position of string where the very next
	 *         note is
	 */
	public static int findLongerList(ArrayList<String> parse) {
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
	 * 
	 * @param parse is the array list of strings that contains a whole line of notes
	 * @return an integer representing the duration of the note.
	 */
	public static String durationCount(ArrayList<String> parse, int row, int column) {
		Integer count = 1;
		
		outerloop: 
		for (int i = row + 1; i < parse.get(0).length(); i++) {
				for (int j = 0; j < parse.size(); j++) {
					if (Character.isDigit(parse.get(j).charAt(i))) {
						break outerloop;
					}
					else {
						if (j == parse.size() - 1) {
							count++;
						}
					}
				}
			}
		count = count / 2;
		if (count > 8) {
			count = 8;
		}
		return count.toString();
	}

	/**
	 * Helper method on reducing the strings in the array correctly every time you
	 * print out a note
	 * 
	 * @param newParse is the array list of strings that contains a whole line of
	 *                 notes
	 * @return an Array list parse that's been reduced
	 */
	public static ArrayList<String> listReduction(ArrayList<String> parse) {

		ArrayList<String> newParse = new ArrayList<>();

		// deep copy
		for (String s : parse) {
			newParse.add(s);
		}

		outerloop: for (int i = 0; i < newParse.get(findLongerList(newParse)).length() - 1; i++) {
			if (i == 0) {
				int a = findLongerList(newParse);
				if (Character.isDigit(newParse.get(a).charAt(0))) {
					newParse.set(a, newParse.get(a).substring(1));
					a = a + 1;
				}
				for (int j = a; j < newParse.size(); j++) {
					if (Character.isDigit(newParse.get(j).charAt(0))) {
						break outerloop;
					} else {
						newParse.set(j, newParse.get(j).substring(1));
					}
				}
			} else {
				for (int j = 0; j < newParse.size(); j++) {
					if (Character.isDigit(newParse.get(j).charAt(0))) {
						break outerloop;
					} else {
						newParse.set(j, newParse.get(j).substring(1));
					}
				}
			}
		}
		return newParse;
	}

	/// May require fix again
	/**
	 * Counts the division of the whole tablature
	 * 
	 * @param parse is the array list of strings that contains a whole line of notes
	 * @return an integer that represents the division of the whole tablature.
	 */
	public static int divisionCount(ArrayList<String> parse) {
		int k = 0;
		// loop used to count the divisions of the tablature
		for (int i = 1; i < parse.get(0).length()-1; i++) {
				k++;
			}
		k = k / 8;
		return k;
	}

	/**
	 * Counts the fret of the note.
	 * 
	 * @param parse is the array list of strings that contains a whole line of notes
	 * @return an integer that represents the fret of the note.
	 */
	public static String fretCount(ArrayList<String> parse) {
		// System.out.println(findLongerList(parse));
		Integer a = Character.getNumericValue(parse.get(findLongerList(parse)).charAt(0));
		String fret = a.toString();
		// System.out.println(fret);
		return fret;
	}

	/**
	 * Method used to get the step count.
	 * 
	 * @param parse is the array list of strings that contains a whole line of notes
	 * @return an String that represents the fret of the note.
	 */

	public static String stepCount(int row, int column) {

		String[][] fretboard = new String[][] {
			{ "E", "F", "F#", "G", "G#", "A", "A#", "B", "C", "C#", "D", "D#", "E" },
			{ "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B" },
			{ "G", "G#", "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G" },
			{ "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B", "C", "C#", "D" },
			{ "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A" },
			{ "E", "F", "F#", "G", "G#", "A", "A#", "B", "C", "C#", "D", "D#", "E" } };
			
			return fretboard[row][column];
	}

	/**
	 * Method used to get the type
	 * 
	 * @param parse is the array list of strings that contains a whole line of notes
	 * @return an String that represents the type of the note.
	 */
	public static String typeDeclare(int duration) {
		String[] types = new String[] { "", "eighth", "quarter", "quarter and eighth", "half", "", "quarter and half", "", "whole" };
		return types[duration];
	}

	public static String octaveCount(int row, int column) {
		String[][] fretboard = new String[][] { 
			{ "4", "4", "4", "4", "4", "4", "4", "4", "5", "5", "5", "5", "5" },
			{ "3", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4" },
			{ "3", "3", "3", "3", "3", "4", "4", "4", "4", "4", "4", "4", "4" },
			{ "3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "4", "4", "4" },
			{ "2", "2", "2", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3" },
			{ "2", "2", "2", "2", "2", "2", "2", "2", "3", "3", "3", "3", "3" } };

			return fretboard[row][column];
	}
	
	// inputing strings of lines.
	public static ArrayList<ArrayList<String>> method1(ArrayList<String> inputFile){	
		
		
		ArrayList<ArrayList<String>> Collections = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> eachCollection = new ArrayList<String>();
		
		for (int i = 0; i < inputFile.size(); i++) {
			if (inputFile.get(i).contains("|-")) {
				for (int j = 0; j < 6; j++) {
					eachCollection.add(inputFile.get(i));
					Collections.add(eachCollection);
					eachCollection = new ArrayList<String>();	
				}
				i = i + 5;
			}
			
			// checks to see if reached six lines
			
		}
		
		// returns 2d arrays of the input lines 
		return Collections;
	}
	
	
	// input of six lines
	public static ArrayList<ArrayList<String>> method2(ArrayList<String> input){ 
		
		ArrayList<ArrayList<String>> sections = new ArrayList<ArrayList<String>>();
		ArrayList<String> eachSection = new ArrayList<String>();
		
		// assumes that all the measures have 17 dashes/notes excluding the vertical lines
		for (int i = 0; i < (input.get(0).length()-1)/18; i++) {	
			for (int j = 0; j < 6; j++) {
				eachSection.add(input.get(j).substring(1+18*i, 18*(i+1)));
			}
			sections.add(eachSection);
			eachSection = new ArrayList<String>();
		}
		
		// returns  2d array of substrings of the measure excluding the vertical lines	
		return sections;
											
	}
	
	public static int countNote(ArrayList<String> parse) {
		int numNotes = 0;
		for (int i = 0; i < parse.size(); i++) {
			for (int j = 0; j < parse.get(0).length(); j++) {
				if (Character.isDigit(parse.get(i).charAt(j))) {
					numNotes++;
				}
			}
		}
		return numNotes;
	}
	public String parseAlter(String note) { 
		
		for (int i = 0; i < note.length(); i++) { 
			if (note.charAt(i) == '#') { 
				return "1";
			}
		
		}
		return "0";
	}
	public static boolean isChord(ArrayList<String> line, char note) { 
		return false;
		
	}
	public static String[] parseChord() {return null;}
}