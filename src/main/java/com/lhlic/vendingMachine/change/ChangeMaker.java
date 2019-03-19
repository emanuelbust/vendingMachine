package com.lhlic.vendingMachine.change;

import java.util.HashMap;
import java.util.Map;

public class ChangeMaker {	
	public static Map<Unit, Integer> makeChange(float payment){
		// Convert the payment to cents
		int paymentAsCents = (int) (Math.ceil(payment * 100));
		// Map each unit to the number of units need to make change
		Map<Unit, Integer> change = new HashMap<Unit, Integer>();
		
		// Calculate how much of each unit is need
		int howMany = 0;
		for(Unit unit: Unit.values()) {
			// How many of the unit fit in the payment
			howMany = paymentAsCents / unit.asCents();
			change.put(unit, howMany);
			
			// How much is left after the unit
			paymentAsCents %= unit.asCents();
		}
		
		return change;
	}
}
