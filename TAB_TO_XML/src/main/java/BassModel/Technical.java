package BassModel;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import guitarModel.HammerOn;
import guitarModel.PullOff;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Technical {
	String string;
	String fret;
	
	@JacksonXmlProperty(localName = "hammer-on")
	HammerOn hammer; 
	
	/*
	 * This has to be an array list because for some reason some notes have
	 * 2 pull offs and I'm not sure how much more it can have
	 * 
	 */
	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "pull-off")
	ArrayList<PullOff> pulloff;
	
	
	public HammerOn getHammerOn() { 
		return hammer;
		
	}
	
	public void setHammer(HammerOn h) { 
		
		hammer = h;
		
	}
	
	public ArrayList<PullOff> getPullOff() {
		
		return pulloff;
		
	}
	
	public void setPullOff(ArrayList<PullOff> p) { 
		
		pulloff = p;
		
	}

	public Technical() {}
	public Technical(String string, String fret) {
		super();
		this.string = string;
		this.fret = fret;
	}
	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}
	public String getFret() {
		return fret;
	}
	public void setFret(String fret) {
		this.fret = fret;
	}
}
