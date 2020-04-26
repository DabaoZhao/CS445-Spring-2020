package org.pop.rs.model.report;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Report {

	@JsonProperty("pid")
	private String pid;

	@JsonProperty("name")
	private String name;

	@JsonProperty("start_date")
	private String startDate;

	@JsonProperty("end_date")
	private String endDate;

	@JsonProperty("rides")
	private int rides;

	@JsonProperty("detail")
	private ReportDetail[] detail;

	public Report(String pid, String name) {
		this.setPid(pid);
		this.setName(name);
	}


    public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRides(int rides) {
		this.rides = rides;
	}

	public void setDetail(ReportDetail[] detail) {
		this.detail = detail;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getRides() {
		return rides;
	}

	public ReportDetail[] getDetail() {
		return detail;
	}
}
