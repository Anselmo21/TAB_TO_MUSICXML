package Model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * This class is meant for the drums only
 *
 */
public class Beam {
	
	@JacksonXmlProperty(isAttribute=true)
	int number; 
	
	public void setNumber(int num) { 
		this.number = num;
		
	}
	
	public int getNumber() {
		
		return this.number;
		
	}
}
