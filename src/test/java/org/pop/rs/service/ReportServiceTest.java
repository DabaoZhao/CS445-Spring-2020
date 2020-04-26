package org.pop.rs.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pop.rs.model.Ride;
import org.pop.rs.model.report.Report;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class ReportServiceTest {
    @Mock
    RideService rideService;
    @Mock
    List<Report> reports;
    @InjectMocks
    ReportService reportService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        List<Report> reportList = new ArrayList<>();
        reportService = new ReportService(reportList);
    }

    @Test
    public void testAdd()  {
        reportService.add(new Report("1", "name"));
    }

    @Test
    public void testGetReports()  {
        List<Report> result = reportService.getReports();
        Assert.assertEquals(2, result.size());
    }

    @Test(expected = NullPointerException.class)
    public void testGetReportWithRidesById()  {
        when(rideService.getByDepartureDate(anyString(), anyString())).thenReturn(Arrays.<Ride>asList(new Ride("1")));
        when(rideService.getRides()).thenReturn(null);
        Report result = reportService.getReportWithRidesById("from", "to", "1");
        Assert.assertNull(result);
    }

    @Test
    public void testFindById()  {

        Report result = reportService.findById("2");
        Assert.assertNull(result);
    }

    @Test
    public void testReports(){
        List<Report> reports = reportService.getReports();

        Assert.assertNotNull(reports);
    }
}
