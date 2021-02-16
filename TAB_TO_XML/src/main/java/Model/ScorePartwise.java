package Model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName="score-partwise")
public class ScorePartwise {

	@JacksonXmlProperty(isAttribute = true)

	public String version;
	
	@JacksonXmlProperty(localName = "part-list")

	public PartList partList;
	
	@JacksonXmlProperty(localName = "part")

    @JacksonXmlElementWrapper(useWrapping = false)
	public Part[] parts;
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public PartList getPartList() {
		return partList;
	}
	public void setPartList(PartList partList) {
		this.partList = partList;
	}
	public Part[] getParts() {
		return parts;
	}
	public void setParts(Part[] parts) {
		this.parts = parts;
	}
}
