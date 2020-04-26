package org.pop.rs.service;

import org.pop.rs.common.Utils;
import org.pop.rs.model.Message;
import org.pop.rs.model.RideMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

	@Autowired
	private AccountService accountService;

	private List<Message> messages = new ArrayList<Message>();

	private List<RideMessage> rideMessages = new ArrayList<RideMessage>();

	public MessageService() {
	}

	public MessageService(List<Message> messages) {
        this.messages = messages;
    }

	public Message add(Message message){
		String lastId = null;
		if(messages.size() > 0) lastId = messages.get(messages.size()-1).getMid();
		String newId = Utils.generateId(lastId);

		message.setMid(newId);
		message.setBody(message.getMsg());
		messages.add(message);
		return message;
	}

	public Message findById(String messageId) {
    	for(Message message:messages){
    		if(message.getMid().equalsIgnoreCase(messageId)){
    			return message;
			}
		}
		return null;
	}

	public List<Message> findByRid(String rid) {
    	List<Message> messageList = new ArrayList<>();
		for(Message message:messages){
			if(message.getRid().equalsIgnoreCase(rid)){
				messageList.add(message);
			}
		}
		return messageList;
	}



}
