package DrumModel;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Instrument {
	
	@JacksonXmlProperty(isAttribute=true)
	String id;
	
	public void setID(String id) { 
		this.id = id;
	}
}
