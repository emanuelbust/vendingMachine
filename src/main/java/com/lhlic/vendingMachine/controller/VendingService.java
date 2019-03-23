package com.lhlic.vendingMachine.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lhlic.vendingMachine.change.ChangeMaker;
import com.lhlic.vendingMachine.exceptions.InsufficientFundsException;
import com.lhlic.vendingMachine.exceptions.ItemNotFoundException;
import com.lhlic.vendingMachine.exceptions.OutOfStockException;
import com.lhlic.vendingMachine.item.Item;

@Service
public class VendingService {
	private static List<Item> items;
	
	static {
		items = new ArrayList<Item>();
		items.add(new Item(1L, "Banana", "The funniest fruit", .85f, 8));
		items.add(new Item(2L, "Hot Cheetos", "The best", 1.50f, 4));
		items.add(new Item(3L, "Arizona Iced Tea", "Have to get this with Hot Cheetos", 1.00f, 3));
		items.add(new Item(4L, "Gum", "Stay fresh with mint gum", .50f, 0));
		items.add(new Item(5L, "Danish", "This danish is pure protein", 1.25f, 1));
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
		if(hits.size() != 1) {
			return null;
		} else {
			return hits.get(0);
		}
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
			throw new InsufficientFundsException(request.getPayment() + " given but " + item.getCost() + " 	required");
		}
		
		// Update the quantity
		item.setQuantity(item.getQuantity() - 1);
		
		// Respond to the controller
		VendResponse response = new VendResponse(	
													true,
													"Enjoy your " + item.getName(),
													item.getQuantity(),
													ChangeMaker.makeChange(request.getPayment() - item.getCost())
												);
		return response;
	}
}
