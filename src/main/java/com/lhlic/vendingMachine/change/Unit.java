package com.lhlic.vendingMachine.change;

public enum Unit {
	// Units of change given by spec
	TWENTY, TEN, FIVE, ONE, QUARTER, DIME, NICKEL, PENNY;
	
	// The value of a unit in cents
	private int cents;

	// Setting cent values
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

	/**
	 *  Getter for the units value in cents
	 */
	public int asCents() {
		return cents;
	}
}
