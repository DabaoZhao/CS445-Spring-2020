package org.pop.rs.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pop.rs.model.Driver;
import org.pop.rs.model.Ratings;

import java.util.ArrayList;
import java.util.List;

public class DriverServiceTest {
    @Mock
    AccountService accountService;
    @Mock
    List<Driver> drivers;
    @InjectMocks
    DriverService driverService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        List<Driver> driverList = new ArrayList<>();
        Ratings ratings = new Ratings();
        ratings.setComment("Good");
        ratings.setRating(3);
        ratings.setRid("2");
        ratings.setSentById("4");
        Ratings[] detail = new Ratings[1];
        detail[0]=ratings;
        Driver driver = new Driver("1","firstName",detail, (double) ratings.getRating(),1,1);
        driverList.add(driver);
        driverService = new DriverService(driverList);
    }

    @Test
    public void testUpdateRatings()  {
        driverService.updateRatings(new Ratings(), "1");
    }

    @Test
    public void testFindById()  {
        Driver result = driverService.findById("1",false);
        Assert.assertEquals("1", result.getAid());
    }
}
