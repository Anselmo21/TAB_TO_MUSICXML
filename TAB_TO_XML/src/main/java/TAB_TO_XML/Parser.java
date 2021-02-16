package TAB_TO_XML;

import java.util.ArrayList;

public class Parser {
	//Helper method to find the next string to check
	private static int findLongerList(ArrayList<String> parse) {
		int count = 0;
		int position = 0;
		for (int i = 0; i < parse.size(); i++) {
			if (count < parse.get(i).length()) {
				count = parse.get(i).length();
				position = i;
			}
		}
		return position;
	}
	//Counts duration of the note. Uses array that stored a whole line of tab (6 strings)
	public static int durationCount(ArrayList<String> parse) {
		int count = 1;
		for (int i = 0; i < parse.get(findLongerList(parse)).length(); i++) {
			if (i == 0) {
			for (int j = findLongerList(parse); j < parse.size(); j++) {
				if (parse.get(j).charAt(i) != '-' && parse.get(j).charAt(i) != '|') {
					break;
				}	else if (parse.get(j).charAt(i) == '|') {
				}	else {
					if (j == parse.size() - 1) {
						count++;
					}
				}
			}
			}	else {
				for (int j = 0; j < parse.size(); j++) {
					if (j < findLongerList(parse)) {
						int k = i - 1;
						if (parse.get(j).charAt(k) != '-' && parse.get(j).charAt(k) != '|') {
							break;
						}	else if (parse.get(j).charAt(k) == '|') {
						}	else {
							if (j == parse.size() - 1) {
								count++;
							}
						}
					}	else {
						if (parse.get(j).charAt(i) != '-' && parse.get(j).charAt(i) != '|') {
							break;
						}	else if (parse.get(j).charAt(i) == '|') {
						}	else {
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
	//Helper method on reducing the strings in the array correctly
	public static ArrayList<String> listReduction(ArrayList<String> parse) {

		for (int i = 0; i < parse.get(findLongerList(parse)).length(); i++) {
			if (i == 0) {
			for (int j = findLongerList(parse); j < parse.size(); j++) {
				if (parse.get(j).charAt(i) != '-' && parse.get(j).charAt(i) != '|') {
					break;
				}	else if (parse.get(j).charAt(i) == '|') {
				}	else {
					parse.set(i, parse.get(i).substring(i+1));
				}
			}
			}	else {
				for (int j = 0; j < parse.size(); j++) {
					if (j < findLongerList(parse)) {
						int k = i - 1;
						if (parse.get(j).charAt(k) != '-' && parse.get(j).charAt(k) != '|') {
							break;
						}	else if (parse.get(j).charAt(k) == '|') {
						}	else {
							parse.set(k, parse.get(k).substring(k+1));
						}
					}	else {
						if (parse.get(j).charAt(i) != '-' && parse.get(j).charAt(i) != '|') {
							break;
						}	else if (parse.get(j).charAt(i) == '|') {
						}	else {
							parse.set(i, parse.get(i).substring(i+1));
						}
					}
				}
			}
		}
		return parse;
	}
	//Counts the division of the whole tablature
	public static int divisionCount(ArrayList<String> parse) {
		int k = 0;
		// loop used to count the divisions of the tablature
				for (int j = 0; j < findLongerList(parse); j++) {
					if (parse.get(0).charAt(j) == '|') {
						if (parse.get(0).charAt(j - 1) == '-' && parse.get(0).charAt(j + 1) == '-') {
							break;
						}
					}	else { 
						k++;
					}
				}
		k = k - 2;
		k = k / 4;
		return k;
	}
	//Counts the fret
	public static int fretCount(ArrayList<String> parse) {
		int fret = parse.get(findLongerList(parse)).charAt(0);
		return fret;
	}
	//Counts the step of the note
	public static String stepCount(ArrayList<String> parse) {
		String step = "";
		if (findLongerList(parse) == 0) {
			
		}	else if (findLongerList(parse) == 1) {
			
		}	else if (findLongerList(parse) == 2) {
			
		}	else if (findLongerList(parse) == 3) {
			
		}	else if (findLongerList(parse) == 4) {
			
		}	else  {
			
		}
		return step;
	}
}
