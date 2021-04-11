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
	public static int divisionCount(String line, int numerator) {
		return line.length() / numerator; 
	}

	
	/**
	 * Method used to get the step count.
	 * 
	 * @param parse is the array list of strings that contains a whole line of notes
	 * @return an String that represents the fret of the note.
	 */
	public static String stepCount(int row, int column, ArrayList<String> tuningSteps) {
		
		String[][] fretboard = new String[][] {
			{ "E", "F", "F#", "G", "G#", "A", "A#", "B", "C", "C#", "D", "D#", "E" },
			{ "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B" },
			{ "G", "G#", "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G" },
			{ "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B", "C", "C#", "D" },
			{ "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A" } };
		
		for (int i = 0; i < fretboard.length; i++) {
			if (fretboard[i][0].equals(tuningSteps.get(row))) {
				return fretboard[i][column];
			}
		}
		
		return fretboard[row][column];
		
	}

	/**
	 * Method used to get the type
	 * 
	 * @param parse is the array list of strings that contains a whole line of notes
	 * @return an String that represents the type of the note.
	 */
	public static String typeDeclare(int duration) {
		String[] types = new String[] { "", "eighth", "quarter", "quarter and eighth", "half", "eighth and half", "quarter and half", "", "whole" };
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
				for (int i = 0; i < (input.get(z-1).split("\\|").length-1); i++) {	
					for (int j = 0; j < 6; j++) {
						if (!(input.get(j+z-1).subSequence(0, 1).equals("|"))) {
							int count=0;
							while(!(input.get(j+z-1).subSequence(count, count+1).equals("|"))) {
								count++;
							}
							eachSection.add(splitter(input.get(j+z-1),count,i));
						} 
						else {
							eachSection.add(splitter(input.get(j+z-1),0,i));
						}
					}
					sections.add(eachSection);
					eachSection = new ArrayList<String>();
				}
			}
			

			// returns  2d array of substrings of the measure excluding the vertical lines	
			return sections;
			//test push
												

		}

		private static String splitter(String note, int count,int element) { 
			note=note.substring(count);
			int digits=0;
			int start=0,end=0;
			for (int i = 0; i < note.length(); i++) { 
				if (note.subSequence(i, i+1).equals("|")) { 
					digits++;
					if (digits==element+1)
						start=i;
					else if(digits==element+2)
						end=i;
				}
			}
			return note.substring(start, end+1);
		}


	public static String parseAlter(String note) { 
		
		for (int i = 0; i < note.length(); i++) { 
			if (note.charAt(i) == '#') { 
				return "1";
			}
		
		}
		return "0";
	}
	public static boolean hasHammer(ArrayList<String> content) { 
		
		for (int i = 0; i < content.size(); i++) { 
			if (content.get(i).contains("h")) { 
				return true;
			}
			
		}
		
		return false; 
	}
	
	
	
 }

		
