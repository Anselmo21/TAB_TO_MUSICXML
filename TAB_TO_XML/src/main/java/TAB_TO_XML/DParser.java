package TAB_TO_XML;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import Interface.Controller;

public class DParser {
	
	/**
	 * Counts duration of a note.
	 * 
	 * @param parse is the array list of strings that contains a whole line of notes
	 * @return an integer representing the duration of the note.
	 */
	public static Integer durationCount(ArrayList<String> parse, int row) {
		Integer count = 1;
		
		outerloop: 
		for (int i = row + 1; i < parse.get(0).length(); i++) {
				for (int j = 0; j < parse.size(); j++) {
					if (parse.get(j).charAt(i) !=  '-') {
						break outerloop;
					}
					else {
						if (j == parse.size() - 1) {
							count++;
						}
					}
				}
			}
		if (count > 16) {
			count = 16;
		}
		return count;
	}

	/**
	 * Counts the division of the whole tablature
	 * 
	 * @param parse is the array list of strings that contains a whole line of notes
	 * @return an integer that represents the division of the whole tablature.
	 */
	public static int divisionCount(ArrayList<String> parse) {
		return (parse.get(0).length()-1) / 4;
	}

	/**
	 * Method used to get the step count.
	 * 
	 * @param parse is the array list of strings that contains a whole line of notes
	 * @return an String that represents the fret of the note.
	 */
	public static String stepCount(int row) {
		String[] step = new String[] { "A", "G", "C", "E", "D", "F" };	
		return step[row];
	}

	/**
	 * Method used to get the type
	 * 
	 * @param parse is the array list of strings that contains a whole line of notes
	 * @return an String that represents the type of the note.
	 */
	public static String typeDeclare(int duration) {
		String[] types = new String[] { "", "16th", "eighth", "", "quarter", "", "", "", "half", "", "", "", "", "", "", "", "whole" };
		return types[duration];
	}
	
	public static String identifyID(int row) {
		String[] step = new String[] { "P1-I50", "P1-I43", "P1-I39", "P1-I48", "P1-I46", "P1-I36" };	
		return step[row];
	}

	public static String octaveCount(int row) {
		if (row == 5) {
			return "4";
		}	else {
			return "5";
		}
	}
	
	// inputing strings of lines.
	public static ArrayList<ArrayList<String>> method1(ArrayList<String> inputFile){	
		
		
		ArrayList<ArrayList<String>> Collections = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> eachCollection = new ArrayList<String>();
		
		for (int i = 0; i < inputFile.size(); i++) {
			if (inputFile.get(i).contains("|-")) {
				int j = 0;
				while (inputFile.get(i+j).contains("|-")) {
					eachCollection.add(inputFile.get(i+j));
					j++;
				}
				eachCollection.add(" ");
				Collections.add(eachCollection);
				eachCollection = new ArrayList<String>();
				i = i + j;
			}
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
	
	
	public static boolean isChord(ArrayList<String> line, char note) { 
		return false;
		
	}
	
	public static String[] parseChord() {return null;}
}