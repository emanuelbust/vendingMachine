package com.lhlic.vendingMachine.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.lhlic.vendingMachine.VendingMachineApplication;
import com.lhlic.vendingMachine.exceptions.InsufficientFundsException;
import com.lhlic.vendingMachine.exceptions.ItemNotFoundException;
import com.lhlic.vendingMachine.exceptions.OutOfStockException;
import com.lhlic.vendingMachine.item.Item;

@Service
public class VendingService {
	// The items stored by the vending machine
	private static List<Item> items;
	
	// Command line logger
	private static final Logger log = LoggerFactory.getLogger(VendingMachineApplication.class);

	// Json builder
	private static final Gson gson = new Gson();
	
	static {
		log.info("Initialized vending machine items");
		items = new ArrayList<Item>();
		items.add(new Item(1L, "Banana", "A comical yellow fruit", .85f, 8));
		items.add(new Item(2L, "Hot Cheetos", "Spicy corn chunks", 1.50f, 4));
		items.add(new Item(3L, "Arizona Iced Tea", "Iced tea falvored with lemon", 1.00f, 3));
		items.add(new Item(4L, "Gum", "12 pack of pepermint gum", .50f, 0));
		items.add(new Item(5L, "Danish", "Flakey dessert with a cherry center", 1.25f, 1));
	}
	
	/**
	 * Getter for all of the items
	 * 
	 * @return A list of all of the items
	 */
	public List<Item> getAllItems(){
		return items;
	}
	
	/**
	 * Gets all of the information of an item with a given id
	 * 
	 * @param id Id of the item you want
	 * @return The item you want
	 */
	public Item getItem(long id) {
		List<Item> hits = items.stream().filter(item -> item.getId().equals(id)).collect(Collectors.toList());
		if(hits.size() == 0) {
			log.info("Item with id: " + id + " not found");
			return null;
		} else if(hits.size() > 1) {
			log.error("Multiple items with id: " + id + " found");
			return null;
		} else {
			log.info("Retrieved item with id: " + id);
			return hits.get(0);
		}
	}
	
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
	public Map<Unit, Integer> makeChange(float change) {
		if(change < 0) {
			log.info("Asked for change for a negative amount: " + change);
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
		
		log.info("Dispensing " + change + " as " + gson.toJson(unitCount));
		return unitCount;
	}
	
	/**
	 * Given an item and a payment, this function vends an item. The following
	 * is checked: the item exists, there is enough of the item in the vending
	 * machine, enough has been paid to purchase the item
	 * 
	 * @param id The id of the item to buy
	 * @param payment The payment in dollars
	 * @return A map with two fields: success and message.
	 * @throws ItemNotFoundException If an item with the id doesn't exist
	 * @throws OutOfStockException The quantity of an item is 0 or less
	 * @throws InsufficientFundsException The amount given is not enough to buy
	 * 									  the item
	 */
	public VendResponse vendItem(long id, VendRequest request) throws ItemNotFoundException, OutOfStockException, InsufficientFundsException {	
		// Look for the item
		Item item = getItem(id);
		if(item == null) {
			throw new ItemNotFoundException("Item with id: " + id + " not found");
		}
		
		// Not enough of the item 
		if(item.getQuantity() <= 0) {
			throw new OutOfStockException(item.getName() + " out of stock");
		}
		
		// Not enough money
		if(item.getCost() > request.getPayment()) {
			throw new InsufficientFundsException("Insert " + item.getFormattedCost()	);
		}
		
		// Update the quantity
		item.setQuantity(item.getQuantity() - 1);
		
		// Respond to the controller
		VendResponse response = new VendResponse(	
													true,
													"Enjoy your " + item.getName(),
													item.getQuantity(),
													makeChange(request.getPayment() - item.getCost())
												);
		return response;
	}
}
