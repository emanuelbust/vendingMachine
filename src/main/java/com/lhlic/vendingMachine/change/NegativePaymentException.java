package com.lhlic.vendingMachine.change;

/**
 * This exception is thrown by {@link, ChangeMaker} if you ask it to make
 * change for a negative amount of money
 *
 */
public class NegativePaymentException extends Exception {
	public NegativePaymentException(String msg) {
		super(msg);
	}
}
