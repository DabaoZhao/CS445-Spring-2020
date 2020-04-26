package org.pop.rs.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.pop.rs.model.Account;
import org.pop.rs.model.Driver;
import org.pop.rs.model.Ratings;
import org.pop.rs.model.Ride;
import org.pop.rs.service.AccountService;
import org.pop.rs.service.DriverService;
import org.pop.rs.service.RideService;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.mockito.Mockito.*;

public class AccountControllerTest {

    @Mock
    AccountService accountService;
    @Mock
    RideService rideService;
    @Mock
    DriverService driverService;
    @InjectMocks
    AccountController accountController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreate()  {
        when(accountService.add(any())).thenReturn(new Account("aid", "firstName", "lastName", null, true));

        ResponseEntity<?> result = accountController.create(new Account("aid", "firstName", "lastName", "123-456-7890", true));

    }

    @Test
    public void testInvalidCreate()  {
        when(accountService.add(any())).thenReturn(new Account("aid", "firstName", "lastName", null, true));
        ResponseEntity<?> result = accountController.create(new Account("aid", "", "lastName", null, true));

    }

    @Test
    public void testUpdateStatus()  {
        when(accountService.update(anyString(), any())).thenReturn(true);
        when(accountService.findById(anyString())).thenReturn(new Account(null, "firstName", "lastName", null, true));

        ResponseEntity<?> result = accountController.updateStatus("aid", new Account(null, "firstName", "lastName", "123-456-7890", true));

    }

    @Test
    public void testInvalidUpdateStatus()  {
        when(accountService.update(anyString(), any())).thenReturn(true);
        when(accountService.findById(anyString())).thenReturn(new Account(null, "firstName", "lastName", null, true));

        ResponseEntity<?> result = accountController.updateStatus("aid", new Account(null, "firstName", "", null, true));

    }

    @Test
    public void testUpdateAccount()  {
        when(accountService.update(anyString(), any())).thenReturn(true);
        when(accountService.findById(anyString())).thenReturn(new Account(null, "firstName", "lastName", "", true));

        ResponseEntity<?> result = accountController.updateAccount("aid", new Account(null, "firstName", "lastName", "", true));

    }

    @Test
    public void testDeleteAccount()  {
        when(accountService.findById(anyString())).thenReturn(new Account("aid", "firstName", "lastName", "phone", true));

        ResponseEntity<?> result = accountController.deleteAccount("aid");

    }

    @Test
    public void testDeleteAccountNotFOund()  {
        when(accountService.findById(anyString())).thenReturn(null);

        ResponseEntity<?> result = accountController.deleteAccount("aid");

    }

    @Test
    public void testGetAccount()  {
        when(accountService.getAccounts()).thenReturn(Arrays.<Account>asList(new Account("aid", "firstName", "lastName", "phone", true)));

        ResponseEntity<?> result = accountController.getAccount("");

    }

    @Test
    public void testGetAccountDetail()  {
        when(accountService.findById(anyString())).thenReturn(new Account("aid", "firstName", "lastName", "phone", true));

        ResponseEntity<?> result = accountController.getAccountDetail("aid");
    }

    @Test
    public void testRateAccount()  {
        when(rideService.findById(anyString())).thenReturn(new Ride("rid"));

        ResponseEntity<?> result = accountController.rateAccount( new Ratings(),"aid");

    }

    @Test
    public void testGetDriverRatings()  {
        when(driverService.findById(anyString(), Mockito.anyBoolean())).thenReturn(new Driver());
        ResponseEntity<?> result = accountController.getDriverRatings("aid");
    }


    @Test
    public void testUpdateAidNotFound()  {
        when(accountService.findById(anyString())).thenReturn(null);
        ResponseEntity<?> result = accountController.updateAccount("aid", new Account(null, "firstName", "lastName", null, true));
    }

    @Test
    public void testUpdateAidInvalidData()  {
        when(accountService.update(anyString(), any())).thenReturn(true);
        when(accountService.findById(anyString())).thenReturn(new Account(null, "firstName", "lastName", null, true));

        ResponseEntity<?> result = accountController.updateAccount("aid", new Account(null, "firstName", "", null, true));
    }



    @Test
    public void testGetRiderRatings()  {
        when(driverService.findById(anyString(),Mockito.anyBoolean())).thenReturn(new Driver());

        ResponseEntity<?> result = accountController.getRiderRatings("rid");
    }
}

