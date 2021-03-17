package DrumModel;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Measure {
	@JacksonXmlProperty(isAttribute=true)
	int number;

	Attributes attributes;
	
    @JacksonXmlElementWrapper(useWrapping = false)
	ArrayList<Note> note;
	Barline barline;
	Backup backup;

	public Measure() {}
	public Measure(int number, ArrayList<Note> note, Attributes attributes, Barline barline, Backup backup) {
		super();
		this.number = number;
		this.note = note;
		this.attributes = attributes;
		this.barline = barline;
		this.backup = backup;
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
	public void setBackup(Backup backup) {
		this.backup = backup;
	}
	public Backup getBackup() {
		return backup;
	}

}
