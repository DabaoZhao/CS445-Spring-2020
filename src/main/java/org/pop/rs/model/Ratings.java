package org.pop.rs.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ratings {

	@JsonProperty("rid")
	private String rid;

	@JsonProperty("sent_by_id")
	private String sentById;

	@JsonProperty("first_name")
	private String firstName;

	@JsonProperty("rating")
	private int rating;

	@JsonProperty("date")
	private String date=setDate();

	@JsonProperty("comment")
	private String comment;

	public Ratings() {

	}

	public String getRid() {
		return rid;
	}

	public String getSentById() {
		return sentById;
	}

	public int getRating() {
		return rating;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public void setSentById(String sentById) {
		this.sentById = sentById;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String setDate() {
		DateFormat formatter = new SimpleDateFormat("dd-MMM-YYYY");
		Date date = new Date();
		return formatter.format(date);
	}
}
