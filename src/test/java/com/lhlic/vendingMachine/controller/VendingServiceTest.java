package com.lhlic.vendingMachine.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.lhlic.vendingMachine.exceptions.InsufficientFundsException;
import com.lhlic.vendingMachine.exceptions.ItemNotFoundException;
import com.lhlic.vendingMachine.exceptions.OutOfStockException;

@RunWith(SpringRunner.class)
public class VendingServiceTest {
	private final long EXISTING_ITEM = 1;
	private final long NON_EXISTANT_ITEM = -1;
	private final long OUT_OF_STOCK_ITEM = 4;
	private Map<Unit, Integer> change = null;

	private VendingService vendingService = new VendingService();
	
	@Test
	public void init() {
		// Check that there's items
		assertNotEquals(null, vendingService.getAllItems());
		
		// Check there's at least five items
		assertTrue(vendingService.getAllItems().size() >= 5);
	}

	@Test
	public void insufficientFundsException() {
		boolean exception = false;
		try {
			vendingService.vendItem(EXISTING_ITEM, new VendRequest(0f));
		} catch (ItemNotFoundException| OutOfStockException e) {
			fail();
		} catch (InsufficientFundsException e) {
			exception = true;
		}
		
		assertTrue(exception);
	}
	
	@Test
	public void itemNotFoundException() {
		boolean exception = false;
		try {
			vendingService.vendItem(NON_EXISTANT_ITEM , new VendRequest(2f));
		} catch (OutOfStockException | InsufficientFundsException e) {
			fail();
		} catch (ItemNotFoundException e) {
			exception = true;
		}
		
		assertTrue(exception);
	}
	
	@Test
	public void outOfStockException() {
		boolean exception = false;
		try {
			vendingService.vendItem(OUT_OF_STOCK_ITEM , new VendRequest(0f));
		} catch (ItemNotFoundException | InsufficientFundsException e) {
			fail();
		} catch (OutOfStockException e) {
			exception = true;
		}
		
		assertTrue(exception);
	}
	
	@Test
	public void successfulVend() {
		try {
			vendingService.vendItem(EXISTING_ITEM , new VendRequest(vendingService.getItem(EXISTING_ITEM).getCost().floatValue()));
		} catch (ItemNotFoundException | OutOfStockException | InsufficientFundsException e) {
			fail();
		}
	}
	
	@Test
	// No change
	public void noChange() {
		float payment = 0f;
		change = vendingService.makeChange(payment);
		
		assertNotEquals(null, change);
		assertEquals(0, change.size());
	}
	
	@Test
	// Multiple of single unit
	public void bill() {
		// Twenties
		float payment = 40f;
		change = vendingService.makeChange(payment);
		assertNotEquals(null, change);
		assertEquals(1, change.size());
		assertEquals(2, change.get(Unit.TWENTY).intValue());
	}

	@Test
	// Mixture of bills
	public void mixedBills() {
		// Twenties
		float payment = 18f;
		change = vendingService.makeChange(payment);
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
		change = vendingService.makeChange(payment);
		assertNotEquals(null, change);
		assertEquals(1, change.size());
		assertEquals(4, change.get(Unit.PENNY).intValue());
	}
	
	@Test
	// Multiple units of coins
	public void mixedCoins() {
		float payment = .37f;
		change = vendingService.makeChange(payment);
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
		change = vendingService.makeChange(payment);
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
		Map<Unit, Integer> change = vendingService.makeChange(-1);
		assertEquals(null, change);
	}
}
