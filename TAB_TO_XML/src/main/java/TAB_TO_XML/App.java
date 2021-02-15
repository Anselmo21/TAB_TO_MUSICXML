package TAB_TO_XML;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

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
			staffDetails.setStaffLines("");
			staffDetails.setStaffTunings(null);
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
			
			Note[] note = new Note[1];
			note[0] = new Note();
			note[0].setDuration("1");
			
			Notations notations = new Notations();
			Technical technical = new Technical();
			technical.setFret("0");
			technical.setString("6");
			notations.setTechnical(technical);
			note[0].setNotations(notations);
			
			Pitch pitch = new Pitch();
			pitch.setStep("E");
			pitch.setOctave("2");
			note[0].setPitch(pitch);
			
			note[0].setType("eighth");
			note[0].setVoice("1");
			measures[0].setNote(note);
			parts[0].setMeasures(measures);
			
			scorePartwise.setParts(parts);
			
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			mapper.writeValue(new File("./Streamresult.musicxml"), scorePartwise);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
