package org.pop.rs.controller;

import java.util.List;

import org.pop.rs.model.report.Report;
import org.pop.rs.service.AccountService;
import org.pop.rs.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;

@RestController
@RequestMapping(value = "/sar")
public class ReportController {

	@Autowired
	private ReportService reportService;
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = "/reports/{pid}{?start_date=DD-MMM-YYYY&end_date=DD-MMM-YYYY}", method = RequestMethod.GET)
	public ResponseEntity<?> getReportsByStartAndEnd(@PathVariable("pid") String pid,@QueryParam("start_date") String start_date, @QueryParam("end_date") String end_date) {

		Report report = reportService.findById(pid);
		if(report==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Report reportData = reportService.getReportWithRidesById(start_date,end_date,pid);
		if(!StringUtils.isEmpty(start_date)) {
			if (start_date.equalsIgnoreCase(end_date)) {
				reportData.setName("Rides taken between two dates");
			}
		}
		return new ResponseEntity<>(reportData,HttpStatus.OK);
	}

	@RequestMapping(value = "/reports", method = RequestMethod.GET)
	public ResponseEntity<?> getReports() {
		List<Report> reportData = reportService.getReports();
		return new ResponseEntity<>(reportData,HttpStatus.OK);
	}
	
}
