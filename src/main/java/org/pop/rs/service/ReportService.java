package org.pop.rs.service;

import java.util.ArrayList;
import java.util.List;

import org.pop.rs.common.Utils;
import org.pop.rs.model.Ride;
import org.pop.rs.model.report.Report;
import org.pop.rs.model.report.ReportDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

	@Autowired
	private RideService rideService;

	List<Report> reports = new ArrayList<>();

	public ReportService() {
	}

	public ReportService(List<Report> reportList) {
		this.reports = reportList;
	}

	public void add(Report report){
		String lastId = null;
		if(reports.size() > 0) lastId = reports.get(reports.size()-1).getPid();
		String newId = Utils.generateId(lastId);

		report.setPid(newId);
		reports.add(report);
	}


	public List<Report> getReports(){
		List<Report> reportList= new ArrayList<>();
		if(reports.size()==0){
			createSampleReports();
			for(Report report: reports){
				reportList.add(new Report(report.getPid(),report.getName()));
			}
		}

		return reports;
	}

	private void createSampleReports() {
		Report report = new Report("1","Rides posted between two dates");
		Report report2 = new Report("2","Rides posted between two dates");

		reports.add(report);
		reports.add(report2);
	}

	public Report getReportWithRidesById(String from,String to,String pid){
		Report report = findById(pid);
		List<Ride> rides = new ArrayList<>();
		Report newReport = new Report(report.getPid(),report.getName());
		if(report!=null ){
			boolean isValidFromDate = Utils.isValidDate(from);
			boolean isValidToDate = Utils.isValidDate(to);
			if(isValidFromDate && isValidToDate){
				newReport.setStartDate(from);
				newReport.setEndDate(to);
				rides = rideService.getByDepartureDate(from,to);
			}
			else if(isValidFromDate){
				newReport.setStartDate(from);
				rides = rideService.getByDepartureDate(from,"");
			}
			else if(isValidToDate){
				newReport.setEndDate(to);
				rides = rideService.getByDepartureDate("",to);
			}
		}
		if(rides.size()==0) {
			rides = rideService.getRides();
		}
		ReportDetail[] reportDetails = new ReportDetail[rides.size()];
		for(int i=0;i<rides.size();i++){
			ReportDetail reportDetail = new ReportDetail();
			reportDetail.setFromZip(rides.get(i).getLocationInfo().getFromZip());
			reportDetail.setToZip(rides.get(i).getLocationInfo().getToZip());
			reportDetail.setCount(1);
			reportDetails[i] = reportDetail;
		}
		newReport.setDetail(reportDetails);
		newReport.setRides(rides.size());
		return newReport;
	}
	public Report findById(String pid){
		for(Report report:reports){
			if(report.getPid().equalsIgnoreCase(pid)){
				return report;
			}
		}
		return null;
	}
}
