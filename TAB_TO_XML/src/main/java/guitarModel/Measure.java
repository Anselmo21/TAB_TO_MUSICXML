package guitarModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"attributes", "barline", "direction", "note", "barline"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Measure {

	@JacksonXmlProperty(isAttribute=true)
	private int number;

	@JacksonXmlProperty(localName = "attributes")
	private Attributes attributes;
	
	@JacksonXmlProperty(localName = "direction")
	private GuitarDirection direction;
	 
    @JacksonXmlElementWrapper(useWrapping = false)
	private ArrayList<Note> note;
    
    @JacksonXmlProperty(localName = "barline")
	private ForwardBarline barline;
    
    @JacksonXmlProperty(localName = "barline")
    private BackwardBarline barline1;

   
    
	public Measure() {}
	public Measure(int number, ArrayList<Note> note, Attributes attributes, ForwardBarline barline) {
		super();
		this.number = number;
		this.note = note;
		this.attributes = attributes;
		this.barline = barline;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public ArrayList<Note> getNote() {
		return note;
	}
	public void setNote(ArrayList<Note> note) {
		this.note = note;
	}
	public Attributes getAttributes() {
		return attributes;
	}
	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}
	public 	ForwardBarline getBarline() {
		return barline;
	}
	public void setForwardBarline(ForwardBarline barline) {
		this.barline = barline;
	}
	
	public void setBackwardBarline(BackwardBarline barline) { 
		this.barline1 = barline;
	}
	
	public void setGuitarDirection(GuitarDirection dir) {
		
		this.direction = dir;
		
	}

	

}
