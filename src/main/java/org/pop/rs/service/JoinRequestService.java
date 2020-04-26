package org.pop.rs.service;

import org.pop.rs.common.Utils;
import org.pop.rs.model.JoinRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JoinRequestService {

	@Autowired
	private AccountService accountService;

	private List<JoinRequest> joinRequests = new ArrayList<JoinRequest>();

    public JoinRequestService(List<JoinRequest> joinRequests) {
        this.joinRequests = joinRequests;
    }

	public JoinRequestService() {
	}

	public JoinRequest updateJoinRequests(JoinRequest newJoinRequest, String joinRequestId) {
		JoinRequest joinRequest = findById(joinRequestId);
		if (joinRequest != null) {
			if(joinRequest.getPickupConfirmed()!=null&&joinRequest.getPickupConfirmed()){
			joinRequest.setPickupConfirmed(newJoinRequest.getPickupConfirmed());
			}
			if(joinRequest.getRideConfirmed()!=null&&joinRequest.getRideConfirmed()) {
				joinRequest.setRideConfirmed(newJoinRequest.getRideConfirmed());
			}
		}
		return joinRequest;
	}

	public JoinRequest add(JoinRequest joinRequest){
		String lastId = null;
		if(joinRequests.size() > 0) lastId = joinRequests.get(joinRequests.size()-1).getJid();
		String newId = Utils.generateId(lastId);

		joinRequest.setJid(newId);
		joinRequests.add(joinRequest);
		return joinRequest;
	}

	public JoinRequest findById(String joinRequestId) {
    	for(JoinRequest joinRequest:joinRequests){
    		if(joinRequest.getJid().equalsIgnoreCase(joinRequestId)){
    			return joinRequest;
			}
		}
		return null;
	}


}
