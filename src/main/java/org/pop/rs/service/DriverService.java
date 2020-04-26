package org.pop.rs.service;

import org.pop.rs.common.Utils;
import org.pop.rs.model.Account;
import org.pop.rs.model.Driver;
import org.pop.rs.model.Ratings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverService {

	@Autowired
	private AccountService accountService;

	public DriverService() {
	}

	private List<Driver> drivers = new ArrayList<Driver>();

    public DriverService(List<Driver> drivers) {
        this.drivers = drivers;
    }




	public void updateRatings(Ratings ratings, String driverId) {
		Driver driver = findById(driverId,true);
		if (driver != null) {
			driver.setRides(driver.getRides() + 1);
			driver.setAverage_rating((driver.getAverage_rating() * driver.getRatings() + ratings.getRating()) / (driver.getRatings() + 1));
			driver.setRatings(driver.getRatings() + 1);
			Ratings[] details = driver.getDetail();
			int size = details.length + 1;
			Ratings[] newDetails = new Ratings[size];
			if (size - 2 >= 0)
				System.arraycopy(details, 0, newDetails, 0, size - 2);
			newDetails[size - 1] = ratings;
			driver.setDetail(newDetails);
		}
	}

	public Driver add(boolean isRider,Ratings ratings,String driverId){
		String lastId = null;
		if(drivers.size() > 0) lastId = drivers.get(drivers.size()-1).getSid();
		String newId = Utils.generateId(lastId);
		Account account = accountService.findById(driverId);

    	Driver driver = new Driver();
    	driver.setAid(driverId);
    	driver.setRider(isRider);
		Ratings[] detail = new Ratings[1];
		ratings.setFirstName(accountService.findById(ratings.getSentById()).getFirstName());
		detail[0]=ratings;
    	driver.setDetail(detail);
    	driver.setAverage_rating((double) ratings.getRating());
    	driver.setRides(1);
    	driver.setRatings(1);
    	driver.setSid(newId);
    	driver.setFirst_name(account.getFirstName());
    	drivers.add(driver);
    	return driver;
	}

	public Driver findById(String driverId,boolean isRider) {
    	for(Driver driver:drivers){
    		if(driver.getAid().equalsIgnoreCase(driverId) && driver.isRider()==isRider){
    			return driver;
			}
		}
		return null;
	}



}
