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
	
	public static String runConversion(String tab) {
		String conversion = null;
		
		switch (getInstrument(tab)) {
		case "Guitar":
			conversion = guitarTabToXML(getFileList(tab));
			break;
		case "Bass":
			conversion = bassTabToXML(getFileList(tab));
			break;
		case "Drums":
			conversion = drumTabToXML(getFileList(tab));
			break;
		default:
			break;
		}

		if (conversion != null) {
			conversion = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
					+ "<!DOCTYPE score-partwise PUBLIC \"-//Recordare//DTD MusicXML 3.1 Partwise//EN\" \"http://www.musicxml.org/dtds/partwise.dtd\">\n"
					+ conversion;
		}
		
		return conversion;
	}

	public static ArrayList<String> getFileList(String text) {
		// read input file, store in array list
		//return GuitarParser.readText(GuitarParser.getText()); // TODO: change location of the used methods here

		ArrayList<String> textList = new ArrayList<>();
		Scanner in = null;
		try {
			in = new Scanner(text);
			while (in.hasNextLine())
				textList.add(in.nextLine());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			in.close();
		}
		return textList;
	}

	public static String getInstrument(String tab) {

		return identifyInstrument(getFileList(tab));
	}

	public static String bassTabToXML(ArrayList<String> tabAsList) {
		try {
			ObjectMapper mapper = new XmlMapper();

			// get a set of collections
			ArrayList<ArrayList<String>> collections = new ArrayList<>();
			collections = BParser.tabToCollection(tabAsList);

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
				ArrayList<ArrayList<String>> measuresOfCollection = BParser.collectionToMeasure(collections.get(i));

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
			staffTuning0.setTuningStep("E");
			staffTuning0.setTuningOctave("1");
			staffTunings.add(staffTuning0);

			BassModel.StaffTuning staffTuning1 = new BassModel.StaffTuning();
			staffTuning1.setLine(2);
			staffTuning1.setTuningStep("A");
			staffTuning1.setTuningOctave("1");
			staffTunings.add(staffTuning1);

			BassModel.StaffTuning staffTuning2 = new BassModel.StaffTuning();
			staffTuning2.setLine(3);
			staffTuning2.setTuningStep("D");
			staffTuning2.setTuningOctave("2");
			staffTunings.add(staffTuning2);

			BassModel.StaffTuning staffTuning3 = new BassModel.StaffTuning();
			staffTuning3.setLine(4);
			staffTuning3.setTuningStep("2");
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

	public static String guitarTabToXML(ArrayList<String> tabAsList) {
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
			collections = GuitarParser.tabToCollection(tabAsList);

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
				ArrayList<ArrayList<String>> measuresOfCollection = GuitarParser.collectionToMeasure(collections.get(i));

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
		for (int y = meas.get(0).indexOf("|") + 1; y < meas.get(0).length(); y++) {
			Boolean hasPrevColNote = false;
			int nextColumn = 0;
			int prevColumn = 0;
			if (y + 1 < meas.get(0).length()) { //prevent index out of bounds
				nextColumn = y + 1;
			}
			if (y - 1 > meas.get(0).indexOf("|") + 1) {
				prevColumn = y - 1;
			}
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
					ArrayList<guitarModel.PullOff> pullList = new ArrayList<guitarModel.PullOff>();
					ArrayList<guitarModel.HammerOn> hammerList = new ArrayList<guitarModel.HammerOn>();
					
				
					//consecutive pull offs 
					if(character == 'p' || character == 'P' && Character.isDigit(meas.get(x).charAt(prevColumn)) && Character.isDigit(meas.get(x).charAt(nextColumn))) { 
						if (meas.get(x).charAt(nextColumn + 1) == 'p' || meas.get(x).charAt(nextColumn + 1) == 'P') {
							guitarModel.PullOff before = new guitarModel.PullOff();
							guitarModel.PullOff after = new guitarModel.PullOff();
							before.setNumber(1);
							after.setNumber(1);
							before.setType("stop");
							after.setType("start");
							after.setSymbol("P");
							pullList.add(before);
							pullList.add(after);
							technical.setPull(pullList);
							notations.setSlur(null);
						}
					}
					//consecutive hammer-ons
					if ((character == 'h') || (character == 'H') && Character.isDigit(meas.get(x).charAt(prevColumn)) && Character.isDigit(meas.get(x).charAt(nextColumn))) { 
						if (meas.get(x).charAt(nextColumn + 1) == 'h' || meas.get(x).charAt(nextColumn + 1) == 'H') {
							guitarModel.HammerOn before1 = new guitarModel.HammerOn();
							guitarModel.HammerOn after1 = new guitarModel.HammerOn();
							before1.setNumber(1);
							after1.setNumber(1);
							before1.setType("stop");
							after1.setType("start");
							after1.setSymbol("H");
							hammerList.add(before1);
							hammerList.add(after1);
							technical.setHammer(hammerList);
							notations.setSlur(null);
							
						}
					}
					
					//Pull-off techniques
					if (meas.get(x).charAt(prevColumn) == 'p' || meas.get(x).charAt(prevColumn) == 'P' && Character.isDigit(meas.get(x).charAt(prevColumn)) && Character.isDigit(meas.get(x).charAt(nextColumn))) {
						guitarModel.PullOff pull = new guitarModel.PullOff();
						pull.setNumber(1);
						pull.setType("stop");
						pullList.add(pull);
						guitarModel.Slur sl = new guitarModel.Slur(); 
						sl.setNumber(1); 
						sl.setType("stop");
						technical.setPull(pullList);
						notations.setSlur(sl);
						
					}
					
					
					//Pull-off techniques 
					if (meas.get(x).charAt(nextColumn) == 'p' || meas.get(x).charAt(nextColumn) == 'P' && Character.isDigit(meas.get(x).charAt(prevColumn)) && Character.isDigit(meas.get(x).charAt(nextColumn))) {
						guitarModel.PullOff pl = new guitarModel.PullOff(); 
						pl.setNumber(1);
						pl.setType("start");
						pl.setSymbol("P");
						pullList.add(pl);
						
						guitarModel.Slur su = new guitarModel.Slur();
						su.setNumber(1);
						su.setPlacement("above");
						su.setType("start");
						technical.setPull(pullList);
						notations.setSlur(su);
					
					}
					
						
						//Hammer-on technique
					if (meas.get(x).charAt(prevColumn) == 'h' || meas.get(x).charAt(prevColumn) == 'H' && Character.isDigit(meas.get(x).charAt(prevColumn)) && Character.isDigit(meas.get(x).charAt(nextColumn))) {
						guitarModel.HammerOn hammer = new guitarModel.HammerOn();
						hammer.setNumber(1);
						hammer.setType("stop");
						
						hammerList.add(hammer);
						
						guitarModel.Slur slur = new guitarModel.Slur(); 
						slur.setNumber(1);
						slur.setType("stop");
						technical.setHammer(hammerList);
						notations.setSlur(slur);
					}
					
					//Hammer-on technique
					if (meas.get(x).charAt(nextColumn) == 'h' || meas.get(x).charAt(nextColumn) == 'H' && Character.isDigit(meas.get(x).charAt(prevColumn)) && Character.isDigit(meas.get(x).charAt(nextColumn))) {
						
						guitarModel.HammerOn ham = new guitarModel.HammerOn();
						ham.setNumber(1);
						ham.setType("start");
						ham.setSymbol("H");
						hammerList.add(ham);
						
						guitarModel.Slur sr = new guitarModel.Slur(); 
						sr.setNumber(1);
						sr.setPlacement("above");
						sr.setType("start");
						technical.setHammer(hammerList);
						notations.setSlur(sr);
					}
		
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

	public static String drumTabToXML(ArrayList<String> tabAsList) {
		try {
			ObjectMapper mapper = new XmlMapper();

			// get a set of collections
			ArrayList<ArrayList<String>> collections = new ArrayList<>();
			collections = DParser.tabToCollection(tabAsList);

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
				ArrayList<ArrayList<String>> measuresOfCollection = DParser.collectionToMeasure(collections.get(i));

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
		DrumModel.Backup bup = new DrumModel.Backup();
		
		Integer backupcount = 0;

		// iter through each measure
		for (int y = 0; y < meas.get(0).length(); y++) {
			Boolean hasPrevColNote = false;
			for (int x = meas.size() - 2; x >= 0; x--) {
				char character = meas.get(x).charAt(y);

				if (character == 'x' || character == 'o') {
					// if has previous note in column
					Integer duration = DParser.durationCount(meas, y);
					
					if (hasPrevColNote == false) {
						if (character == 'o') {
							if (DParser.beamNumber(DParser.typeDeclare(duration)) == 0) {
								note.add(new DrumModel.Note());
							}	else if (DParser.beamNumber(DParser.typeDeclare(duration)) == 1) {
								DrumModel.Note1B newn = new DrumModel.Note1B();
								newn.setBeam(DParser.beamState(meas, x, y));
								newn.setLocation("1");
								note.add(newn);
							}	else if (DParser.beamNumber(DParser.typeDeclare(duration)) == 2) {
								DrumModel.Note2B newn = new DrumModel.Note2B();
								newn.setBeam1(DParser.beamState(meas, x, y));
								newn.setLocation1("1");
								newn.setBeam2(DParser.beamState(meas, x, y));
								newn.setLocation2("2");
								note.add(newn);
							}
						}	else if (character == 'x') {
							if (DParser.beamNumber(DParser.typeDeclare(duration)) == 0) {
								DrumModel.NoteNH newn = new DrumModel.NoteNH();
								newn.setNoteHead("x");
								note.add(newn);
							}	else if (DParser.beamNumber(DParser.typeDeclare(duration)) == 1) {
								DrumModel.NoteNH1B newn = new DrumModel.NoteNH1B();
								newn.setNoteHead("x");
								newn.setBeam(DParser.beamState(meas, x, y));
								newn.setLocation("1");
								note.add(newn);
							}	else if (DParser.beamNumber(DParser.typeDeclare(duration)) == 2) {
								DrumModel.NoteNH2B newn = new DrumModel.NoteNH2B();
								newn.setNoteHead("x");
								newn.setBeam1(DParser.beamState(meas, x, y));
								newn.setLocation1("1");
								newn.setBeam2(DParser.beamState(meas, x, y));
								newn.setLocation2("2");
								note.add(newn);
							}
						}	
						backupcount = backupcount + duration;
					}	else if (hasPrevColNote == true) {
						if (character == 'o') {
							if (DParser.beamNumber(DParser.typeDeclare(duration)) == 0) {
								note.add(new DrumModel.ChordNote());
							}	else if (DParser.beamNumber(DParser.typeDeclare(duration)) == 1) {
								DrumModel.ChordNote1B newn = new DrumModel.ChordNote1B();
								newn.setBeam(DParser.beamState(meas, x, y));
								newn.setLocation("1");
								note.add(newn);
							}	else if (DParser.beamNumber(DParser.typeDeclare(duration)) == 2) {
								DrumModel.ChordNote2B newn = new DrumModel.ChordNote2B();
								newn.setBeam1(DParser.beamState(meas, x, y));
								newn.setLocation1("1");
								newn.setBeam2(DParser.beamState(meas, x, y));
								newn.setLocation2("2");
								note.add(newn);
							}
						}	else if (character == 'x') {
							if (DParser.beamNumber(DParser.typeDeclare(duration)) == 0) {
								DrumModel.ChordNoteNH newn = new DrumModel.ChordNoteNH();
								newn.setNoteHead("x");
								note.add(newn);
							}	else if (DParser.beamNumber(DParser.typeDeclare(duration)) == 1) {
								DrumModel.ChordNoteNH1B newn = new DrumModel.ChordNoteNH1B();
								newn.setNoteHead("x");
								newn.setBeam(DParser.beamState(meas, x, y));
								newn.setLocation("1");
								note.add(newn);
							}	else if (DParser.beamNumber(DParser.typeDeclare(duration)) == 2) {
								DrumModel.ChordNoteNH2B newn = new DrumModel.ChordNoteNH2B();
								newn.setNoteHead("x");
								newn.setBeam1(DParser.beamState(meas, x, y));
								newn.setLocation1("1");
								newn.setBeam1(DParser.beamState(meas, x, y));
								newn.setLocation1("2");
								note.add(newn);
							}
						} 
					}
	
					note.get(note.size() - 1).setDuration(duration.toString());

					note.get(note.size() - 1).setType(DParser.typeDeclare(duration));

					Instrument instrument = new Instrument();
					instrument.setID(DParser.identifyID(x));
					note.get(note.size() - 1).setInstrument(instrument);

					note.get(note.size() - 1).setVoice("1");
					note.get(note.size() - 1).setStem("up");

					Unpitched unpitched = new Unpitched();
					unpitched.setDisplayOctave(DParser.octaveCount(x));
					unpitched.setDisplayStep(DParser.stepCount(x));
					note.get(note.size() - 1).setUnpitch(unpitched);

					// set has note in the column to true
					hasPrevColNote = true;
				}
			}
		}

		// backup here
				for (int y = 0; y < meas.get(0).length(); y++) {
					char character = meas.get(meas.size() - 1).charAt(y);
					if (character == 'o') {
						Integer n = backupcount - y;
						System.out.println(n);
						bup.setDuration(n.toString());
						break;
					}
				}

				Integer durwrest = 0;
				for (int y = 0; y < meas.get(0).length(); y++) {
					char character = meas.get(meas.size() - 1).charAt(y);
					if (character == 'o') {
						note.add(new DrumModel.Note());
						for (int x = meas.size() - 2; x >= 2; x--) {
							if (Character.isDigit(meas.get(x).charAt(y))) {
								for (int i = y + 1; i < meas.get(0).length(); i++) {
									int tmp = 0;
									if (Character.isDigit(meas.get(x).charAt(i))) {
										tmp++;
									} else {
										break;
									}
									if (tmp > durwrest) {
										durwrest = tmp;
									}
								}
							}
						}

						if (durwrest > 0) {
							note.get(note.size() - 1).setDuration(durwrest.toString());
							note.get(note.size() - 1).setType(DParser.typeDeclare(durwrest));
						} else {
							Integer duration = DParser.durationCount(meas, y);
							note.get(note.size() - 1).setDuration(duration.toString());
							note.get(note.size() - 1).setType(DParser.typeDeclare(duration));
						}

						Instrument instrument = new Instrument();
						instrument.setID(DParser.identifyID(meas.size() - 1));
						note.get(note.size() - 1).setInstrument(instrument);

						note.get(note.size() - 1).setVoice("2");
						note.get(note.size() - 1).setStem("down");

						Unpitched unpitched = new Unpitched();
						unpitched.setDisplayOctave(DParser.octaveCount(meas.size() - 1));
						unpitched.setDisplayStep(DParser.stepCount(meas.size() - 1));
						note.get(note.size() - 1).setUnpitch(unpitched);
					}
				}


		newMeasure.setNote(note);

		return newMeasure;
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
		if (helpMe(content) == 4) return "Bass";
		else if (helpMe(content) == 6) return "Guitar";
		
		return "No Instrument Detected";
	}

	/**
	 * This method helps identify if the instrument is a bass or a guitar.
	 * @param content
	 * @return
	 */
	public static int helpMe(ArrayList<String> content) {
		int count = 0;
		for (int i = 0; i < content.size(); i++) {
			if (!content.get(i).equals("")) count++;
			else break;
		}
		return count;
	}
}

//push to resolve conflict