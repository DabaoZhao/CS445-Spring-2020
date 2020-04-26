package org.pop.rs.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pop.rs.model.Ride;

import java.util.ArrayList;
import java.util.List;

public class RideServiceTest {
    @Mock
    List<Ride> rides;
    @InjectMocks
    RideService rideService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        List<Ride> rideList = new ArrayList<>();
        Ride ride = new Ride("1");
        rideList.add(ride);
        rideService = new RideService(rideList);
    }

    @Test
    public void testAdd()  {
        Ride result = rideService.add(new Ride("2"));
        Assert.assertEquals("2",result.getRid());
    }

    @Test
    public void testUpdate()  {
        boolean result = rideService.update("1", new Ride("2"));
        Assert.assertEquals(true, result);
    }

    @Test public void testFindById()  {
        Ride result = rideService.findById("1");
        Assert.assertEquals("1",result.getRid());
    }

    @Test
    public void testDelete()  {
        rideService.delete("1");
    }

    @Test
    public void testFindBYKeyword()  {
        List<Ride> result = rideService.findBYKeyword("from", "to", "departure_date");
        Assert.assertEquals(0,result.size());
    }
    
    @Test
    public void testByDepartureDate(){
        List<Ride> result = rideService.getByDepartureDate("from", "to");
        Assert.assertNull(result);
    }

}
