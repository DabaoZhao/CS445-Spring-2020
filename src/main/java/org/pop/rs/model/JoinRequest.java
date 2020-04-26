package org.pop.rs.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JoinRequest {

	private String jid;

	@JsonProperty("aid")
	private String aid;

	@JsonProperty("passengers")
	private int passengers;

	@JsonProperty("ride_confirmed")
	private Boolean rideConfirmed = null;

	@JsonProperty("pickup_confirmed")
	private Boolean pickupConfirmed = null;

	public JoinRequest() {
	}

	public JoinRequest(String jid) {
		this.jid = jid;
	}

	public JoinRequest(String jid,String aid) {
		this.jid = jid;
		this.aid =aid;
	}

	public JoinRequest(String aid, Boolean rideConfirmed) {
		this.aid = aid;
		this.rideConfirmed = rideConfirmed;
	}

	public String getJid() {
		return jid;
	}

	public void setJid(String jid) {
		this.jid = jid;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}

	public Boolean getRideConfirmed() {
		return rideConfirmed;
	}

	public void setRideConfirmed(Boolean rideConfirmed) {
		this.rideConfirmed = rideConfirmed;
	}

	public Boolean getPickupConfirmed() {
		return pickupConfirmed;
	}

	public void setPickupConfirmed(Boolean pickupConfirmed) {
		this.pickupConfirmed = pickupConfirmed;
	}
}
