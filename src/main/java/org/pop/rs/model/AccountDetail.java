package org.pop.rs.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDetail {

	@JsonProperty("aid")
	private String aid;

	@JsonProperty("name")
	private String name;

	@JsonProperty("is_active")
	private boolean isActive;


	public AccountDetail() {
	}

	public AccountDetail(String aid, String name, boolean isActive) {
		this.aid = aid;
		this.name = name;
		this.isActive = isActive;
	}

	public AccountDetail(String aid) {
		this.aid =aid;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}


	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

}
