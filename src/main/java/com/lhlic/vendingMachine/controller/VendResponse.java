package com.lhlic.vendingMachine.controller;

import java.util.Map;

/**
 * This class exists to standardize the response from the server for a post
 * request to vend an item
 */
public class VendResponse {
	// Whether or not the vend was successful
	private Boolean success;
	// The message for the machine to display to the user
	private String message;
	// The quantity of the item left after the successful vend
	private Integer remaining;
	// The change to be given back to the user
	private Map<Unit, Integer> change;
	
	
	/*
	 * Constructors
	 */
	public VendResponse() {}
	
	public VendResponse(Boolean success, String message, Integer remaining, Map<Unit, Integer> change) {
		this.success = success;
		this.message = message;
		this.remaining = remaining;
		this.change = change;
	}

	/*
	 * Getters and setters 
	 */
	
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
