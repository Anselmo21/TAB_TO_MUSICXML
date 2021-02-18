package Model;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Measure {

	@JacksonXmlProperty(isAttribute=true)
	int number;

    @JacksonXmlElementWrapper(useWrapping = false)
	Note[] note;
	Attributes attributes;
	Barline barline;

	public Measure() {}
	public Measure(int number, Note[] note, Attributes attributes, Barline barline) {
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
	public Note[] getNote() {
		return note;
	}
	public void setNote(Note[] note) {
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

}
