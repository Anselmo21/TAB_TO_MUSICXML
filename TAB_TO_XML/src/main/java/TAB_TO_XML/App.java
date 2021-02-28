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

import BassModel.*;
import DrumModel.*;
import guitarModel.*;

public class App {

	static String conversion;

	public static void main(String[] args) {
		
		switch (getInstrument()) {
			case "Guitar":
				conversion = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
						+ "<!DOCTYPE score-partwise PUBLIC \"-//Recordare//DTD MusicXML 3.1 Partwise//EN\" \"http://www.musicxml.org/dtds/partwise.dtd\">\n"
						+ guitarTabToXML();
				break;
			case "Bass":
				conversion = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
						+ "<!DOCTYPE score-partwise PUBLIC \"-//Recordare//DTD MusicXML 3.1 Partwise//EN\" \"http://www.musicxml.org/dtds/partwise.dtd\">\n"
						+ bassTabToXML();
				break;
			case "Drums":
				conversion = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
						+ "<!DOCTYPE score-partwise PUBLIC \"-//Recordare//DTD MusicXML 3.1 Partwise//EN\" \"http://www.musicxml.org/dtds/partwise.dtd\">\n"
						+ drumTabToXML();
				break;
			default:
				break;
		}
			
	}
	
	public static ArrayList<String> getFileList() {
		// read input file, store in array list
		return GuitarParser.readText(GuitarParser.getText()); // TODO: change location of the used methods here
	}
	
	public static String getInstrument() {
		
		return GuitarParser.identifyInstrument(getFileList());
	}

	public static String getConversion() {
		return conversion;
	}

	private static String bassTabToXML() {
		try {
			ObjectMapper mapper = new XmlMapper();

			// get a set of collections
			ArrayList<ArrayList<String>> collections = new ArrayList<>();
			collections = BParser.method1(getFileList());
			
			BassModel.ScorePartwise scorePartwise = new BassModel.ScorePartwise();
			scorePartwise.setVersion("3.1");
			BassModel.PartList partList = new BassModel.PartList();
			ArrayList<BassModel.ScorePart> scoreParts = new ArrayList<BassModel.ScorePart>();
			BassModel.ScorePart scorepart = new BassModel.ScorePart();
			scorepart.setId("P1");
			scorepart.setPartName("Bass Guitar");
			scoreParts.add(scorepart);

			partList.setScoreParts(scoreParts);
			scorePartwise.setPartList(partList);

			ArrayList<BassModel.Part> parts = new ArrayList<BassModel.Part>();
			BassModel.Part part = new BassModel.Part();
			part.setId("P1");
			parts.add(part);

			ArrayList<BassModel.Measure> measures = new ArrayList<BassModel.Measure>();

			int measureCount = 0;
			// iter through each collection
			for (int i = 0; i < collections.size(); i++) {
				ArrayList<ArrayList<String>> measuresOfCollection = BParser.method2(collections.get(i));

				// iter through each measure set
				for (int j = 0; j < measuresOfCollection.size(); j++) {
					measureCount++;
					BassModel.Measure newMeasure = parseBassMeasure(measuresOfCollection.get(j), measureCount);
					measures.add(newMeasure);
				}
			}

			// set last measure to have barline values
			BassModel.Barline barline = new BassModel.Barline();
			barline.setBarStyle("light-heavy");
			barline.setLocation("right");
			measures.get(measures.size() - 1).setBarline(barline);

			parts.get(0).setMeasures(measures);

			scorePartwise.setParts(parts);

			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			// mapper.writeValue(new File("./Streamresult.musicxml"), scorePartwise);
			return mapper.writeValueAsString(scorePartwise);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	private static BassModel.Measure parseBassMeasure(ArrayList<String> meas, int measureNumber) {

		BassModel.Measure newMeasure = new BassModel.Measure();
		newMeasure.setNumber(measureNumber);
		int division = BParser.divisionCount(meas);
		// if first measure, set the attributes
		if (measureNumber == 1) {
			BassModel.Attributes attributes = new BassModel.Attributes();
			BassModel.Clef clef = new BassModel.Clef();
			clef.setSign("TAB");
			clef.setLine("2");
			attributes.setClef(clef);

			attributes.setDivisions(division);

			BassModel.Key key = new BassModel.Key();
			key.setFifths("0");
			attributes.setKey(key);

			BassModel.StaffDetails staffDetails = new BassModel.StaffDetails();
			staffDetails.setStaffLines("4");

			// staff tunings
			ArrayList<BassModel.StaffTuning> staffTunings = new ArrayList<BassModel.StaffTuning>();
			BassModel.StaffTuning staffTuning0 = new BassModel.StaffTuning();
			staffTuning0.setLine(1);
			staffTuning0.setTuningStep("G");
			staffTuning0.setTuningOctave("2");
			staffTunings.add(staffTuning0);

			BassModel.StaffTuning staffTuning1 = new BassModel.StaffTuning();
			staffTuning1.setLine(2);
			staffTuning1.setTuningStep("D");
			staffTuning1.setTuningOctave("2");
			staffTunings.add(staffTuning1);

			BassModel.StaffTuning staffTuning2 = new BassModel.StaffTuning();
			staffTuning2.setLine(3);
			staffTuning2.setTuningStep("A");
			staffTuning2.setTuningOctave("3");
			staffTunings.add(staffTuning2);

			BassModel.StaffTuning staffTuning3 = new BassModel.StaffTuning();
			staffTuning3.setLine(4);
			staffTuning3.setTuningStep("E");
			staffTuning3.setTuningOctave("3");
			staffTunings.add(staffTuning3);

		

			staffDetails.setStaffTunings(staffTunings);
			attributes.setStaffDetails(staffDetails);

			BassModel.Time time = new BassModel.Time();
			time.setBeats("4");
			time.setBeatType("4");
			attributes.setTime(time);

			newMeasure.setAttributes(attributes);
		} else {
			newMeasure.setAttributes(null);
		}

		newMeasure.setBarline(null);

		ArrayList<BassModel.Note> note = new ArrayList<BassModel.Note>();

		// iter through each measure
		for (int y = 0; y < meas.get(0).length(); y++) {
			Boolean hasPrevColNote = false;
			for (int x = meas.size() - 1; x >= 0; x--) {
				char character = meas.get(x).charAt(y);

				if (Character.isDigit(character)) {
					// if has previous note in column
					if (hasPrevColNote) {
						note.add(new BassModel.ChordNote());
					} else {
						note.add(new BassModel.Note());
					}

					Integer duration = BParser.durationCount(meas, y, division);
					note.get(note.size() - 1).setDuration(duration.toString());

					note.get(note.size() - 1).setType(BParser.typeDeclare(duration));
					note.get(note.size() - 1).setVoice("1");

					// if the note is length 2, it contains a sharp
					if (BParser.stepCount(x, Character.getNumericValue(character)).length() == 2) {
						BassModel.AlteredPitch pitch = new BassModel.AlteredPitch();
						pitch.setAlter("1");
						pitch.setStep(BParser.stepCount(x, Character.getNumericValue(character)).substring(0, 1));
						pitch.setOctave(BParser.octaveCount(x, Character.getNumericValue(character)));
						note.get(note.size() - 1).setPitch(pitch);
					} else {
						BassModel.Pitch pitch = new BassModel.Pitch();
						pitch.setStep(BParser.stepCount(x, Character.getNumericValue(character)));
						pitch.setOctave(BParser.octaveCount(x, Character.getNumericValue(character)));
						note.get(note.size() - 1).setPitch(pitch);
					}

					// set notations, technical is a sub-element of notations
					BassModel.Notations notations = new BassModel.Notations();
					BassModel.Technical technical = new BassModel.Technical();
					technical.setFret("" + character);
					Integer stringNumber = (x + 1);
					technical.setString(stringNumber.toString());
					notations.setTechnical(technical);
					note.get(note.size() - 1).setNotations(notations);

					// set has note in the column to true
					hasPrevColNote = true;
				}
			}
		}

		newMeasure.setNote(note);

		return newMeasure;
	}

	private static String guitarTabToXML() {
		try {
			ObjectMapper mapper = new XmlMapper();
//			InputStream inputStream = new FileInputStream("C:\\Users\\shawn\\Desktop\\parts1.xml");
//			TypeReference<List<Part>> typeReference = new TypeReference<List<Part>>() {};
//			List<Part> parts = mapper.readValue(inputStream, typeReference);
//			
//			for (Part p : parts) {
//				System.out.println("name is: " + p.getId());
//			}
			
			// get a set of collections
			ArrayList<ArrayList<String>> collections = new ArrayList<>();
			collections = GuitarParser.method1(getFileList());

			guitarModel.ScorePartwise scorePartwise = new guitarModel.ScorePartwise();
			scorePartwise.setVersion("3.1");
			guitarModel.PartList partList = new guitarModel.PartList();
			ArrayList<guitarModel.ScorePart> scoreParts = new ArrayList<guitarModel.ScorePart>();
			guitarModel.ScorePart scorepart = new guitarModel.ScorePart();
			scorepart.setId("P1");
			scorepart.setPartName("Classical Guitar");
			scoreParts.add(scorepart);

			partList.setScoreParts(scoreParts);
			scorePartwise.setPartList(partList);

			ArrayList<guitarModel.Part> parts = new ArrayList<guitarModel.Part>();
			guitarModel.Part part = new guitarModel.Part();
			part.setId("P1");
			parts.add(part);

			ArrayList<guitarModel.Measure> measures = new ArrayList<guitarModel.Measure>();

			int measureCount = 0;
			// iter through each collection
			for (int i = 0; i < collections.size(); i++) {
				ArrayList<ArrayList<String>> measuresOfCollection = GuitarParser.method2(collections.get(i));

				// iter through each measure set
				for (int j = 0; j < measuresOfCollection.size(); j++) {
					measureCount++;
					guitarModel.Measure newMeasure = parseGuitarMeasure(measuresOfCollection.get(j), measureCount);
					measures.add(newMeasure);
				}
			}

			// set last measure to have barline values
			guitarModel.Barline barline = new guitarModel.Barline();
			barline.setBarStyle("light-heavy");
			barline.setLocation("right");
			measures.get(measures.size() - 1).setBarline(barline);

			parts.get(0).setMeasures(measures);

			scorePartwise.setParts(parts);

			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			// mapper.writeValue(new File("./Streamresult.musicxml"), scorePartwise);
			return mapper.writeValueAsString(scorePartwise);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private static guitarModel.Measure parseGuitarMeasure(ArrayList<String> meas, int measureNumber) {

		guitarModel.Measure newMeasure = new guitarModel.Measure();
		newMeasure.setNumber(measureNumber);
		int division = GuitarParser.divisionCount(meas);
		// if first measure, set the attributes
		if (measureNumber == 1) {
			guitarModel.Attributes attributes = new guitarModel.Attributes();
			guitarModel.Clef clef = new guitarModel.Clef();
			clef.setSign("TAB");
			clef.setLine("5");
			attributes.setClef(clef);

			attributes.setDivisions(division);

			guitarModel.Key key = new guitarModel.Key();
			key.setFifths("0");
			attributes.setKey(key);

			guitarModel.StaffDetails staffDetails = new guitarModel.StaffDetails();
			staffDetails.setStaffLines("6");

			// staff tunings
			ArrayList<guitarModel.StaffTuning> staffTunings = new ArrayList<guitarModel.StaffTuning>();
			guitarModel.StaffTuning staffTuning0 = new guitarModel.StaffTuning();
			staffTuning0.setLine(1);
			staffTuning0.setTuningStep("E");
			staffTuning0.setTuningOctave("2");
			staffTunings.add(staffTuning0);

			guitarModel.StaffTuning staffTuning1 = new guitarModel.StaffTuning();
			staffTuning1.setLine(2);
			staffTuning1.setTuningStep("A");
			staffTuning1.setTuningOctave("2");
			staffTunings.add(staffTuning1);

			guitarModel.StaffTuning staffTuning2 = new guitarModel.StaffTuning();
			staffTuning2.setLine(3);
			staffTuning2.setTuningStep("D");
			staffTuning2.setTuningOctave("3");
			staffTunings.add(staffTuning2);

			guitarModel.StaffTuning staffTuning3 = new guitarModel.StaffTuning();
			staffTuning3.setLine(4);
			staffTuning3.setTuningStep("G");
			staffTuning3.setTuningOctave("3");
			staffTunings.add(staffTuning3);

			guitarModel.StaffTuning staffTuning4 = new guitarModel.StaffTuning();
			staffTuning4.setLine(5);
			staffTuning4.setTuningStep("B");
			staffTuning4.setTuningOctave("3");
			staffTunings.add(staffTuning4);

			guitarModel.StaffTuning staffTuning5 = new guitarModel.StaffTuning();
			staffTuning5.setLine(6);
			staffTuning5.setTuningStep("E");
			staffTuning5.setTuningOctave("4");
			staffTunings.add(staffTuning5);

			staffDetails.setStaffTunings(staffTunings);
			attributes.setStaffDetails(staffDetails);

			guitarModel.Time time = new guitarModel.Time();
			time.setBeats("4");
			time.setBeatType("4");
			attributes.setTime(time);

			newMeasure.setAttributes(attributes);
		} else {
			newMeasure.setAttributes(null);
		}

		newMeasure.setBarline(null);

		ArrayList<guitarModel.Note> note = new ArrayList<guitarModel.Note>();

		// iter through each measure
		for (int y = 0; y < meas.get(0).length(); y++) {
			Boolean hasPrevColNote = false;
			for (int x = meas.size() - 1; x >= 0; x--) {
				char character = meas.get(x).charAt(y);

				if (Character.isDigit(character)) {
					// if has previous note in column
					if (hasPrevColNote) {
						note.add(new guitarModel.ChordNote());
					} else {
						note.add(new guitarModel.Note());
					}

					Integer duration = GuitarParser.durationCount(meas, y, division);
					note.get(note.size() - 1).setDuration(duration.toString());

					note.get(note.size() - 1).setType(GuitarParser.typeDeclare(duration));
					note.get(note.size() - 1).setVoice("1");

					// if the note is length 2, it contains a sharp
					if (GuitarParser.stepCount(x, Character.getNumericValue(character)).length() == 2) {
						guitarModel.AlteredPitch pitch = new guitarModel.AlteredPitch();
						pitch.setAlter("1");
						pitch.setStep(GuitarParser.stepCount(x, Character.getNumericValue(character)).substring(0, 1));
						pitch.setOctave(GuitarParser.octaveCount(x, Character.getNumericValue(character)));
						note.get(note.size() - 1).setPitch(pitch);
					} else {
						guitarModel.Pitch pitch = new guitarModel.Pitch();
						pitch.setStep(GuitarParser.stepCount(x, Character.getNumericValue(character)));
						pitch.setOctave(GuitarParser.octaveCount(x, Character.getNumericValue(character)));
						note.get(note.size() - 1).setPitch(pitch);
					}

					// set notations, technical is a sub-element of notations
					guitarModel.Notations notations = new guitarModel.Notations();
					guitarModel.Technical technical = new guitarModel.Technical();
					technical.setFret("" + character);
					Integer stringNumber = (x + 1);
					technical.setString(stringNumber.toString());
					notations.setTechnical(technical);
					note.get(note.size() - 1).setNotations(notations);

					// set has note in the column to true
					hasPrevColNote = true;
				}
			}
		}

		newMeasure.setNote(note);

		return newMeasure;
	}
	
	private static String drumTabToXML() {
		try {
			ObjectMapper mapper = new XmlMapper();

			// get a set of collections
			ArrayList<ArrayList<String>> collections = new ArrayList<>();
			collections = DParser.method1(getFileList());
			
			DrumModel.ScorePartwise scorePartwise = new DrumModel.ScorePartwise();
			scorePartwise.setVersion("3.1");
			DrumModel.PartList partList = new DrumModel.PartList();
			ArrayList<DrumModel.ScorePart> scoreParts = new ArrayList<DrumModel.ScorePart>();
			DrumModel.ScorePart scorepart = new DrumModel.ScorePart();
			scorepart.setId("P1");
			scorepart.setPartName("Drumset");
			scoreParts.add(scorepart);

			partList.setScoreParts(scoreParts);
			scorePartwise.setPartList(partList);

			ArrayList<DrumModel.Part> parts = new ArrayList<DrumModel.Part>();
			DrumModel.Part part = new DrumModel.Part();
			part.setId("P1");
			parts.add(part);

			ArrayList<DrumModel.Measure> measures = new ArrayList<DrumModel.Measure>();

			int measureCount = 0;
			// iter through each collection
			for (int i = 0; i < collections.size(); i++) {
				ArrayList<ArrayList<String>> measuresOfCollection = DParser.method2(collections.get(i));

				// iter through each measure set
				for (int j = 0; j < measuresOfCollection.size(); j++) {
					measureCount++;
					DrumModel.Measure newMeasure = parseDrumMeasure(measuresOfCollection.get(j), measureCount);
					measures.add(newMeasure);
				}
			}

			// set last measure to have barline values
			DrumModel.Barline barline = new DrumModel.Barline();
			barline.setBarStyle("light-heavy");
			barline.setLocation("right");
			measures.get(measures.size() - 1).setBarline(barline);

			parts.get(0).setMeasures(measures);

			scorePartwise.setParts(parts);

			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			// mapper.writeValue(new File("./Streamresult.musicxml"), scorePartwise);
			return mapper.writeValueAsString(scorePartwise);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private static DrumModel.Measure parseDrumMeasure(ArrayList<String> meas, int measureNumber) {

		DrumModel.Measure newMeasure = new DrumModel.Measure();
		newMeasure.setNumber(measureNumber);
		int division = DParser.divisionCount(meas);
		// if first measure, set the attributes
		if (measureNumber == 1) {
			DrumModel.Attributes attributes = new DrumModel.Attributes();
			DrumModel.Clef clef = new DrumModel.Clef();
			clef.setSign("percussion");
			clef.setLine("2");
			attributes.setClef(clef);

			attributes.setDivisions(division);

			DrumModel.Key key = new DrumModel.Key();
			key.setFifths("0");
			attributes.setKey(key);

			DrumModel.Time time = new DrumModel.Time();
			time.setBeats("4");
			time.setBeatType("4");
			attributes.setTime(time);

			newMeasure.setAttributes(attributes);
		} else {
			newMeasure.setAttributes(null);
		}

		newMeasure.setBarline(null);

		ArrayList<DrumModel.Note> note = new ArrayList<DrumModel.Note>();

		// iter through each measure
		for (int y = 0; y < meas.get(0).length(); y++) {
			Boolean hasPrevColNote = false;
			for (int x = meas.size() - 1; x >= 0; x--) {
				char character = meas.get(x).charAt(y);

				if (Character.isDigit(character)) {
					// if has previous note in column
					if (hasPrevColNote) {
						note.add(new DrumModel.Note());
					} else {
						note.add(new DrumModel.Note());
					}

					Integer duration = DParser.durationCount(meas, y);
					note.get(note.size() - 1).setDuration(duration.toString());

					note.get(note.size() - 1).setType(DParser.typeDeclare(duration));
					note.get(note.size() - 1).setVoice("1");

					// if the note is length 2, it contains a sharp
//					if (GuitarParser.stepCount(x, Character.getNumericValue(character)).length() == 2) {
//						DrumModel.AlteredPitch pitch = new DrumModel.AlteredPitch();
//						pitch.setAlter("1");
//						pitch.setStep(DParser.stepCount(x, Character.getNumericValue(character)).substring(0, 1));
//						pitch.setOctave(DParser.octaveCount(x, Character.getNumericValue(character)));
//						note.get(note.size() - 1).setPitch(pitch);
//					} else {
//						DrumModel.Pitch pitch = new DrumModel.Pitch();
//						pitch.setStep(DParser.stepCount(x, Character.getNumericValue(character)));
//						pitch.setOctave(DParser.octaveCount(x, Character.getNumericValue(character)));
//						note.get(note.size() - 1).setPitch(pitch);
//					}

					// set has note in the column to true
					hasPrevColNote = true;
				}
			}
		}

		newMeasure.setNote(note);

		return newMeasure;
	}

}
