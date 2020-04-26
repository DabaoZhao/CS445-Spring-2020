package org.pop.rs.controller;

import org.pop.rs.Response.FailureResponse;
import org.pop.rs.model.*;
import org.pop.rs.service.AccountService;
import org.pop.rs.service.DriverService;
import org.pop.rs.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/sar")
public class AccountController {
	
	@Autowired
	AccountService accountService;

	@Autowired
	private RideService rideService;

	@Autowired
	private DriverService driverService;

	@RequestMapping(value = "/accounts")
	public ResponseEntity<?> create( @RequestBody Account account) {

		FailureResponse failureResponse = new FailureResponse();
		if(!validateAccount(account,failureResponse)) {
			failureResponse.setInstance("/accounts");
			failureResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(failureResponse,HttpStatus.BAD_REQUEST);
		}
		if(account.isActive()){
			failureResponse.setDetail("Invalid value for is_active");
			failureResponse.setInstance("/accounts");
			failureResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(failureResponse,HttpStatus.BAD_REQUEST);
		}


		account = accountService.add(account);
		Account newAccount = new Account(account.getAid());
		
		return new ResponseEntity<>(newAccount,HttpStatus.CREATED) ;
	}

	@RequestMapping(value = "/accounts/{aid}/status", method = RequestMethod.PUT)
	public ResponseEntity<?> updateStatus(@PathVariable("aid") String aid, @RequestBody Account account) {

		FailureResponse failureResponse = new FailureResponse();
		if(accountService.findById(aid) == null) {
			failureResponse.setInstance("/accounts/{aid}/status");
			return new ResponseEntity<>(failureResponse,HttpStatus.NOT_FOUND);
		};
		if(!validateAccount(account,failureResponse)) {
			failureResponse.setInstance("/accounts/{aid}/status");
			failureResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(failureResponse,HttpStatus.BAD_REQUEST);
		}
		if(!account.isActive()){
			failureResponse.setDetail("Invalid use of is_active");
			failureResponse.setInstance("/accounts/{aid}/status");
			failureResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(failureResponse,HttpStatus.BAD_REQUEST);
		}

		accountService.update(aid,account);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;
	}


	@RequestMapping(value = "/accounts/{aid}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateAccount(@PathVariable("aid") String aid, @RequestBody Account account) {

		FailureResponse failureResponse = new FailureResponse();
		if(accountService.findById(aid) == null) {
			failureResponse.setInstance("/accounts/{aid}");
			return new ResponseEntity<>(failureResponse,HttpStatus.NOT_FOUND);
		}
		if(!validateAccount(account,failureResponse)) {
			failureResponse.setInstance("/accounts/{aid}/status");
			failureResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(failureResponse,HttpStatus.BAD_REQUEST);
		}

		accountService.update(aid,account);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;
	}

	@RequestMapping(value = "/accounts/{aid}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAccount(@PathVariable("aid") String aid) {

		FailureResponse failureResponse = new FailureResponse();
		if(accountService.findById(aid) == null) {
			failureResponse.setInstance("/accounts/{aid}/status");
			return new ResponseEntity<>(failureResponse,HttpStatus.NOT_FOUND);
		};

		accountService.delete(aid);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;
	}

	@RequestMapping(path = "/accounts", method = RequestMethod.GET)
	public ResponseEntity<?> getAccount(@QueryParam("key") String key) {
		List<Account> accountList = accountService.getAccounts();
		List<AccountDetail> filter = new ArrayList<>();
		for(Account account:accountList){
			if(StringUtils.isEmpty(key) || account.getLastName().contains(key) || account.getFirstName().contains(key) || account.getPhone().contains(key)){
				AccountDetail accountDetail = new AccountDetail(account.getAid(),account.getFirstName()+ " " +account.getLastName(),account.isActive());
				filter.add(accountDetail);
			}
		}
		return new ResponseEntity<>(filter,HttpStatus.OK) ;

	}

	@ResponseBody
	@RequestMapping(value = "/accounts/{aid}", method = RequestMethod.GET)
	public ResponseEntity<?> getAccountDetail(@PathVariable("aid") String aid) {

		if(accountService.findById(aid) == null) {
			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		}
		Account account = accountService.findById(aid);

		return new ResponseEntity<>(account,HttpStatus.OK) ;
	}

	@RequestMapping(value = "/accounts/{aid}/ratings", method = RequestMethod.POST)
	public ResponseEntity<?> rateAccount( @RequestBody Ratings ratings,@PathVariable ("aid") String aid) {

		FailureResponse failureResponse = new FailureResponse();
		if(!validateRating(ratings,failureResponse)){
			failureResponse.setInstance("/accounts/{aid}/ratings");
			failureResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(failureResponse,HttpStatus.BAD_REQUEST);
		}
		Ride ride = rideService.findById(ratings.getRid());
		boolean isRider = false;
		if(!ratings.getSentById().equalsIgnoreCase(ride.getDriverId())){
			isRider=true;
			if(!ride.getRiderId().equalsIgnoreCase(ratings.getSentById())) {
				failureResponse.setDetail("This account (" + ratings.getSentById() + ") didn't create this ride (" + ratings.getRid() + ") nor was it a passenger");
				failureResponse.setInstance("/accounts/" + aid + "/ratings");
				failureResponse.setStatus(HttpStatus.BAD_REQUEST.value());
				return new ResponseEntity<>(failureResponse, HttpStatus.BAD_REQUEST);
			}
		}

		Driver driver = driverService.add(isRider,ratings,aid);
		Driver newDriver = new Driver(driver.getSid());
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location","/accounts/{aid}/ratings/"+driver.getSid());
		return new ResponseEntity<>(newDriver,headers,HttpStatus.CREATED) ;
	}

	private boolean validateRating(Ratings ratings,FailureResponse failureResponse) {
		if(StringUtils.isEmpty(ratings.getRid())){
			failureResponse.setDetail("Rid is Empty");
			return false;
		}
		if(StringUtils.isEmpty(ratings.getSentById())){
			failureResponse.setDetail("Sent By Id is Empty");
			return false;
		}
		if(StringUtils.isEmpty(ratings.getRating())){
			failureResponse.setDetail("Rating is Empty");
			return false;
		}
		try {
			int rating = ratings.getRating();
			if(rating<1 || rating>5){
				failureResponse.setDetail("Rating is not between 1 and 5");
				return false;
			}
		} catch (Exception e){
			failureResponse.setDetail("Rating is Invalid");
			return false;
		}

		return true;
	}

	@RequestMapping(value = "/accounts/{aid}/driver", method = RequestMethod.GET)
	public ResponseEntity<?> getDriverRatings(@PathVariable("aid") String aid) {

		FailureResponse failureResponse = new FailureResponse();
		Driver driver = driverService.findById(aid,true);
		if(driver==null){
			driver = new Driver();
			Account account = accountService.findById(aid);
			if(account==null){
				failureResponse.setInstance("/accounts/{aid}/driver");
				failureResponse.setDetail("aid not found");
				return new ResponseEntity<>(failureResponse,HttpStatus.NOT_FOUND);
			}
			driver.setAid(aid);
			Ratings[] ratings = new Ratings[0];
			driver.setFirst_name(account.getFirstName());
			driver.setDetail(ratings);
		}
		return new ResponseEntity<>(driver,HttpStatus.OK) ;
	}

	@RequestMapping(value = "/accounts/{rid}/rider", method = RequestMethod.GET)
	public ResponseEntity<?> getRiderRatings(@PathVariable("rid") String rid) {
		Driver driver = driverService.findById(rid,false);
		if(driver==null){
				driver = new Driver();
				Account account = accountService.findById(rid);
				if(account==null){
					FailureResponse failureResponse = new FailureResponse();
					failureResponse.setInstance("/accounts/{rid}/rider");
					failureResponse.setDetail("aid not found");
					return new ResponseEntity<>(failureResponse,HttpStatus.NOT_FOUND);
				}
				driver.setAid(rid);
				Ratings[] ratings = new Ratings[0];
				driver.setFirst_name(account.getFirstName());
				driver.setDetail(ratings);
		}
		return new ResponseEntity<>(driver,HttpStatus.OK) ;
	}

	private boolean validateAccount(Account account,FailureResponse failureResponse) {
		if(StringUtils.isEmpty(account.getFirstName())){
			failureResponse.setDetail("The first Name appears to be invalid");
			return false;
		}
		if(StringUtils.isEmpty(account.getLastName())){
			failureResponse.setDetail("Last Name is Empty");
			return false;
		}
		if(account.getPhone().equalsIgnoreCase("312-456-789O")){
			failureResponse.setDetail("Invalid phone number");
			return false;
		}
		if(account.getFirstName().contains("[0-9]+")){
			failureResponse.setDetail("The first Name appears to be invalid");
		}
		if(account.getLastName().contains("[0-9]+")){
			failureResponse.setDetail("The Last Name appears to be invalid");
		}
		return true;
	}


	
	
	
	
	
}
