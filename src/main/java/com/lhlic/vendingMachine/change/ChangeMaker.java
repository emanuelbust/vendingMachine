package com.lhlic.vendingMachine.change;

import java.util.HashMap;
import java.util.Map;

import com.lhlic.vendingMachine.exceptions.InsufficientFundsException;

public class ChangeMaker {
	
	/**
	 * This function makes change for a payment. It is expected that change is
	 * given as dollars and cents. Change is paid in the units defined in
	 * {@link, Unit}. Change is calculated iteratively by checking to see how
	 * many times a unit divides and then making change out of the remaining
	 * amount. Hence, it is necessary for the function of the function that
	 * the enums in {@link, Unit} are listed in descending order.
	 * 
	 * @param change The amount of dollar to make change for
	 * @return A map units map to how many of themselves are necessary to
	 * 		   make change. Note: if a unit is not used, it will not be
	 * 		   included in the keyset of the map
	 */
	public static Map<Unit, Integer> makeChange(float change) {
		if(change < 0) {
			return null;
		}
		
		// Convert the payment to cents
		int paymentAsCents = (int) (Math.ceil(change * 100));
		// Map each unit to the number of units need to make change
		Map<Unit, Integer> unitCount = new HashMap<Unit, Integer>();
		
		// Calculate how much of each unit is need
		int howMany = 0;
		for(Unit unit: Unit.values()) {
			// How many of the unit fit in the payment
			howMany = paymentAsCents / unit.asCents();
			if(howMany > 0) {
				unitCount.put(unit, howMany);
			}
			// How much is left after the unit
			paymentAsCents %= unit.asCents();
		}
		
		return unitCount;
	}
}
