package BassModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Slur {

	@JacksonXmlProperty(isAttribute=true)
	int number;
	
	@JacksonXmlProperty(isAttribute=true) 
	String placement;
	
	@JacksonXmlProperty(isAttribute=true)
	String type; 
	
	public void setNumber(int number) { 
		
		this.number = number; 
		
	}
	
	public int getNumber() { 
		return number; 
		
	}
	
	public void setPlacement(String p) { 
		
		placement = p; 
		
	}
	
	public String getPlacement() { 
		
		return placement; 
		
	}
	
	public void setType(String t) { 
		
		type = t; 
		
	}
	
	public String getType() { 
		return type;
		
	}
}