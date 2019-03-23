package com.lhlic.vendingMachine.controller;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
}
