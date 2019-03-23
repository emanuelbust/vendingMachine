package com.lhlic.vendingMachine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhlic.vendingMachine.exceptions.InsufficientFundsException;
import com.lhlic.vendingMachine.exceptions.ItemNotFoundException;
import com.lhlic.vendingMachine.exceptions.OutOfStockException;
import com.lhlic.vendingMachine.item.Item;


@RestController
@RequestMapping("item")
@CrossOrigin
public class Controller {
	@Autowired
	private VendingService vendingService;
	
	/**
	 * Gets a list of all of the items in the vending machine
	 * 
	 * @return A list of all the items in the vending machine
	 */
	@GetMapping
	public List<Item> getAllItems(){
		return vendingService.getAllItems();
	}
	
	/**
	 * Gets all of the information of an item with a given id
	 * 
	 * @param id Id of the item you want
	 * @return The item you want
	 */
	@GetMapping("/{id}")
	public Item getItem(@PathVariable long id) {
		return vendingService.getItem(id);
	}
	
	/**
	 * Calls for an item to vended and all of the 
	 * 
	 * @param id The id of the item to buy
	 * @param payment The payment in dollars
	 * @return A map with two fields: success and message.
	 */
	@PostMapping("/{id}")
	public VendResponse vendItem(@PathVariable long id, @RequestBody VendRequest request) {	
		// Try to vend the item
		VendResponse response = null;
		try {
			response = vendingService.vendItem(id, request);
		} catch (ItemNotFoundException | OutOfStockException | InsufficientFundsException e) {
			response = new VendResponse();
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		
		// Respond to the front end
		return response;
	}
}
