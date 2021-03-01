package TAB_TO_XML;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import Interface.Controller;

public class GuitarParser {

	/**
	 * Counts duration of a note.
	 * 
	 * @param parse is the array list of strings that contains a whole line of notes
	 * @return an integer representing the duration of the note.
	 */
	public static Integer durationCount(ArrayList<String> parse, int row, int division) {
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
		count = count / division;
		if (count > 8) {
			count = 8;
		}	else if (count == 0) {
			count = 1;
		}
		return count;
	}

	/// May require fix again
	/**
	 * Counts the division of the whole tablature
	 * 
	 * @param parse is the array list of strings that contains a whole line of notes
	 * @return an integer that represents the division of the whole tablature.
	 */
	public static int divisionCount(ArrayList<String> parse) {
		return (parse.get(0).length()-1) / 8;
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
	
	// returns a list of list<string>, where each list<string> is a collection
	public static ArrayList<ArrayList<String>> tabToCollection(ArrayList<String> inputFile){	
		
		
		ArrayList<ArrayList<String>> Collections = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> eachCollection = new ArrayList<String>();
		
		for (int i = 0; i < inputFile.size(); i++) {
			if (inputFile.get(i).contains("|")) {
				for (int j = 0; j < 6; j++) {
					eachCollection.add(inputFile.get(i+j));
				}
				eachCollection.add(" ");
				Collections.add(eachCollection);
				eachCollection = new ArrayList<String>();
				i = i + 6;
			}
		}
		
		
		// returns 2d arrays of the input lines 
		return Collections;
	}
	
	
	//return list of list<string>, where each list<string> represents a single measure
	public static ArrayList<ArrayList<String>> collectionToMeasure(ArrayList<String> input){ 
		
		ArrayList<ArrayList<String>> sections = new ArrayList<ArrayList<String>>();
		ArrayList<String> eachSection = new ArrayList<String>();
		
		// assumes that all the measures have 17 dashes/notes excluding the vertical lines
		for (int z = 1; z < input.size(); z=z+6){
			for (int i = 0; i < (input.get(0).length()-1)/18; i++) {	
				for (int j = 0; j < 6; j++) {
					eachSection.add(input.get(j+z-1).substring(1+18*i, 18*(i+1)));
				}
				sections.add(eachSection);
				eachSection = new ArrayList<String>();
			}
		}
		
		// returns  2d array of substrings of the measure excluding the vertical lines	
		return sections;
											
	}
	
	public static String parseAlter(String note) { 
		
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