package org.pop.rs.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Driver {

	private String sid;
	private String aid;

	public Driver(String sid) {
	this.sid = sid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	private String first_name;

	private Ratings[] detail;

	private Double average_rating;

	private int rides=0;

	private int ratings=0;

	private boolean isRider;

	public boolean isRider() {
		return isRider;
	}

	public void setRider(boolean rider) {
		isRider = rider;
	}

	public Driver() {

	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public Driver(String aid, String firstName, Ratings[] detail, Double averageRating, int rides, int ratings) {
		this.aid = aid;
		this.first_name = firstName;
		this.detail = detail;
		this.average_rating = averageRating;
		this.rides = rides;
		this.ratings = ratings;
	}

	public String getAid() {
		return aid;
	}

	public Ratings[] getDetail() {
		return detail;
	}

	public void setDetail(Ratings[] detail) {
		this.detail = detail;
	}

	public Double getAverage_rating() {
		return average_rating;
	}

	public void setAverage_rating(Double average_rating) {
		this.average_rating = average_rating;
	}

	public int getRides() {
		return rides;
	}

	public void setRides(int rides) {
		this.rides = rides;
	}

	public int getRatings() {
		return ratings;
	}

	public void setRatings(int ratings) {
		this.ratings = ratings;
	}
}
