package BassModel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonPropertyOrder({ "grace", "pitch", "duration", "voice","stem", "type", "notations" })
public class BassGraceNote {
	@JacksonXmlProperty(localName = "grace")
	private String grace;

	@JacksonXmlProperty(localName = "stem")
	private String stem;
	
	
	public void setGrace() { 
		this.grace = null;
	}

	public void setStem() { 
		this.stem = "none";
	}

	//Default Constructor
	public BassGraceNote() {}
	
}

