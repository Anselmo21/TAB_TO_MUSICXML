package guitarModel;

public class Technical {
	String string;
	String fret;
//	HammerOn hammerOn;
//	PullOff pullOff;

	public Technical() {}
	public Technical(String string, String fret) {
		super();
		this.string = string;
		this.fret = fret;
	}
	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}
	public String getFret() {
		return fret;
	}
	public void setFret(String fret) {
		this.fret = fret;
	}
	
//	public void setHammerOn(HammerOn hammerOn) {
//		this.hammerOn = hammerOn;
//	}
//	
//	public HammerOn getHammerOn() {
//		return hammerOn;
//	}
//	
//	public void setPullOff(PullOff pullOff) {
//		this.pullOff = pullOff;
//	}
//	
//	public PullOff getPullOff() {
//		return pullOff;
//	}
}
