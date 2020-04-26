package org.pop.rs.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pop.rs.model.JoinRequest;

import java.util.ArrayList;
import java.util.List;

public class JoinRequestServiceTest {
    @Mock AccountService accountService;
    @Mock List<JoinRequest> joinRequests;
    @InjectMocks JoinRequestService joinRequestService;

    @Before public void setUp() {
        MockitoAnnotations.initMocks(this);
        JoinRequest joinRequest = new JoinRequest("1");
        joinRequest.setAid("1");
        joinRequest.setPassengers(2);
        List<JoinRequest> joinRequestList = new ArrayList<>();
        joinRequestList.add(joinRequest);
        joinRequestService = new JoinRequestService(joinRequestList);

    }

    @Test public void testUpdateJoinRequests()  {
        joinRequestService.updateJoinRequests(new JoinRequest("1"), "2");
    }

    @Test public void testAdd()  {
        JoinRequest result = joinRequestService.add(new JoinRequest("1"));
        Assert.assertNotNull(result);
    }

    @Test public void testFindById()  {
        JoinRequest result = joinRequestService.findById("1");
        Assert.assertNotNull(result);
    }
}
