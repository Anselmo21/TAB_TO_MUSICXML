package BassModel;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class HammerOn {
	
	@JacksonXmlProperty(isAttribute=true)
	int number;
	
	@JacksonXmlProperty(isAttribute=true)
	String type; 
	
	//This field stores the symbol associated to the hammer on...which is either h or H
	String symbol;
	
	public int getNumber() {
		
		return number; 
		
	}
	
	public void setNumber(int num) { 
		
		number = num;
		
	}
	
	public String getType() { 
		
		return type; 
		
	}
	
	public void setType(String type) { 
		
		this.type = type; 
		
	}
	
	public void setSymbol(String sym) { 
		
		symbol = sym; 
		
	}
	
	public String getSymbol() { 
		
		return symbol;
		
	}
}