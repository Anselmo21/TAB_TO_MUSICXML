package Model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class PartList {
	
	@JacksonXmlProperty(localName = "score-part")
	ScorePart[] scoreParts;

	public ScorePart[] getScoreParts() {
		return scoreParts;
	}

	public void setScoreParts(ScorePart[] scoreParts) {
		this.scoreParts = scoreParts;
	}
	
	
}
