package Model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Part {
	
	@JacksonXmlProperty(isAttribute = true)
	String id;
	Measure[] measure;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Measure[] getMeasure() {
		return measure;
	}
	public void setMeasure(Measure[] measure) {
		this.measure = measure;
	}

}
