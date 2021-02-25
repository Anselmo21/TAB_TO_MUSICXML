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
			Measure measure = new Measure();
			measure.setNumber(1);
			measures.add(measure);
			
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

			measures.get(0).setAttributes(attributes);
			Barline barline = new Barline();
			barline.setBarStyle("light-heavy");
			barline.setLocation("right");
			measures.get(0).setBarline(barline);
			

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
			
			ArrayList<Note> note = new ArrayList<Note>(); //Number of notes within the measure, BUT currently all notes within set of 6...
			for (int j = 0; j < numNotes; j++) {
				note.add(new Note());
				note.get(j).setDuration(Parser.durationCount(printArr)); // TODO: this is wrong
				// TO DO: check over this method
				note.get(j).setType(Parser.typeDeclare(printArr));
				note.get(j).setVoice("1");
				
				
				// set pitch
				Pitch pitch = new Pitch();
				pitch.setStep(Parser.stepCount(printArr));
				pitch.setOctave(Parser.octaveCount(printArr));
				note.get(j).setPitch(pitch);
				
				// set notations, technical is a sub-element of notations
				Notations notations = new Notations();
				Technical technical = new Technical();
				technical.setFret(Parser.fretCount(printArr));
				Integer stringNumber = 6 - Parser.findLongerList(printArr);
				technical.setString(stringNumber.toString());
				notations.setTechnical(technical);
				note.get(j).setNotations(notations);
				
				printArr = Parser.listReduction(printArr);
			}
			
			// TO FIX: right now we only support 1 measure and 1 part
			measures.get(0).setNote(note);
			parts.get(0).setMeasures(measures);
			
			scorePartwise.setParts(parts);

			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			mapper.writeValue(new File("./Streamresult.musicxml"), scorePartwise);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
