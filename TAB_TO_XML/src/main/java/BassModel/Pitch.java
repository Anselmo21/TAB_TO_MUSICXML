package BassModel;

public class Pitch {
	String step;
	String octave;
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public String getOctave() {
		return octave;
	}
	public void setOctave(String octave) {
		this.octave = octave;
	}

	public Pitch() {}
	public Pitch(String step, String octave) {
		super();
		this.step = step;
		this.octave = octave;
	}
}
