package com.example.systemevents;

import java.util.Date;

public class gRPC {
	
	enum AnswerType {SUCCESFULL, ERROR }
	
	enum ActionType {
		GET,
		UPDATE,
		CREATE,
		DELETE
	};
	
	private Date timeAction;
	private String microservice;
	private String user;
	private ActionType action;
	private String resource;
	private AnswerType response;
	
	
	public gRPC(Date timeAction, String microservice, String user, ActionType action, String resource,
				AnswerType response) {
		super();
		this.timeAction = timeAction;
		this.microservice = microservice;
		this.user = user;
		this.action = action;
		this.resource = resource;
		this.response = response;
	}
	
	
	public Date getTimestampAkcije() {
		return timeAction;
	}
	public void setTimestampAkcije(Date timeAction) {
		this.timeAction = timeAction;
	}
	
	
	public String getMikroservis() {
		return microservice;
	}
	public void setMikroservis(String mikroservice) {
		this.microservice = mikroservice;
	}
	
	
	public String getKorisnik() {
		return user;
	}
	public void setKorisnik(String user) {
		this.user = user;
	}
	
	
	public ActionType getAkcija() {
		return action;
	}
	public void setAkcija(ActionType action) {
		this.action = action;
	}
	
	
	public String getResurs() {
		return resource;
	}
	public void setResurs(String resource) {
		this.resource = resource;
	}
	
	
	public AnswerType getOdgovor() {
		return response;
	}
	public void setOdgovor(AnswerType response) {
		this.response = response;
	}
	
	@Override
	public String toString() {
		return "gRPC(" +
				", timeAction" + timeAction.toString() +
				", action" + action +
				", microservice" + microservice +
				", resource" + resource +
				")";
	}
	
}
