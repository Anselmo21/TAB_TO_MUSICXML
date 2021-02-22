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
		outerloop: 
			for (int i = 0; i < newParse.get(0).length() - 1; i++) {
				int a = 0;
				for (int j = 0; j < newParse.size(); j++) {
					if (Character.isDigit(newParse.get(j).charAt(i))) {
						if (a == j) {
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
		outerloop: 
			for (int i = 0; i < newParse.get(0).length() - 1; i++) {
				int a = 0;
				for (int j = 0; j < newParse.size(); j++) {
					if (Character.isDigit(newParse.get(j).charAt(i))) {
						if (a == j) {
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
	 * Counts duration of a note.
	 * 
	 * @param parse is the array list of strings that contains a whole line of notes
	 * @return an integer representing the duration of the note.
	 */
	
	public static String durationCount(ArrayList<String> parse, int notenum) {
		Integer count = 1;
		
		outerloop: 
		for (int i = findnotexposition(parse, notenum) + 1; i < parse.get(0).length(); i++) {
				for (int j = 0; j < parse.size(); j++) {
					if (parse.get(j).charAt(i+1) == '-' && parse.get(j).charAt(i + 2) == '|') {
						break outerloop;
					} else if (Character.isDigit(parse.get(j).charAt(i))) {
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
	 * returns the fret of the note.
	 * 
	 * @param parse is the array list of strings that contains a whole line of notes
	 * @return an integer that represents the fret of the note.
	 */
	public static Character findfret(ArrayList<String> parse, int notenum) {
		Character note = parse.get(findnoteyposition(parse, notenum)).charAt(findnotexposition(parse, notenum));
		return note;
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
		for (int i = 1; i < parse.get(0).length(); i++) {
			if (parse.get(0).charAt(i) == '|') {
				k++;
			}
		}
		k = k - 2;
		return k;
	}

	/**
	 * Method used to get the step count.
	 * 
	 * @param parse is the array list of strings that contains a whole line of notes
	 * @return an String that represents the fret of the note.
	 */

	public static String stepCount(ArrayList<String> parse, int notenum) {

		String[][] fretboard = new String[][] {
			{ "E", "F", "F#", "G", "G#", "A", "A#", "B", "C", "C#", "D", "D#", "E" },
			{ "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B" },
			{ "G", "G#", "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G" },
			{ "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B", "C", "C#", "D" },
			{ "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A" },
			{ "E", "F", "F#", "G", "G#", "A", "A#", "B", "C", "C#", "D", "D#", "E" } };
			String stepC = "";

			stepC = (fretboard[findnoteyposition(parse, notenum)][Character.getNumericValue(findfret(parse, notenum))]);
			return stepC;
	}

	/**
	 * Method used to get the type
	 * 
	 * @param parse is the array list of strings that contains a whole line of notes
	 * @return an String that represents the type of the note.
	 */
	public static String typeDeclare(ArrayList<String> parse, int notenum) {
		String type = "";
		String[] types = new String[] { "", "eighth", "quarter", "quarter and eighth", "half", "", "quarter and half", "", "whole" };
		type = (types[Character.getNumericValue(durationCount(parse, notenum).charAt(0))]);
		return type;
	}

	public static String octaveCount(ArrayList<String> parse, int notenum) {
		String octave;
		String[][] fretboard = new String[][] { { "4", "4", "4", "4", "4", "4", "4", "4", "5", "5", "5", "5", "5" },
			{ "3", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4" },
			{ "3", "3", "3", "3", "3", "4", "4", "4", "4", "4", "4", "4", "4" },
			{ "3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "4", "4", "4" },
			{ "2", "2", "2", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3" },
			{ "2", "2", "2", "2", "2", "2", "2", "2", "3", "3", "3", "3", "3" } };

			octave = (fretboard[findnoteyposition(parse, notenum)][Character.getNumericValue(findfret(parse, notenum))]);

			return octave;
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

}