package guitarModel;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Barline {

	@JacksonXmlProperty(isAttribute=true)
	private String location;

	@JacksonXmlProperty(localName = "bar-style")
	private String barStyle;

	public Barline() {}
	public Barline(String location, String barStyle) {
		super();
		this.location = location;
		this.barStyle = barStyle;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBarStyle() {
		return barStyle;
	}

	public void setBarStyle(String barStyle) {
		this.barStyle = barStyle;
	}

}
