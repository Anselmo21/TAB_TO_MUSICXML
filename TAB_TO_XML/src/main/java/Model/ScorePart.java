package Model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ScorePart {
	@JacksonXmlProperty(isAttribute=true)
	String id;

	@JacksonXmlProperty(localName = "part-name")
	String partName; 
	
	//@JacksonXmlProperty(localName = "score-instrument")
	//ScoreInstrument[] instruments; //Only for the drums!
	//Don't include in guitar implementation

	public ScorePart() {}
	
	/**
	 * <p> This a constructor specifically for drums </p>
	 * @param id
	 * @param partName
	 * @param instruments
	 */
	public ScorePart(String id, String partName, ScoreInstrument[] instruments) { 
		
		super(); 

		this.id = id; 
		this.partName = partName; 
		//this.instruments = instruments;
		
		
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
