package com.lhlic.vendingMachine.change;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

public class ChangeMakerTest {
	private Map<Unit, Integer> change = null;
	
	@Test
	// No change
	public void noChange() {
		float payment = 0f;
		change = ChangeMaker.makeChange(payment);
		
		assertNotEquals(null, change);
		assertEquals(0, change.size());
	}
	
	@Test
	// Multiple of single unit
	public void bill() {
		// Twenties
		float payment = 40f;
		change = ChangeMaker.makeChange(payment);
		assertNotEquals(null, change);
		assertEquals(1, change.size());
		assertEquals(2, change.get(Unit.TWENTY).intValue());
	}

	@Test
	// Mixture of bills
	public void mixedBills() {
		// Twenties
		float payment = 18f;
		change = ChangeMaker.makeChange(payment);
		assertNotEquals(null, change);
		assertEquals(3, change.size());
		assertEquals(1, change.get(Unit.TEN).intValue());
		assertEquals(1, change.get(Unit.FIVE).intValue());
		assertEquals(3, change.get(Unit.ONE).intValue());	
	}
	
	@Test
	// One unit of coins
	public void coin() {
		float payment = .04f;
		change = ChangeMaker.makeChange(payment);
		assertNotEquals(null, change);
		assertEquals(1, change.size());
		assertEquals(4, change.get(Unit.PENNY).intValue());
	}
	
	@Test
	// Multiple units of coins
	public void mixedCoins() {
		float payment = .37f;
		change = ChangeMaker.makeChange(payment);
		assertNotEquals(null, change);
		assertEquals(3, change.size());
		assertEquals(1, change.get(Unit.QUARTER).intValue());
		assertEquals(1, change.get(Unit.DIME).intValue());
		assertEquals(2, change.get(Unit.PENNY).intValue());	
	}
	
	
	@Test
	// Multiple coins and bills
	public void billsAndCoins() {
		float payment = 36.42f;
		change = ChangeMaker.makeChange(payment);
		assertNotEquals(null, change);
		assertEquals(8, change.size());
		assertEquals(1, change.get(Unit.TWENTY).intValue());
		assertEquals(1, change.get(Unit.TEN).intValue());
		assertEquals(1, change.get(Unit.FIVE).intValue());
		assertEquals(1, change.get(Unit.ONE).intValue());	
		assertEquals(1, change.get(Unit.QUARTER).intValue());	
		assertEquals(1, change.get(Unit.NICKEL).intValue());	
		assertEquals(1, change.get(Unit.DIME).intValue());	
		assertEquals(2, change.get(Unit.PENNY).intValue());
	}
	
	/**
	 * Error cases
	 */
	@Test
	// Expect an exception for negative payment
	public void negative() {
		Map<Unit, Integer> change = ChangeMaker.makeChange(-1);
		assertEquals(null, change);
	}
}
