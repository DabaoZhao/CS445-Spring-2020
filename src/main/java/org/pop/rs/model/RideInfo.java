package org.pop.rs.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class RideInfo {

    @JsonProperty("rid")
    private String rid;

    @JsonProperty("location_info")
    LocationInfo location_info;

    @JsonProperty("date_time")
    DateTime date_time;

    @JsonProperty("car_info")
    CarInfo car_Info;

    @JsonProperty("max_passengers")
    private int max_passengers;

    @JsonProperty("amount_per_passenger")
    private Double amount_per_passenger = null;

    @JsonProperty("conditions")
    private String conditions;

    @JsonProperty("driver")
    private String driver;

    @JsonProperty("driver_picture")
    private String driver_picture;

    @JsonProperty("rides")
    private int rides = 0;

    @JsonProperty("ratings")
    private int ratings = 0;

    @JsonProperty("average_rating")
    private Double average_rating=null;

    @JsonProperty("comments_about_driver")
    List<Ratings> comments_about_driver = new ArrayList<>();

    public RideInfo() {
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public void setLocation_info(LocationInfo location_info) {
        this.location_info = location_info;
    }

    public void setDate_time(DateTime date_time) {
        this.date_time = date_time;
    }

    public void setCar_Info(CarInfo car_Info) {
        this.car_Info = car_Info;
    }

    public void setMax_passengers(int max_passengers) {
        this.max_passengers = max_passengers;
    }

    public void setAmount_per_passenger(Double amount_per_passenger) {
        this.amount_per_passenger = amount_per_passenger;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setDriver_picture(String driver_picture) {
        this.driver_picture = driver_picture;
    }

    public void setRides(int rides) {
        this.rides = rides;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }

    public void setAverage_rating(double average_rating) {
        this.average_rating = average_rating;
    }

    public void setComments_about_driver(List<Ratings> comments_about_driver) {
        this.comments_about_driver = comments_about_driver;
    }
}
