package guitarModel;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/*
 * This class is for the sake of creating the "direction" tag
 * to support repeated measures
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SpecialGuitarMeasure extends Measure {
	
	@JacksonXmlProperty(isAttribute=true)
	private int number;

	private Attributes attributes;
	
    @JacksonXmlElementWrapper(useWrapping = false)
	private ArrayList<Note> note;
	private Barline barline;

	@JacksonXmlProperty(localName = "direction")
	private GuitarDirection direction;
	
	public SpecialGuitarMeasure() {}
	public SpecialGuitarMeasure(int number, ArrayList<Note> note, Attributes attributes, Barline barline) {
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
	public Barline getBarline() {
		return barline;
	}
	public void setBarline(Barline barline) {
		this.barline = barline;
	}
	
	public void setGuitarDirection(GuitarDirection d) { 
		this.direction = d;
	}
	
}
