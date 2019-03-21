package com.lhlic.vendingMachine.controller;

public class VendRequest {
	private float payment;

	public VendRequest() {}
	
	public VendRequest(float payment) {
		this.payment = payment;
	}
	
	public float getPayment() {
		return payment;
	}

	public void setPayment(float payment) {
		this.payment = payment;
	}
}
