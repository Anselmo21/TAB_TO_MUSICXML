package DrumModel;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Beam {
	
	@JacksonXmlProperty(isAttribute=true)
	String id;
	
	public void setID(String id) { 
		
		this.id = id; 
		
	}
	
	public String getID() { 
		
		return id;
	}
}
