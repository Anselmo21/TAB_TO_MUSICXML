package guitarModel;

public class Notations {
	Technical technical;
//	Slur slur;

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
	
//	public void setSlur(Slur slur) {
//		this.slur = slur;
//	}
//	
//	public Slur getSlur() {
//		return slur;
//	}
}
