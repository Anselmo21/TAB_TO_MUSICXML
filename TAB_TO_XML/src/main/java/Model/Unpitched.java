
package Model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;


/**
 * 
 * This class is meant for the guitar only
 *
 */
public class Unpitched {
	
	@JacksonXmlProperty(localName = "display-step")
	String step; 
	
	@JacksonXmlProperty(localName = "display-octave") 
	int octave; 
	
	public Unpitched(String step, int oct) { 
		
		this.step = step;
		octave = oct; 
		
	}
	
	public void setStep(String step) {
		
		this.step = step;
		
	}
	
	public String getStep() { 
		
		return this.step;
		
	}
	
	public void setOctave(int oct) { 
		
		this.octave = oct;
		
	}
}
