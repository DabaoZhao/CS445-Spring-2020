package org.pop.rs.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarInfo {

	@JsonProperty("make")
	private String make;

	@JsonProperty("model")
	private String model;

	@JsonProperty("color")
	private String color;

	@JsonProperty("plate_state")
	private String plate_state;

	@JsonProperty("plate_serial")
	private String plate_serial;

	public CarInfo() {

	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setPlate_serial(String plate_serial) {
		this.plate_serial = plate_serial;
	}

	public void setPlate_state(String plate_state) {
		this.plate_state = plate_state;
	}
}
