package org.pop.rs.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RideMessage {

	private Ride ride;

	private Message[] messages;

}
