package DrumModel;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Unpitched {
	
	@JacksonXmlProperty(localName = "display-step")
	String displayStep;
	
	@JacksonXmlProperty(localName = "display-octave")
	String displayOctave;
	
	public void setDisplayStep(String ds) { 
		displayStep = ds;
		
	}
	
	public String getDisplayStep() { 
		return displayStep; 
		
	}
	
	public void setDisplayOctave(String dO) { 
		displayOctave = dO;
		
	}
	
	public String getDisplayOctave() { 
		return displayOctave;
		
	}

}

