package org.pop.rs.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sar")
public class SearchController {

@RequestMapping(value = "/search?key=keyword{&start_date=DD-MMM-YYYY&end_date=DD-MMM-YYYY}", method = RequestMethod.GET)
	public ResponseEntity<?> getReports(String topic,String key) {

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
