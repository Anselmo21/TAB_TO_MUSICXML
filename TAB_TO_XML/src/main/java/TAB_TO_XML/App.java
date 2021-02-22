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
			ScorePart[] scoreParts = new ScorePart[1];
			scoreParts[0] = new ScorePart();
			scoreParts[0].setId("P1");
			scoreParts[0].setPartName("Classical Guitar");
			partList.setScoreParts(scoreParts);
			scorePartwise.setPartList(partList);

			Part[] parts = new Part[1];
			parts[0] = new Part();
			parts[0].setId("P1");
						
			Measure[] measures = new Measure[1];
			measures[0] = new Measure();
			measures[0].setNumber(1);

			Attributes attributes = new Attributes();
			Clef clef = new Clef();
			clef.setSign("TAB");
			clef.setLine("5");
			attributes.setClef(clef);
			attributes.setDivisions(2);

			Key key = new Key();
			key.setFifths("0");
			attributes.setKey(key);

			StaffDetails staffDetails = new StaffDetails();
			staffDetails.setStaffLines("6");
			
			// staff tunings
			StaffTuning[] staffTunings = new StaffTuning[6];
			staffTunings[0] = new StaffTuning();
			staffTunings[0].setLine(1);
			staffTunings[0].setTuningStep("E");
			staffTunings[0].setTuningOctave("2");
			
			staffTunings[1] = new StaffTuning();
			staffTunings[1].setLine(2);
			staffTunings[1].setTuningStep("A");
			staffTunings[1].setTuningOctave("2");
			
			staffTunings[2] = new StaffTuning();
			staffTunings[2].setLine(3);
			staffTunings[2].setTuningStep("D");
			staffTunings[2].setTuningOctave("3");
			
			staffTunings[3] = new StaffTuning();
			staffTunings[3].setLine(4);
			staffTunings[3].setTuningStep("G");
			staffTunings[3].setTuningOctave("3");
			
			staffTunings[4] = new StaffTuning();
			staffTunings[4].setLine(5);
			staffTunings[4].setTuningStep("B");
			staffTunings[4].setTuningOctave("3");
			
			staffTunings[5] = new StaffTuning();
			staffTunings[5].setLine(6);
			staffTunings[5].setTuningStep("E");
			staffTunings[5].setTuningOctave("4");
			
			staffDetails.setStaffTunings(staffTunings);
			attributes.setStaffDetails(staffDetails);

			Time time = new Time();
			time.setBeats("4");
			time.setBeatType("4");
			attributes.setTime(time);

			measures[0].setAttributes(attributes);
			Barline barline = new Barline();
			barline.setBarStyle("light-heavy");
			barline.setLocation("right");
			measures[0].setBarline(barline);

			ArrayList<String> storeFile = new ArrayList<>();
			storeFile = Parser.readLineByLine(Parser.getPath());
			
			
			// gets the first set of 6 strings, reduces tab until hits note within a column
			// the column with a note is kept and onwards
			// after the set of 6 strings is set, remove that set from the root
			ArrayList<String> printArr = new ArrayList<>();
			printArr = Parser.extractStrings(storeFile);
			storeFile = Parser.reduceRoot(storeFile);
			printArr = Parser.listReduction(printArr);
			
			for (int j = 0; j < printArr.get(0).length(); j++) {
				System.out.println("///////////////////");
				for (int i = 0; i < printArr.size(); i++) {
					System.out.println(printArr.get(i).charAt(j));
				}
			}
			
			// gets the number of notes, sets it to 'numNotes'
			int numNotes = Parser.countNote(printArr);
			//int numMeasures = Parser.countMeasures(printArr);
			//System.out.println(numMeasures);
			
			
			Note[] note = new Note[numNotes]; //Number of notes within the measure, BUT currently all notes within set of 6...
			for (int j = 0; j < numNotes; j++) {
				note[j] = new Note();
				note[j].setDuration(Parser.durationCount(printArr)); // TODO: this is wrong
				// TO DO: check over this method
				note[j].setType(Parser.typeDeclare(printArr));
				note[j].setVoice("1");
				
				// set pitch
				Pitch pitch = new Pitch();
				pitch.setStep(Parser.stepCount(printArr));
				pitch.setOctave(Parser.octaveCount(printArr));
				note[j].setPitch(pitch);
				
				// set notations, technical is a sub-element of notations
				Notations notations = new Notations();
				Technical technical = new Technical();
				technical.setFret(Parser.fretCount(printArr));
				Integer stringNumber = 6 - Parser.findLongerList(printArr);
				technical.setString(stringNumber.toString());
				notations.setTechnical(technical);
				note[j].setNotations(notations);
				
				printArr = Parser.listReduction(printArr);
			}
			
			// TO FIX: right now we only support 1 measure and 1 part
			measures[0].setNote(note);
			parts[0].setMeasures(measures);
			
			scorePartwise.setParts(parts);

			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			mapper.writeValue(new File("./Streamresult.musicxml"), scorePartwise);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
