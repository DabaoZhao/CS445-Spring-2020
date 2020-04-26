package org.pop.rs.controller;

import org.pop.rs.Response.FailureResponse;
import org.pop.rs.common.Utils;
import org.pop.rs.model.*;
import org.pop.rs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/sar")
public class RideController {

	@Autowired
	RideService rideService;

	@Autowired
	private JoinRequestService joinRequestService;

	@Autowired
	private DriverService driverService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/rides", method = RequestMethod.POST)
	public ResponseEntity<?> create( @RequestBody Ride ride) {

		FailureResponse failureResponse = new FailureResponse();
		if(accountService.findById(ride.getDriverId())==null || !accountService.findById(ride.getDriverId()).isActive()){
			failureResponse.setDetail("This account ("+ride.getDriverId()+ ") is not active, may not create a ride.");
			failureResponse.setInstance("/rides");
			failureResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(failureResponse,HttpStatus.BAD_REQUEST);
		}
		if(!validateRide(ride,failureResponse)) {
			failureResponse.setInstance("/rides");
			failureResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(failureResponse,HttpStatus.BAD_REQUEST);
		}

		ride = rideService.add(ride);
		Ride newRide = new Ride(ride.getRid());

		return new ResponseEntity<>(newRide,HttpStatus.CREATED) ;
	}

	@RequestMapping(value = "/rides/{rid}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateRide(@PathVariable("rid") String rid, @RequestBody Ride ride) {

		FailureResponse failureResponse = new FailureResponse();
		if(rideService.findById(rid) == null) {
			failureResponse.setInstance("/rides/{rid}");
			return new ResponseEntity<>(failureResponse,HttpStatus.NOT_FOUND);
		}
		if(!validateRide(ride,failureResponse)) {
			failureResponse.setInstance("/rides/{rid}");
			failureResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(failureResponse,HttpStatus.BAD_REQUEST);
		}

		rideService.update(rid,ride);
		return new ResponseEntity<Ride>(HttpStatus.NO_CONTENT) ;
	}

	@RequestMapping(value = "/rides/{rid}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRide(@PathVariable("rid") String rid) {

		FailureResponse failureResponse = new FailureResponse();
		if(rideService.findById(rid) == null) {
			failureResponse.setInstance("/rides/{rid}");
			return new ResponseEntity<>(failureResponse,HttpStatus.NOT_FOUND);
		};

		rideService.delete(rid);
		return new ResponseEntity<Ride>(HttpStatus.NO_CONTENT) ;
	}

	@RequestMapping(value = "/rides/{rid}", method = RequestMethod.GET)
	public ResponseEntity<?> getRideDetail(@PathVariable("rid") String rid) {
		Ride ride = rideService.findById(rid);
		if(ride == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Driver driver = driverService.findById(ride.getDriverId(),true);
		Account account = accountService.findById(ride.getDriverId());
		if(driver==null){
			driver = new Driver();
			if(account==null){
				FailureResponse failureResponse = new FailureResponse();
				failureResponse.setInstance("/rides/{rid}");
				failureResponse.setDetail("aid not found");
				return new ResponseEntity<>(failureResponse,HttpStatus.NOT_FOUND);
			}
			driver.setAid(ride.getDriverId());
			Ratings[] ratings = new Ratings[0];
			driver.setFirst_name(account.getFirstName());
			driver.setDetail(ratings);
		}
		RideInfo rideInfo = new RideInfo();
		rideInfo.setCar_Info(ride.getCarInfo());
		rideInfo.setDate_time(ride.getDateTime());
		rideInfo.setLocation_info(ride.getLocationInfo());
		rideInfo.setRid(rid);
		rideInfo.setAmount_per_passenger(ride.getAmount_per_passenger());
		rideInfo.setMax_passengers(ride.getMax_passengers());
		if(driver.getAverage_rating()!=null) {
			rideInfo.setAverage_rating(driver.getAverage_rating());
		}
		rideInfo.setRides(driver.getRides());
		rideInfo.setRatings(driver.getRatings());
		rideInfo.setDriver(driver.getFirst_name());
		if(!StringUtils.isEmpty(account.getPicture())) {
			rideInfo.setDriver_picture(account.getPicture());
		}
		List<Ratings> arrayList = Arrays.asList(driver.getDetail());
		rideInfo.setComments_about_driver(arrayList);
		rideInfo.setConditions(ride.getConditions());

		return new ResponseEntity<>(rideInfo,HttpStatus.OK);
	}

	@RequestMapping(value = "/rides", method = RequestMethod.GET)
	public ResponseEntity<List<Ride>> getRide() {
		List<Ride> rideList = rideService.getRides();
		return new ResponseEntity<>(rideList,HttpStatus.OK) ;
	}

	@RequestMapping(value = "/rides{?from=fromkey&to=tokey&date=departure_date}", method = RequestMethod.GET)
	public ResponseEntity<?> rateRide(@QueryParam("from") String from, @QueryParam("to") String to,@QueryParam("date") String departure_date) {

		List<Ride> rides = rideService.findBYKeyword(from,to,departure_date);
		return new ResponseEntity<>(rides,HttpStatus.OK) ;
	}

	@RequestMapping(value = "/rides/{rid}/join_requests", method = RequestMethod.POST)
	public ResponseEntity<?> joinRequests( @RequestBody JoinRequest joinRequest, @PathVariable("rid") String rid) {

		FailureResponse failureResponse = new FailureResponse();
		if(!validateJoinRequests(joinRequest,failureResponse)) {
			failureResponse.setInstance("/rides");
			failureResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(failureResponse,HttpStatus.BAD_REQUEST);
		}
		Ride ride = rideService.findById(rid);

		if(accountService.findById(joinRequest.getAid())==null || !accountService.findById(joinRequest.getAid()).isActive()){
			failureResponse.setDetail("This account ("+joinRequest.getAid()+ ") is not active, may not create a join ride request.");
			failureResponse.setInstance("/rides/1/join_requests");
			failureResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(failureResponse,HttpStatus.BAD_REQUEST);
		}
		JoinRequest newJoinRequest = joinRequestService.add(joinRequest);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Location","/rides/{rid}/join_requests"+joinRequest.getJid());
		return new ResponseEntity<>(new JoinRequest(newJoinRequest.getJid()),headers,HttpStatus.CREATED) ;
	}

	@RequestMapping(value = "/rides/{rid}/join_requests/{jid}", method = RequestMethod.PATCH)
	public ResponseEntity<?> updateRequests( @RequestBody JoinRequest joinRequest, @PathVariable("rid") String rid,@PathVariable("jid") String jid) {

		FailureResponse failureResponse = new FailureResponse();
		JoinRequest updateJoinRequest = joinRequestService.findById(jid);
		if(updateJoinRequest==null){
			failureResponse.setInstance("/rides/null/join_requests/null");
			failureResponse.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<>(failureResponse,HttpStatus.NOT_FOUND);
		}
		Ride ride = rideService.findById(rid);
		if(accountService.findById(joinRequest.getAid())==null || !accountService.findById(joinRequest.getAid()).isActive()){
			failureResponse.setDetail("This account ("+rid+ ") is not active, may not create a join ride request.");
			failureResponse.setInstance("/rides/null/join_requests/null");
			failureResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(failureResponse,HttpStatus.BAD_REQUEST);
		}
		if(!StringUtils.isEmpty(joinRequest.getPickupConfirmed()) && !joinRequest.getPickupConfirmed() ) {
			failureResponse.setInstance("/rides");
			failureResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(failureResponse,HttpStatus.BAD_REQUEST);
		}
		if(!StringUtils.isEmpty(joinRequest.getRideConfirmed()) && !joinRequest.getRideConfirmed() ) {
			failureResponse.setInstance("/rides");
			failureResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(failureResponse,HttpStatus.BAD_REQUEST);
		}
		if(StringUtils.isEmpty(joinRequest.getAid()) || !joinRequest.getAid().equalsIgnoreCase(ride.getDriverId())){
			if(!ride.getRiderId().equalsIgnoreCase(joinRequest.getAid())) {
				failureResponse.setStatus(HttpStatus.BAD_REQUEST.value());
				failureResponse.setInstance("/rides/null/join_requests/null");
				failureResponse.setDetail("This account (" + joinRequest.getAid() + ") didn't create the ride (" + rid + ") nor was it a passenger");
				return new ResponseEntity<>(failureResponse, HttpStatus.BAD_REQUEST);
			}
		}
		JoinRequest updatedJoinRequest = joinRequestService.updateJoinRequests(joinRequest,jid);
		ride.setRiderId(updatedJoinRequest.getAid());
		rideService.update(ride.getRid(),ride);
		return new ResponseEntity<>(HttpStatus.OK) ;
	}

	@RequestMapping(value = "/rides/{rid}/messages", method = RequestMethod.POST)
	public ResponseEntity<?> updateMessages( @RequestBody Message message, @PathVariable("rid") String rid) {

		FailureResponse failureResponse = new FailureResponse();
		Ride ride = rideService.findById(rid);
		if(ride==null){
			failureResponse.setInstance("/rides");
			failureResponse.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<>(failureResponse,HttpStatus.NOT_FOUND);
		}
		if(StringUtils.isEmpty(message.getAid())) {
			failureResponse.setDetail("Aid is Empty");
			failureResponse.setInstance("/rides");
			failureResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(failureResponse,HttpStatus.BAD_REQUEST);
		}
		message.setRid(rid);
		message.setSent_by_aid(message.getAid());
		Message newMessage = messageService.add(message);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Location","/rides/{rid}/messages"+message.getMid());
		return new ResponseEntity<>(newMessage,headers,HttpStatus.CREATED) ;
	}

	@RequestMapping(value = "/rides/{rid}/messages", method = RequestMethod.GET)
	public ResponseEntity<?> getRideMessages(@PathVariable("rid") String rid) {
		List<Message> messages = messageService.findByRid(rid);
		return new ResponseEntity<>(messages,HttpStatus.OK);
	}


	private boolean validateJoinRequests(JoinRequest joinRequest, FailureResponse failureResponse) {
		if(StringUtils.isEmpty(joinRequest.getAid())){
			failureResponse.setDetail("Aid is empty");
			return false;
		}
		if(joinRequest.getPickupConfirmed()!=null){
			failureResponse.setDetail("PickUp Confirmed is not null");
			return false;
		}
		if(joinRequest.getRideConfirmed()!=null){
			failureResponse.setDetail("Ride Confirmed is not null");
			return false;
		}
		return true;
	}

	private boolean validateRide(Ride ride,FailureResponse failureResponse) {

		if(StringUtils.isEmpty(ride.getDriverId())){
			failureResponse.setDetail("Driver Id is Empty");
			return false;
		}
		if(StringUtils.isEmpty(ride.getLocationInfo())){
			failureResponse.setDetail("Location Info is Empty");
			return false;
		}
		LocationInfo locationInfo = ride.getLocationInfo();
		if(StringUtils.isEmpty(locationInfo.getFromCity())){
			failureResponse.setDetail("From City Info is Empty");
			return false;
		}
		if(StringUtils.isEmpty(locationInfo.getToCity())){
			failureResponse.setDetail("To City Info is Empty");
			return false;
		}
		DateTime dateTime = ride.getDateTime();
		if(dateTime == null){
			failureResponse.setDetail(" Invalid DateTime");
			return false;
		}
		if(!Utils.isValidDate(dateTime.getDate())){
			failureResponse.setDetail("Invalid Date");
			return false;
		}
		if(!Utils.isValidTime(dateTime.getTime())){
			failureResponse.setDetail("Invalid Time");
			return false;
		}



		return true;
	}
}
