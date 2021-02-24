package Model;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
public class Note {
	Pitch pitch;
	String duration;
	String voice;
	String type;
	Notations notations;
	
	//Drum fields only
	//Beam value; 
	//String notehead;
	//Unpitched unpitched_value;
	//Missing instrument ID...no clue how to do that and what that means for the note
	
   
    
//	public String getNoteHead() { 
	//	return null;
		//return this.notehead;
		
	//}
	
	//public void setNoteHead(String note) { 
		
		//this.notehead = note;
		
	//}
	
	//public String getBeamValue() { 
		
		//return null;
		
	//}
	
	public Pitch getPitch() {
		return pitch;
	}
	public void setPitch(Pitch pitch) {
		this.pitch = pitch;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getVoice() {
		return voice;
	}
	public void setVoice(String voice) {
		this.voice = voice;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Notations getNotations() {
		return notations;
	}
	public void setNotations(Notations notations) {
		this.notations = notations;
	}

	
	public Note() {}
	public Note(Pitch pitch, String duration, String voice, String type, Notations notations) {
		super();
		this.pitch = pitch;
		this.duration = duration;
		this.voice = voice;
		this.type = type;
		this.notations = notations;
	}
}
