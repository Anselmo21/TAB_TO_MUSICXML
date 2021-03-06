package guitarModel;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Notations {
	Technical technical;
	Slur slur;
	
	public Slur getSlur() { 
		
		return slur; 
		
	}
	
	public void setSlur(Slur s) { 
		
		slur = s; 
		
	}
	
	public Notations() {}
	public Notations(Technical technical) {
		super();
		this.technical = technical;
	}

	public Technical getTechnical() {
		return technical;
	}

	public void setTechnical(Technical technical) {
		this.technical = technical;
	}
}
