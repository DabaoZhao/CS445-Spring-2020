package org.pop.rs.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pop.rs.model.report.Report;
import org.pop.rs.service.AccountService;
import org.pop.rs.service.ReportService;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.mockito.Mockito.*;

public class ReportControllerTest {
    @Mock
    ReportService reportService;
    @Mock
    AccountService accountService;
    @InjectMocks
    ReportController reportController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetReportsByStartAndEnd()  {
        when(reportService.getReportWithRidesById(anyString(), anyString(), anyString())).thenReturn(new Report("pid", "name"));
        when(reportService.findById(anyString())).thenReturn(new Report("pid", "name"));

        ResponseEntity<?> result = reportController.getReportsByStartAndEnd("pid", "start_date", "end_date");
    }

    @Test
    public void testGetReports()  {
        when(reportService.getReports()).thenReturn(Arrays.<Report>asList(new Report("pid", "name")));
        ResponseEntity<?> result = reportController.getReports();

    }
}
