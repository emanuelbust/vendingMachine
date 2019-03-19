package com.lhlic.vendingMachine.change;

import static org.junit.Assert.*;

import org.junit.Test;


public class ChangeMakerTest {
	public static final float CENT_MOE = .001f;
	
	@Test
	public void dollarConversion() {
		assert Unit.TWENTY.asDollars() == 20.00;
		assert Unit.TEN.asDollars() == 10.00;
		assert Unit.FIVE.asDollars() == 5.00;
		assert Unit.ONE.asDollars() == 1.00;
		assertEquals(Unit.QUARTER.asDollars(), .25, CENT_MOE);
		assertEquals(Unit.DIME.asDollars(), .1, CENT_MOE);
		assertEquals(Unit.NICKEL.asDollars(), .05, CENT_MOE);
		assertEquals(Unit.PENNY.asDollars(), .01, CENT_MOE);
	}
	
	
	@Test
	// Giving zero dollars should result in no change
	public void noChange() {
		float amount = 0;
		for(Unit unit: Unit.values()) {
			assert ChangeMaker.howMany(amount, unit) == 0;
		}
	}
	
	@Test
	// Check a unit will be returned as changed
	// I.e. ten dollars in ten dollar bills is 1
	public void unitAsChange() {
		for(Unit unit: Unit.values()) {
			assertEquals(1, ChangeMaker.howMany(unit.asDollars(), unit));
		}
	}
	
	@Test
	// Check multiple of a single unit
	public void oneUnitMultiple() {
		for(int i = 0; i < 100) {
			
		}
	}
	
	
	/**
	 * Error cases
	 */
	
	@Test
	// Expect an exception for negative payment
	public void negative() {}
}
