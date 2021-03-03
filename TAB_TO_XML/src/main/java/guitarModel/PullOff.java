package guitarModel;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class PullOff {
	@JacksonXmlProperty(isAttribute=true)
	String number;
	
	@JacksonXmlProperty(isAttribute=true)
	String type;
	
	String value;
	
	public PullOff() {}
	public PullOff(String number, String type) {
		this.number = number;
		this.type = type;
	}
	public PullOff(String number, String type, String value) {
		this.number = number;
		this.type = type;
		this.value = value;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
