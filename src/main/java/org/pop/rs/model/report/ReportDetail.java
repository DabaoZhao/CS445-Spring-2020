package org.pop.rs.model.report;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportDetail {

	@JsonProperty("from_zip")
	private String fromZip;

	@JsonProperty("to_zip")
	private String toZip;

	@JsonProperty("count")
	private int count;

	public void setCount(int count) {
		this.count = count;
	}

	public void setFromZip(String fromZip) {
		this.fromZip = fromZip;
	}

	public void setToZip(String toZip) {
		this.toZip = toZip;
	}
}
