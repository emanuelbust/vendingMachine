package com.lhlic.vendingMachine.controller;

import java.util.Map;

import com.lhlic.vendingMachine.change.Unit;

public class VendResponse {
	private Boolean success;
	private String message;
	private Integer remaining;
	private Map<Unit, Integer> change;
	
	public VendResponse() {}
	
	public VendResponse(Boolean success, String message, Integer remaining, Map<Unit, Integer> change) {
		this.success = success;
		this.message = message;
		this.remaining = remaining;
		this.change = change;
	}

	public Boolean isSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getRemaining() {
		return remaining;
	}

	public void setRemaining(Integer remaining) {
		this.remaining = remaining;
	}

	public Map<Unit, Integer> getChange() {
		return change;
	}

	public void setChange(Map<Unit, Integer> change) {
		this.change = change;
	}
}
