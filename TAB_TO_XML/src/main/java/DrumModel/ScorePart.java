package DrumModel;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.ArrayList;

public class ScorePart {
	@JacksonXmlProperty(isAttribute=true)
	String id;

	@JacksonXmlProperty(localName = "part-name")
	String partName; 
	
	// These aren't working for some reason
	@JacksonXmlProperty(localName = "score-instrument")
    @JacksonXmlElementWrapper(useWrapping = false)
	ArrayList<ScoreInstrument> instruments;
	

	public ScorePart() {}
	
	public void setScoreInstrument(ArrayList<ScoreInstrument> instruments) { 
		
		this.instruments = instruments;
		
	}
	
	public ArrayList<ScoreInstrument> getScoreInstrument() { 
		
		return this.instruments;
	}
	/**
	 * <p> This a constructor specifically for drums </p>
	 * @param id
	 * @param partName
	 * @param instruments
	 */
	public ScorePart(String id, String partName, ArrayList<ScoreInstrument> instruments) { 
		
		super(); 

		this.id = id; 
		this.partName = partName; 
		this.instruments = instruments;
		
		
	}
	public ScorePart(String id, String partName) {
		super();
		this.id = id;
		this.partName = partName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}
}

