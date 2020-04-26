package org.pop.rs.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.pop.rs.model.*;
import org.pop.rs.service.*;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.mockito.Mockito.*;

public class RideControllerTest {
    @Mock RideService rideService;
    @Mock JoinRequestService joinRequestService;
    @Mock DriverService driverService;
    @Mock MessageService messageService;
    @Mock AccountService accountService;
    @InjectMocks RideController rideController;

    @Before public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test public void testInvalidCreateDriverIdEmpty()  {
        when(rideService.add(any())).thenReturn(new Ride("rid"));
        when(accountService.findById(Mockito.anyString())).thenReturn(null);
        ResponseEntity<?> result = rideController.create(new Ride("rid"));
    }

    @Test public void testInvalidCreate()  {
        Ride ride = new Ride(("1"));
        ride.setDriverId("2");
        when(rideService.add(any())).thenReturn(ride);
        when(accountService.findById(Mockito.anyString())).thenReturn(null);
        ResponseEntity<?> result = rideController.create(ride);
    }

    @Test public void testUpdateRide()  {
        when(rideService.update(anyString(), any())).thenReturn(true);
        when(rideService.findById(anyString())).thenReturn(new Ride("rid"));

        ResponseEntity<?> result = rideController.updateRide("rid", new Ride("rid"));
    }

    @Test public void testDeleteRide()  {
        when(rideService.findById(anyString())).thenReturn(new Ride("rid"));

        ResponseEntity<?> result = rideController.deleteRide("rid");
    }

    @Test public void testDeleteRideNotFound()  {
        when(rideService.findById(anyString())).thenReturn(null);

        ResponseEntity<?> result = rideController.deleteRide("rid");
    }

    @Test public void testGetRide()  {
        when(rideService.getRides()).thenReturn(Arrays.<Ride>asList(new Ride("rid")));

        ResponseEntity<?> result = rideController.getRide();

    }

    @Test public void testGetRideNotFound()  {
        when(rideService.findById(Mockito.anyString())).thenReturn(null);

        ResponseEntity<?> result = rideController.getRideDetail("rid");

    }

    @Test public void testGetRideDetail()  {
        when(rideService.findById(anyString())).thenReturn(new Ride("rid"));
        when(driverService.findById(anyString(),Mockito.anyBoolean())).thenReturn(new Driver("aid", "firstName", new Ratings[] { null }, Double.valueOf(0), 0, 0));
        when(accountService.findById(anyString())).thenReturn(new Account("aid"));
        ResponseEntity<?> result = rideController.getRideDetail("rid");

    }

    @Test public void testRateRide()  {
        when(rideService.findBYKeyword(anyString(), anyString(), anyString())).thenReturn(Arrays.<Ride>asList(new Ride("rid")));

        ResponseEntity<?> result = rideController.rateRide("from", "to", "departure_date");

    }

    @Test public void testJoinRequests()  {
        when(joinRequestService.add(any())).thenReturn(new JoinRequest("jid"));

        ResponseEntity<?> result = rideController.joinRequests(new JoinRequest("jid"), "rid");

    }

    @Test public void testValidJoinRequests()  {
        when(joinRequestService.add(any())).thenReturn(new JoinRequest("jid"));
        JoinRequest joinRequest = getJoinRequests();
        joinRequest.setPickupConfirmed(null);
        when(joinRequestService.add(any())).thenReturn(joinRequest);
        ResponseEntity<?> result = rideController.joinRequests(getJoinRequests(), "rid");

    }

    @Test public void testValidUpdateJoinRequests()  {

        Account account = new Account();
        account.setActive(true);
        account.setAid("4");
        when(accountService.findById(Mockito.anyString())).thenReturn(account);
        when(joinRequestService.add(any())).thenReturn(getJoinRequests());
        when(rideService.findById(Mockito.any())).thenReturn(getRide());
        when(joinRequestService.findById(Mockito.anyString())).thenReturn(getJoinRequests());
        when(joinRequestService.updateJoinRequests(Mockito.any(),Mockito.anyString())).thenReturn(getJoinRequests());
        ResponseEntity<?> result = rideController.updateRequests(getJoinRequests(),"1","1");

    }

    @Test public void testInvalidUpdateRequests()  {
        when(rideService.findById(anyString())).thenReturn(new Ride("rid"));
        when(joinRequestService.findById(anyString())).thenReturn(new JoinRequest("jid"));
        when(accountService.findById(Mockito.anyString())).thenReturn(null);
        ResponseEntity<?> result = rideController.updateRequests(new JoinRequest("jid","aid"), "rid", "jid");

    }

    @Test public void testInValidUpdateRequest()  {
        when(rideService.findById(anyString())).thenReturn(new Ride("rid"));
        when(joinRequestService.findById(anyString())).thenReturn(new JoinRequest("jid"));
        when(accountService.findById(Mockito.anyString())).thenReturn(null);
        ResponseEntity<?> result = rideController.updateRequests(new JoinRequest("aid",false), "rid", "jid");
    }

    @Test public void testInvalidUpdateRequests3()  {
        when(rideService.findById(anyString())).thenReturn(new Ride("rid"));
        when(joinRequestService.findById(anyString())).thenReturn(null);
        ResponseEntity<?> result = rideController.updateRequests(new JoinRequest("jid"), "rid", "jid");

    }

    @Test public void testInvalidUpdateMessages()  {
        when(rideService.findById(anyString())).thenReturn(new Ride("rid"));
        when(messageService.add(any())).thenReturn(new Message());
        ResponseEntity<?> result = rideController.updateMessages(new Message(), "rid");
    }

    @Test public void testValidUpdateMessages()  {
        Message message = new Message();
        message.setRid("rid");
        message.setAid("2");
        message.setMsg("hello");
        when(rideService.findById(anyString())).thenReturn(new Ride("rid"));
        when(messageService.add(any())).thenReturn(message);
        ResponseEntity<?> result = rideController.updateMessages(message, "rid");
    }

    @Test public void testInvalidUpdateMessagesWithRIDEmpty()  {
        when(rideService.findById(anyString())).thenReturn(null);
        ResponseEntity<?> result = rideController.updateMessages(new Message(), "rid");
    }

    @Test public void testGetRideMessages()  {
        when(messageService.findByRid(anyString())).thenReturn(Arrays.<Message>asList(new Message()));

        ResponseEntity<?> result = rideController.getRideMessages("rid");
    }

    @Test
    public void testValidCreateRide(){
        Account account = new Account();
        account.setActive(true);
        when(accountService.findById(Mockito.anyString())).thenReturn(account);
        when(rideService.add(Mockito.any())).thenReturn(getRide());
        ResponseEntity<?> result = rideController.create(getRide());
    }

    public Ride getRide(){
        Ride ride = new Ride();
        ride.setRid("4");
        ride.setDriverId("4");
        DateTime dateTime = new DateTime();
        dateTime.setTime("10:30");
        dateTime.setDate("10-Apr-2019");
        ride.setDateTime(dateTime);
        LocationInfo locationInfo = new LocationInfo();
        locationInfo.setFromCity("ab");
        locationInfo.setFromZip("123");
        locationInfo.setToZip("345");
        locationInfo.setToCity("def");
        ride.setLocationInfo(locationInfo);
        CarInfo carInfo = new CarInfo();
        carInfo.setModel("Car");
        carInfo.setColor("black");
        carInfo.setMake("2000");
        carInfo.setPlate_serial("123");
        ride.setCarInfo(carInfo);
        return ride;
    }

    public JoinRequest getJoinRequests(){
        JoinRequest joinRequest = new JoinRequest();
        joinRequest.setAid("4");
        joinRequest.setPickupConfirmed(true);
        return joinRequest;
    }
}