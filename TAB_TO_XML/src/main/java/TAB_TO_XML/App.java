package TAB_TO_XML;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import Model.*;

public class App {

	public static void main(String[] args) {
		try {
			ObjectMapper mapper = new XmlMapper();
//			InputStream inputStream = new FileInputStream("C:\\Users\\shawn\\Desktop\\parts1.xml");
//			TypeReference<List<Part>> typeReference = new TypeReference<List<Part>>() {};
//			List<Part> parts = mapper.readValue(inputStream, typeReference);
//			
//			for (Part p : parts) {
//				System.out.println("name is: " + p.getId());
//			}

			// Will stay the same until line 77
			ScorePartwise scorePartwise = new ScorePartwise();
			scorePartwise.setVersion("3.1");
			PartList partList = new PartList();
			ArrayList<ScorePart> scoreParts = new ArrayList<ScorePart>();
			ScorePart scorepart = new ScorePart();
			scorepart.setId("P1");
			scorepart.setPartName("Classical Guitar");
			scoreParts.add(scorepart);
			
			partList.setScoreParts(scoreParts);
			scorePartwise.setPartList(partList);

			ArrayList<Part> parts = new ArrayList<Part>();
			Part part = new Part();
			part.setId("P1");
			parts.add(part);
						
			ArrayList<Measure> measures = new ArrayList<Measure>(); 
			
			
//			Barline barline = new Barline();
//			barline.setBarStyle("light-heavy");
//			barline.setLocation("right");
//			measures.get(0).setBarline(barline);
			
			// read input file, store in array list
			ArrayList<String> storeFile = new ArrayList<>();
			storeFile = Parser.readLineByLine(Parser.getPath());
			
			
			// get a set of collections
			ArrayList<ArrayList<String>> collections = new ArrayList<>();
			collections = Parser.method1(storeFile);
			
			
			int measureCount = 0;
			// iter through each collection
			for (int i = 0; i < collections.size(); i++) {
				ArrayList<ArrayList<String>> measuresOfCollection = Parser.method2(collections.get(i));
				
				// iter through each measure set
				for(int j = 0; j < measuresOfCollection.size(); j++) {
					measureCount++;
					Measure newMeasure = parseMeasure(measuresOfCollection.get(j), measureCount);
					measures.add(newMeasure);
				}
			}

			parts.get(0).setMeasures(measures);
			
			scorePartwise.setParts(parts);

			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			mapper.writeValue(new File("./Streamresult.musicxml"), scorePartwise);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	private static Measure parseMeasure(ArrayList<String> meas, int measureNumber) {
		
		Measure newMeasure = new Measure();
		newMeasure.setNumber(measureNumber);
		int division = Parser.divisionCount(meas);
		// if first measure, set the attributes
		if (measureNumber == 1) {
			Attributes attributes = new Attributes();
			Clef clef = new Clef();
			clef.setSign("TAB");
			clef.setLine("5");
			attributes.setClef(clef);

			attributes.setDivisions(division);

			Key key = new Key();
			key.setFifths("0");
			attributes.setKey(key);

			StaffDetails staffDetails = new StaffDetails();
			staffDetails.setStaffLines("6");
			
			// staff tunings
			ArrayList<StaffTuning> staffTunings = new ArrayList<StaffTuning>();
			StaffTuning staffTuning0 = new StaffTuning();
			staffTuning0.setLine(1);
			staffTuning0.setTuningStep("E");
			staffTuning0.setTuningOctave("2");
			staffTunings.add(staffTuning0);
			
			StaffTuning staffTuning1 = new StaffTuning(); 
			staffTuning1.setLine(2);
			staffTuning1.setTuningStep("A");
			staffTuning1.setTuningOctave("2");
			staffTunings.add(staffTuning1);
			
			StaffTuning staffTuning2 = new StaffTuning();
			staffTuning2.setLine(3);
			staffTuning2.setTuningStep("D");
			staffTuning2.setTuningOctave("3");
			staffTunings.add(staffTuning2);
			
			StaffTuning staffTuning3 = new StaffTuning();
			staffTuning3.setLine(4);
			staffTuning3.setTuningStep("G");
			staffTuning3.setTuningOctave("3");
			staffTunings.add(staffTuning3);
			
			StaffTuning staffTuning4 = new StaffTuning();
			staffTuning4.setLine(5);
			staffTuning4.setTuningStep("B");
			staffTuning4.setTuningOctave("3");
			staffTunings.add(staffTuning4);
			
			StaffTuning staffTuning5 = new StaffTuning();
			staffTuning5.setLine(6);
			staffTuning5.setTuningStep("E");
			staffTuning5.setTuningOctave("4");
			staffTunings.add(staffTuning5);
			
			staffDetails.setStaffTunings(staffTunings);
			attributes.setStaffDetails(staffDetails);

			Time time = new Time();
			time.setBeats("4");
			time.setBeatType("4");
			attributes.setTime(time);

			newMeasure.setAttributes(attributes);
		}
		else {
			newMeasure.setAttributes(null);
		}
		
		newMeasure.setBarline(null);

		
		ArrayList<Note> note = new ArrayList<Note>();
		
		// iter through each measure
		for (int y = 0; y < meas.get(0).length(); y++) {
			Boolean hasPrevColNote = false;
			for (int x = 0; x < meas.size(); x++) {
				char character = meas.get(x).charAt(y);

				if (Character.isDigit(character)) {
					// if has previous note in column
					if (hasPrevColNote) {
						note.add(new ChordNote());
					}
					else {
						note.add(new Note());
					}
					
					Integer duration = Parser.durationCount(meas, y, division);
					note.get(note.size()-1).setDuration(duration.toString());
					
					note.get(note.size()-1).setType(Parser.typeDeclare(duration));
					note.get(note.size()-1).setVoice("1");
					
					
					// if the note is length 2, it contains a sharp
					if (Parser.stepCount(x, Character.getNumericValue(character)).length() == 2) {
						AlteredPitch pitch = new AlteredPitch();
						pitch.setAlter("1");
						pitch.setStep(Parser.stepCount(x, Character.getNumericValue(character)).substring(0, 1));
						pitch.setOctave(Parser.octaveCount(x, Character.getNumericValue(character)));
						note.get(note.size()-1).setPitch(pitch);
					}
					else {
						Pitch pitch = new Pitch();
						pitch.setStep(Parser.stepCount(x, Character.getNumericValue(character)));
						pitch.setOctave(Parser.octaveCount(x, Character.getNumericValue(character)));
						note.get(note.size()-1).setPitch(pitch);
					}
					
					
					// set notations, technical is a sub-element of notations
					Notations notations = new Notations();
					Technical technical = new Technical();
					technical.setFret("" + character);
					Integer stringNumber = (x + 1);
					technical.setString(stringNumber.toString());
					notations.setTechnical(technical);
					note.get(note.size()-1).setNotations(notations);
					
					// set has note in the column to true
					hasPrevColNote = true;
				}
			}
		}
		
		newMeasure.setNote(note);

		return newMeasure;
	}

}
