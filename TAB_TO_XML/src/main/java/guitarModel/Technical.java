package guitarModel;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonPropertyOrder({"pulloff", "hammer", "string", "fret"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Technical {
	private String string;
	private String fret;
	
	@JacksonXmlProperty(localName = "hammer-on")
	private HammerOn hammer; 
	
	/*
	 * This has to be an array list because for some reason some notes have
	 * 2 pull offs and I'm not sure how much more it can have
	 * 
	 */

	@JacksonXmlProperty(localName = "pull-off")
	private PullOff pulloff;
	
	
	
	public HammerOn getHammer() {
		
		return hammer;
		
	}
	
	public void setHammer(HammerOn h) { 
		
		hammer = h;
		
	}
	
	public void setPull(PullOff pull) { 
		pulloff = pull;
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
