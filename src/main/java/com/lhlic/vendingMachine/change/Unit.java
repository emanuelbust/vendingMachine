package com.lhlic.vendingMachine.change;

public enum Unit {
	TWENTY, TEN, FIVE, ONE, QUARTER, DIME, NICKEL, PENNY;
	private int cents;

	static {
		TWENTY.cents = 100 * 20;
		TEN.cents = 100 * 10;
		FIVE.cents = 100 * 5;
		ONE.cents = 100 * 1;
		QUARTER.cents = 25;
		DIME.cents = 10;
		NICKEL.cents = 5;
		PENNY.cents = 1;
	}	

	public int asCents() {
		return cents;
	}
	
	public float asDollars() {
		return (float) (cents / 100.0);
	}
}
