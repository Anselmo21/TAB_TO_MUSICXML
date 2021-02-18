package Model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Part {

	@JacksonXmlProperty(isAttribute = true)
	String id;

	@JacksonXmlProperty(localName = "measure")
    @JacksonXmlElementWrapper(useWrapping = false)
	public Measure[] measures;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Measure[] getMeasures() {
		return measures;
	}
	public void setMeasures(Measure[] measures) {
		this.measures = measures;
	}

	public Part() {}
	public Part(String id, Measure[] measures) {
		super();
		this.id = id;
		this.measures = measures;
	}

}
