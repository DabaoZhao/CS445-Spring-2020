package org.pop.rs.service;

import org.pop.rs.common.Utils;
import org.pop.rs.model.Ride;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class RideService {

	private List<Ride> rides = new ArrayList<Ride>();

	public RideService() {
	}

	public RideService(List<Ride> rideList) {
        this.rides=rideList;
    }

    public Ride add(Ride ride) {
		String lastId = null;
		if(rides.size() > 0) {
			lastId = rides.get(rides.size()-1).getRid();
		}
		String newId = Utils.generateId(lastId);
		
		ride.setRid(newId);
		rides.add(ride);
		return ride;
	}
	
	public boolean update(String rid,Ride newRide) {
		for (Ride ride : rides) {
			if(ride.getRid().equals(rid)) {
				ride.setDriverId(newRide.getDriverId());
				ride.setCarInfo(newRide.getCarInfo());
				ride.setDateTime(newRide.getDateTime());
				ride.setLocationInfo(newRide.getLocationInfo());
				return true;
			}
		}
		return false;
	}
	
	public Ride findById(String rid) {
		for (Ride ride : rides) {
			if(ride.getRid().equals(rid)) return ride;
		}
		return null;
	}
	public void delete(String rid){
		Ride ride = findById(rid);
		rides.remove(ride);
	}

	/**
	 * @return the rides
	 */
	public List<Ride> getRides() {
		return rides;
	}

	public List<Ride> findBYKeyword(String from, String to, String departure_date) {
		List<Ride> rideList = new ArrayList<>();
		for(Ride ride:rides){
			if(ride.getLocationInfo()!=null) {
				if (ride.getLocationInfo().getFromCity().equalsIgnoreCase(from)) {
					if (StringUtils.isEmpty(to) || ride.getLocationInfo().getToCity().equalsIgnoreCase(to)) {
						if (StringUtils.isEmpty(departure_date) || ride.getDateTime().getDate().equalsIgnoreCase(departure_date)) {
							rideList.add(ride);
						}
					}
				}
			}
		}
		return rideList;
	}

	public List<Ride> getByDepartureDate(String fromDate, String afterDate){
		List<Ride> rideList = new ArrayList<>();
		for(Ride ride:rides){
			if(ride.getDateTime()==null){
				return null;
			}
			boolean isFromDate=false,isAfterDate=false;

			String departureDate = ride.getDateTime().getDate();
			if(!StringUtils.isEmpty(fromDate)){
				isFromDate=true;
			}
			if(!StringUtils.isEmpty(afterDate)){
				isAfterDate=true;
			}
			if(isFromDate && isAfterDate){
				if(departureDate.compareTo(fromDate)>=0 && departureDate.compareTo(afterDate)<=0){
					rideList.add(ride);
				}
			}
			else if(isFromDate) {
					if (departureDate.compareTo(fromDate) >= 0) {
						rideList.add(ride);
					}
				}
			else if (isAfterDate){
				if(departureDate.compareTo(fromDate)<=0){
					rideList.add(ride);
				}
			}

		}
		return rideList;
	}


}
