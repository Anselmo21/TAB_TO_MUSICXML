package Model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class AlteredPitch extends Pitch {
	
	@JacksonXmlProperty(localName = "alter")
	String alter;

	public void setAlter(String alter) { 
		this.alter = alter; 
		
	}
	
	public String getAlter() { 
		
		return this.alter;
		
	}
}
