package com.lhlic.vendingMachine.controller;

/**
 * This class provides a pojo for vending request to mapped into by spring.
 */
public class VendRequest {
	// How much the customer is playing
	private float payment;

	
	/*
	 * Constructors
	 */
	public VendRequest() {}
	
	public VendRequest(float payment) {
		this.payment = payment;
	}
	
	/*
	 * Getters and setters 
	 */
	
	public float getPayment() {
		return payment;
	}

	public void setPayment(float payment) {
		this.payment = payment;
	}
}
