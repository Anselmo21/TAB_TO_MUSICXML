package Model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ChordNote extends Note {
	
	@JacksonXmlProperty(localName = "chord")
	String chord;
	 public void setChord() { 
		   this.chord = null;
	   }
}
