package org.pop.rs.controller;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

public class SearchControllerTest {
    SearchController searchController = new SearchController();

    @Test public void testGetReports() throws Exception {
        ResponseEntity<?> result = searchController.getReports("topic", "key");
        Assert.assertNotNull(result);
    }
}
