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
	public static Integer durationCount(ArrayList<String> parse, int column) {
		Integer count = 1;
		
		outerloop: 
		for (int i = column + 1; i < parse.get(0).length(); i++) {
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
	 * Counts duration of a note.
	 * 
	 * @param parse is the array list of strings that contains a whole line of notes
	 * @return an integer representing the duration of the note.
	 */
	public static Integer durationCountLastLine(ArrayList<String> parse, int column) {
		Integer count = 1;
		
		outerloop: 
		for (int i = column + 1; i < parse.get(0).length(); i++) {
					if (parse.get(parse.size()-1).charAt(i) !=  '-') {
						break outerloop;
					}
					else {
						count++;
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
	public static int divisionCount(String line, int numerator) {
		return line.length() / numerator; 
	}

	/**
	 * Method used to get the step count.
	 * 
	 * @param parse is the array list of strings that contains a whole line of notes
	 * @return an String that represents the fret of the note.
	 */
	public static String stepCount(String ID) {
		String[] Symbols = new String[] { "CC", "C ", "HH", "R ", "Rd", "RD", "T ", "HT", "t ", "MT", "SN", "SD", "FT", "BD", "B " };
		String[] step = new String[] { "A", "A", "G", "F", "F", "F", "E", "E", "D", "D", "C", "C", "A", "F", "F" };
		for (int i = 0; i < Symbols.length; i++) {
			if (Symbols[i].equals(ID)) {
				return step[i];
			}
		}
		return null;
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
	
	public static String identifyID(String ID, Character Character) {
		String[] Symbols = new String[] { "CC", "C ", "R ", "Rd", "RD", "T ", "HT", "t ", "MT", "SN", "SD", "FT", "BD", "B " };
		String[] IDs = new String[] { "P1-I50", "P1-I50", "P1-I43", "P1-I43", "P1-I43", "P1-I48", "P1-I48", "P1-I46", "P1-I46", "P1-I39", "P1-I39", "P1-I42", "P1-I36", "P1-I36" };
		if (ID.equals("HH")) {
			if (Character == 'x' || Character == 'X') {
				return "P1-I43";
			}	else if (Character == 'o') {
				return "P1-I47";
			}
		}	else {
		for (int i = 0; i < Symbols.length; i++) {
			if (Symbols[i].equals(ID)) {
				return IDs[i];
			}
		}
		}
		return null;
	}

	public static String octaveCount(String ID) {
		if (ID.equals("FT") || ID.equals("BD") || ID.equals("B ")) {
			return "4";
		}	else {
			return "5";
		}
	}
	
	/**
	 * Method used to get the state of the beam
	 * 
	 * @param parse is the array list of strings that contains a whole line of notes
	 * @return an String that returns either begin, continue and end.
	 */
	public static String beamState(ArrayList<String> parse, String type, int row, int column, int beamcount) {
		String state = null;
		if (beamcount > 0 && beamcount < 3) {
		if (type == "16th")	{
			if (column > 0 && column < parse.get(0).length() - 1) {
				int statenum = 0;
				for (int i = 4; i >= 0; i--) {
					if (parse.get(i).charAt(column+1) == 'x' || parse.get(i).charAt(column+1) == 'o') {
						statenum += 2;
						break;
					}
				}
				for (int i = 4; i >= 0; i--) {
					 if (parse.get(i).charAt(column-1) == 'x' || parse.get(i).charAt(column-1) == 'o') {
						statenum += 1;
						break;
					}
				}
				if (statenum == 3) {
					state = "continue";
				} else if (statenum == 2) {
					state = "begin";
				} else if (statenum == 1) {
					state = "end";
				}	else {
					state = "No beam";
				}
			}	else if (column == 0 || beamcount == 0) {
				state = "begin";
			}	else if (beamcount == 3 || column == parse.get(0).length() - 2) {
				state = "end";
			}
		}	else if (type == "eighth") {
			if (column > 1 && column < parse.get(0).length() - 2) {
				int statenum = 0;
				for (int i = 4; i >= 0; i--) {
					if (parse.get(i).charAt(column+2) == 'x' || parse.get(i).charAt(column+2) == 'o') {
						statenum += 2;
						break;
					}
				}
				for (int i = 4; i >= 0; i--) {
					 if (parse.get(i).charAt(column-2) == 'x' || parse.get(i).charAt(column-2) == 'o') {
						statenum += 1;
						break;
					}
				}
				if (statenum == 3) {
					state = "continue";
				} else if (statenum == 2) {
					state = "begin";
				} else if (statenum == 1) {
					state = "end";
				}	else if (statenum == 0){
					state = "No beam";
				}
			}	else if (column == 0) {
				state = "begin";
			}	else {
				state = "end";
			}	
		}
		} else if (beamcount == 0) {
			state = "begin";
		}	else if (beamcount == 3) {
			state = "end";
		}
		return state;
	}

	/**
	 * Helper method to update the size of the beams
	 * 
	 * @param state is the state of the note which is placed on a beam and beamcount is the current size of the beam.
	 * @return an int that returns from 1 to 4
	 */
	public static int countBeam(String state, int beamcount) {
		if (state == "begin") {
			beamcount = 1;
		}	else if (state == "end") {
			beamcount = 4;
		}	else {
			beamcount++;
		}
		return beamcount;
	}
	
	/**
	 * Method used to get the state of the beam
	 * 
	 * @param parse is the array list of strings that contains a whole line of notes
	 * @return an String that returns either begin, continue and end.
	 */
	public static int beamNumber(String type) {
		int beamnum = 0;
		if (type == "eighth") {
			beamnum = 1;
		} else if (type == "16th") {
			beamnum = 2;
		} 
		return beamnum;
	}
	// inputing strings of lines.
	public static ArrayList<ArrayList<String>> tabToCollection(ArrayList<String> inputFile){	
		
		
		ArrayList<ArrayList<String>> Collections = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> eachCollection = new ArrayList<String>();
		
		int count = 0;
		for (int i = 0; i < inputFile.size(); i++) {
			if (inputFile.get(i).contains("|")) {
				for (int j = i; j < inputFile.size(); j++) {
					if (inputFile.get(j).contains("|")) {
						count++;
					}	else  {
						break;
					}
				}
				for (int j = 0; j < count; j++) {
					eachCollection.add(inputFile.get(i+j));
				}
				eachCollection.add(" ");
				Collections.add(eachCollection);
				eachCollection = new ArrayList<String>();
				i = i + count;
				count = 0;
			}
		}
		// returns 2d arrays of the input lines 
		
		return Collections;
	}
	
	
	
	// input of six lines
	public static ArrayList<ArrayList<String>> collectionToMeasure(ArrayList<String> input){ 
		
		ArrayList<ArrayList<String>> sections = new ArrayList<ArrayList<String>>();
		ArrayList<String> eachSection = new ArrayList<String>();
		
		ArrayList<String> in = new ArrayList<String>();
		
		int o = input.get(0).length();
		
		for (int i = 0; i < input.size()-1; i++) {
			if (input.get(i).subSequence(0, 2).equals("Hf") || input.get(i).subSequence(0, 2).equals("HF")) {
				break;
			}	else if (!(input.get(i).contains("REPEAT"))) {
			in.add(input.get(i).substring(2, o));
			}
		}

		// assumes that all the measures have 16 dashes/notes excluding the vertical lines
		for (int z = 1; z < in.size(); z=z+(in.size())){
			for (int i = 0; i < (in.get(0).length()-1)/17; i++) {
				for (int j = 0; j < in.size(); j++) {
					if (!(in.get(j+z-1).subSequence(0, 1).equals("|"))) {
						int count=0;
						while(!(in.get(j+z-1).subSequence(count, count+1).equals("|"))) {
							count++;
						}
						eachSection.add(in.get(j+z-1).substring(1+17*i+count, 17*(i+1)+count));
					}
					else {
						eachSection.add(in.get(j+z-1).substring(1+17*i, 17*(i+1)));
					}
				}	
				sections.add(eachSection);
				eachSection = new ArrayList<String>();
			}
		}
		// returns  2d array of substrings of the measure excluding the vertical lines	
		return sections;
	}
	
	public static ArrayList<ArrayList<String>> collectionToMeasureExtractID(ArrayList<String> input){ 
		
		ArrayList<ArrayList<String>> sections = new ArrayList<ArrayList<String>>();
		ArrayList<String> eachSection = new ArrayList<String>();
		
		ArrayList<String> in = new ArrayList<String>();
		
		for (int i = 0; i < input.size()-1; i++) {
			if (!(input.get(i).contains("REPEAT"))) {
			in.add(input.get(i));
			}	else if (input.get(i).subSequence(0, 2).equals("Hf") || input.get(i).subSequence(0, 2).equals("HF")) {
				break;
			}
		}
		
		// assumes that all the measures have 16 dashes/notes excluding the vertical lines
		for (int z = 1; z < in.size(); z=z+in.size()){
			for (int i = 0; i < (in.get(0).length()-1)/17; i++) {
				for (int j = 0; j < in.size(); j++) {
					int count=0;
					while(!(in.get(j+z-1).subSequence(count, count+1).equals("|"))) {
						count++;
					}
					eachSection.add(in.get(j+z-1).substring(0, count));
					}
				sections.add(eachSection);
				eachSection = new ArrayList<String>();
			}
		}
		// returns  2d array of substrings of the ID of the instruments
		return sections;
	}	
	
	public static boolean isChord(ArrayList<String> line, char note) { 
		return false;
		
	}
	
	public static String[] parseChord() {return null;}
}