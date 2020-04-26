package org.pop.rs.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationInfo {

	@JsonProperty("from_city")
	private String fromCity;

	@JsonProperty("from_zip")
	private String fromZip;

	@JsonProperty("to_city")
	private String toCity;

	@JsonProperty("to_zip")
	private String toZip;


	public LocationInfo() {

	}

	public String getFromCity() {
		return fromCity;
	}

	public String getToCity() {
		return toCity;
	}

	public String getFromZip() {
		return fromZip;
	}

	public String getToZip() {
		return toZip;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public void setFromZip(String fromZip) {
		this.fromZip = fromZip;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public void setToZip(String toZip) {
		this.toZip = toZip;
	}
}
