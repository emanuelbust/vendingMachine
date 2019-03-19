package com.lhlic.vendingMachine.change;

public class ChangeMaker {
	public static int howMany(float amount, Unit unit) {
		// What is the amount given in cents?
		int cents = (int) (Math.ceil(amount * 100));

		
		// How many of the unit are in the given amount?
		int howManyFit = cents / unit.asCents();
		
		return howManyFit;
	}
}
