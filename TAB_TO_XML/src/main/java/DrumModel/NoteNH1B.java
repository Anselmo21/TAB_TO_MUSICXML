package DrumModel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonPropertyOrder({"unpitch", "duration", "instrument", "voice", "type", "stem", "notehead", "beam"})
public class NoteNH1B extends Note{
	@JacksonXmlProperty(isAttribute=true)
	String location;
	
	@JacksonXmlProperty(localName = "beam")
	String beam;
	
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
	
	public void setBeam(String beam) {
		this.beam = beam;
	}
	
	public String getBeam() {
		return beam;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getLocation() {
		return location;
	}
	
	public NoteNH1B() {}
	public NoteNH1B(Unpitched pitch, String duration, String voice, String stem, String type, Instrument instrument, String notehead, String beam, String location) {
		
		super();
		this.beam = beam;
		this.location = location;
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
