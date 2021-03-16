package DrumModel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonPropertyOrder({"unpitch", "duration", "instrument", "voice", "type", "stem", "notehead", "beam1", "beam2"})
public class NoteNH2B extends Note{
	@JacksonXmlProperty(isAttribute=true)
	String location1;
	
	@JacksonXmlProperty(localName = "beam1")
	String beam1;
	
	@JacksonXmlProperty(isAttribute=true)
	String location2;
	
	@JacksonXmlProperty(localName = "beam2")
	String beam2;
	
	Unpitched unpitch;
	String duration; 
	Instrument instrument;
	String voice; 
	String type; 
	String stem;
	String notehead;
	
	public void setUnpitch(Unpitched pitch) { 
		unpitch = pitch;
		
	}
	
	public Unpitched getUnpitch() { 
		
		return unpitch;
		
	}
	
	public void setDuration(String duration) { 
		
		this.duration = duration; 
		
	}
	
	public String getDuration() { 
		
		return duration; 
		
	}
	
	public void setVoice(String voice) { 
		
		this.voice = voice;  
		
	}
	
	public String getVoice() { 
		
		return voice; 
		
	}
	
	public void setType(String type) { 
		
		this.type = type; 
		
	
	}
	
	public String getType() { 
		
		return type;
		
	}
	
	public void setStem(String stem) { 
		
		this.stem = stem; 
		
	}
	
	public String getStem() { 
		
		return stem;
		
	}
	
	public void setInstrument(Instrument instrument) { 
		this.instrument = instrument;
		
	}
	public Instrument getInstrument() { 
		
		return instrument; 
		
	}
	
	public void setNoteHead(String notehead) {
		this.notehead = notehead;
	}
	
	public String getNoteHead() {
		return notehead;
	}
	
	public void setBeam1(String beam) {
		this.beam1 = beam;
	}
	
	public String getBeam1() {
		return beam1;
	}
	
	public void setLocation1(String location) {
		this.location1 = location;
	}
	
	public String getLocation1() {
		return location1;
	}
	
	public void setBeam2(String beam) {
		this.beam2 = beam;
	}
	
	public String getBeam2() {
		return beam2;
	}
	
	public void setLocation2(String location) {
		this.location2 = location;
	}
	
	public String setLocation2() {
		return location2;
	}
	
	public NoteNH2B() {}
	public NoteNH2B(Unpitched pitch, String duration, String voice, String stem, String type, Instrument instrument, String notehead, String beam1, String location1, String beam2, String location2) {
		
		super();
		this.beam1 = beam1;
		this.location1 = location1;
		this.beam2 = beam2;
		this.location2 = location2;
		this.instrument = instrument;
		this.unpitch = pitch;
		this.duration = duration;
		this.voice = voice;
		this.type = type;
		this.stem = stem;
		this.voice = voice;
		this.notehead = notehead;
		
	}
}
