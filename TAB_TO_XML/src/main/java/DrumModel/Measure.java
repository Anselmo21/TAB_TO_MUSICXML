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
	ArrayList<Object> note;
	Barline barline;
	Backup backup;
	
	@JacksonXmlProperty(localName = "note")
    @JacksonXmlElementWrapper(useWrapping = false)
	ArrayList<Object> noteBack;

	public ArrayList<Object> getNoteBack() {
		return noteBack;
	}

	public void setNoteBack(ArrayList<Object> noteBack) {
		this.noteBack = noteBack;
	}

	public Backup getBackup() {
		return backup;
	}

	public void setBackup(Backup backup) {
		this.backup = backup;
	}

	public Measure() {}
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public ArrayList<Object> getNote() {
		return note;
	}
	public void setNote(ArrayList<Object> note) {
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
