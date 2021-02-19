package Model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * THIS CLASS IS MEANT FOR THE DRUMS ONLY
 * DO NOT IMPLEMENT FOR THE GUITAR
 *
 */
public class ScoreInstrument {

	
	@JacksonXmlProperty(isAttribute=true)
	String id;
	
	@JacksonXmlProperty(localName = "instrument-name")
	String instrumentName;
	
	public ScoreInstrument(String id, String name) { 
		
		this.id = id;
		this.instrumentName = name;
		
	}
	
	public String getInstrumentName() {
		
		return this.instrumentName;
		
	}
	
	public void setInstrumentName(String name) { 
		
		this.instrumentName = name;
		
	}
	
	public String getID() {
		
		return this.id;
		
	}
	
	public void setID(String id) { 
		
		this.id = id;
		
	}
}
