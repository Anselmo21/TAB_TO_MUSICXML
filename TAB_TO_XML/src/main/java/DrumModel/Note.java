package DrumModel;

import Model.Notations;
import Model.Pitch;

public class Note {
	Unpitched unpitch;
	String duration; 
	//instrument id
	String voice; 
	String type; 
	String stem;
	String notehead;
	//beam number
	
	public void setUnpitch(Unpitched pitch) { 
		unpitch = unpitch;
		
	}
	
	public Unpitched getUnpitch() { 
		
		return unpitch;
		
	}
	
	public void setDuration(String duration) { 
		
		this.duration = duration; 
		
	}
	
	public String getDuration() { 
		
		return duration; 
		
	}
	
	public void setVoice(String voice) { 
		
		this.voice = voice;  
		
	}
	
	public String getVoice() { 
		
		return voice; 
		
	}
	
	public void setType(String type) { 
		
		this.type = type; 
		
	
	}
	
	public String getType() { 
		
		return type;
		
	}
	
	public void setStem(String stem) { 
		
		this.stem = stem; 
		
	}
	
	public String getStem() { 
		
		return stem;
		
	}
	
	public void setNoteHead(String note) {
		
		notehead = note;
		
	}
	
	public Note() {}
	public Note(Unpitched pitch, String duration, String voice, String stem, String notehead) {
		
		
		super();
		this.unpitch = pitch;
		this.duration = duration;
		this.voice = voice;
		this.type = type;
		this.stem = stem;
		this.voice = voice;
		this.notehead = notehead;
		
	}
	
}
