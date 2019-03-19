package com.lhlic.vendingMachine.change;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.Random;

import org.junit.Test;


public class ChangeMakerTest {
	@Test
	// Check for mixture of change
	public void mixed() {
		float payment = 7.00f;
		Map<Unit, Integer> change = ChangeMaker.makeChange(payment);
		for(Unit unit: change.keySet()) {
			System.out.println(unit + " -> " + change.get(unit));
		}
	}
	
	/**
	 * Error cases
	 */
	
	@Test
	// Expect an exception for negative payment
	public void negative() {}
}
