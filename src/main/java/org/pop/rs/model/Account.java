package org.pop.rs.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account {

	@JsonProperty("aid")
	private String aid;

	@JsonProperty("first_name")
	private String firstName;

	@JsonProperty("last_name")
	private String lastName;

	@JsonProperty("picture")
	private String picture="";

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("is_active")
	private boolean isActive;


	public Account() {
	}

	public Account(String aid, String firstName, String lastName, String picture, String phone, boolean isActive) {
		this.aid = aid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.picture = picture;
		this.phone = phone;
		this.isActive = isActive;
	}

	public Account(String aid) {
		this.aid =aid;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public Account(String aid, String firstName, String lastName, String phone, boolean isActive) {
		this.aid = aid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.isActive = isActive;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}



	@Override public String toString() {
		return "Account{" + "aid='" + aid + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", phone='" + phone + '\'' + ", isActive=" + isActive + '}';
	}
}
