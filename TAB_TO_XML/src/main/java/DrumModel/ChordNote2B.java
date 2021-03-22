package DrumModel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonPropertyOrder({"chord", "unpitch", "duration", "instrument", "voice", "type", "stem", "beam1", "beam2"})
public class ChordNote2B extends Note{
		@JacksonXmlProperty(isAttribute=true)
		String location1;
		
		@JacksonXmlProperty(localName = "beam")
		@JacksonXmlElementWrapper(useWrapping = false)
		Beam[] beam = new Beam[2];
		
		@JacksonXmlProperty(isAttribute=true)
		String location2;
		
		@JacksonXmlProperty(localName = "chord")
		String chord;
		
		Unpitched unpitch;
		String duration; 
		Instrument instrument;
		String voice; 
		String type; 
		String stem;
		
		public void setChord() { 
			   this.chord = null;
			   
		}
		
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
		
		public void setBeam1(Beam beam) {
			this.beam[0] = beam;
		}
		
		public Beam getBeam1() {
			return beam[0];
		}
		
		public void setLocation1(String location) {
			this.location1 = location;
		}
		
		public String getLocation1() {
			return location1;
		}
		
		public void setBeam2(Beam beam) {
			this.beam[1] = beam;
		}
		
		public Beam getBeam2() {
			return beam[1];
		}
		
		public void setLocation2(String location) {
			this.location2 = location;
		}
		
		public String setLocation2() {
			return location2;
		}
		
		public ChordNote2B() {}
}
