package com.lhlic.vendingMachine.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lhlic.vendingMachine.VendRequest;
import com.lhlic.vendingMachine.VendResponse;
import com.lhlic.vendingMachine.VendingMachineApplication;
import com.lhlic.vendingMachine.change.ChangeMaker;
import com.lhlic.vendingMachine.change.NegativePaymentException;
import com.lhlic.vendingMachine.change.Unit;
import com.lhlic.vendingMachine.item.Item;
import com.lhlic.vendingMachine.item.ItemRepository;


/**
 * TODO DO this
 */
@RestController
@RequestMapping("item")
public class Controller {
	@Autowired
	private ItemRepository itemRepo;
	
	/**
	 * Gets all of the information of an item with a given id
	 * 
	 * @param id Id of the item you want
	 * @return The item you want
	 */
	@GetMapping("/{id}")
	public Item getItem(@PathVariable long id) {
		Item hit = itemRepo.findById(id).orElse(null);
		return hit;
	}
	
	/**
	 * Given an item and a payment, this function vends an item. The following
	 * is checked: the item exists, there is enough of the item in the vending
	 * machine, enough has been paid to purchase the item
	 * 
	 * @param id The id of the item to buy
	 * @param payment The payment in dollars
	 * @return A map with two fields: success and message.
	 */
	@PostMapping("/{id}")
	public VendResponse vendItem(@PathVariable long id, @RequestBody VendRequest request) throws NegativePaymentException {	
		Item hit = itemRepo.findById(id).orElse(null);
		
		// Item not found
		if(hit == null) {
			return new VendResponse(false, "Item not found");
		}
		
		// Not enough of the item 
		if(hit.getQuantity() <= 0) {
			return new VendResponse(false, hit.getName() + " is out of stock");
		}
		
		// Not enough money
		if(hit.getCost() > request.getPayment()) {
			return new VendResponse(false, "Insufficient funds");
		}
		
		// Dispense one of the item
		VendResponse response = new VendResponse(true, "Enjoy your " + hit.getName());
		
		// Update the quantity
		hit.setQuantity(hit.getQuantity() - 1);
		itemRepo.save(hit);
		return response;
	}
}
