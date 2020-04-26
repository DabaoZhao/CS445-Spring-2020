package org.pop.rs.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ride {

	private String rid;

	@JsonProperty("aid")
	private String driverId;

	@JsonProperty("rdid")
	private String riderId;

	public String getRiderId() {
		return riderId;
	}

	public void setRiderId(String riderId) {
		this.riderId = riderId;
	}

	@JsonProperty("location_info")
	private LocationInfo locationInfo;

	@JsonProperty("date_time")
	private DateTime dateTime;

	@JsonProperty("car_info")
	private CarInfo carInfo;

	@JsonProperty("max_passengers")
	private int max_passengers;

	@JsonProperty("amount_per_passenger")
	private Double amount_per_passenger = null;

	@JsonProperty("conditions")
	private String conditions;

	public Ride() {
	}

	public Ride(String rid, String driverId, LocationInfo locationInfo, DateTime dateTime, CarInfo carInfo) {
		this.rid = rid;
		this.driverId = driverId;
		this.locationInfo = locationInfo;
		this.dateTime = dateTime;
		this.carInfo = carInfo;
	}

	public Ride(String rid) {
		this.setRid(rid);
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public LocationInfo getLocationInfo() {
		return locationInfo;
	}

	public void setLocationInfo(LocationInfo locationInfo) {
		this.locationInfo = locationInfo;
	}

	public DateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(DateTime dateTime) {
		this.dateTime = dateTime;
	}

	public CarInfo getCarInfo() {
		return carInfo;
	}

	public void setCarInfo(CarInfo carInfo) {
		this.carInfo = carInfo;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public void setMax_passengers(int max_passengers) {
		this.max_passengers = max_passengers;
	}

	public void setAmount_per_passenger(Double amount_per_passenger) {
		this.amount_per_passenger = amount_per_passenger;
	}

	public Double getAmount_per_passenger() {
		return amount_per_passenger;
	}

	public int getMax_passengers() {
		return max_passengers;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
}
