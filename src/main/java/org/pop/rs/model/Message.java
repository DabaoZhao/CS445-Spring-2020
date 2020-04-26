package org.pop.rs.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {

	private String mid;

	private String sent_by_aid;

	private String rid;

	private String body;

	@JsonProperty("aid")
	private String aid;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@JsonProperty("msg")
	private String msg;

	public Message() {
	}

	public Message(String mid, String aid, String msg) {
		this.mid = mid;
		this.sent_by_aid = aid;
		this.msg = msg;
	}

	public Message(String mid) {
		this.mid = mid;
	}

	public String getSent_by_aid() {
		return sent_by_aid;
	}

	public void setSent_by_aid(String sent_by_aid) {
		this.sent_by_aid = sent_by_aid;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String rid) {
		this.aid = rid;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
