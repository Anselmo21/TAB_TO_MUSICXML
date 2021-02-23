package Model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StaffDetails {
	@JacksonXmlProperty(localName = "staff-lines")
	String staffLines;

	@JacksonXmlProperty(localName = "staff-tuning")
	@JacksonXmlElementWrapper(useWrapping = false)
	StaffTuning[] staffTunings;

	public String getStaffLines() {
		return staffLines;
	}

	public void setStaffLines(String staffLines) {
		this.staffLines = staffLines;
	}

	public StaffTuning[] getStaffTunings() {
		return staffTunings;
	}

	public void setStaffTunings(StaffTuning[] staffTunings) {
		this.staffTunings = staffTunings;
	}

	public StaffDetails() {}
	public StaffDetails(String staffLines, StaffTuning[] staffTunings) {
		super();
		this.staffLines = staffLines;
		this.staffTunings = staffTunings;
	}
}
