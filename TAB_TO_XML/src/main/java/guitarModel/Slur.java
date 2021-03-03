package guitarModel;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Slur {
	@JacksonXmlProperty(isAttribute=true)
	String number; 
	
	@JacksonXmlProperty(isAttribute=true)
	String placement;
	
	@JacksonXmlProperty(isAttribute=true)
	String type;
	
	public Slur() {}
	public Slur(String number, String type) {
		this.number = number;
		this.type = type;
	}
	public Slur(String number, String placement, String type) {
		this.number = number;
		this.placement = placement;
		this.type = type;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setPlacement(String placement) {
		this.placement = placement;
	}
	
	public String getPlacement() {
		return placement;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
}
