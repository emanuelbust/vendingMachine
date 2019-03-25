package com.lhlic.vendingMachine.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.lhlic.vendingMachine.VendingMachineApplication;
import com.lhlic.vendingMachine.exceptions.InsufficientFundsException;
import com.lhlic.vendingMachine.exceptions.ItemNotFoundException;
import com.lhlic.vendingMachine.exceptions.OutOfStockException;
import com.lhlic.vendingMachine.item.Item;


/**
 * This class manages all of the http request from the front end. The
 * controller is able to get single items, all items, and vend and item. 
 */
@RestController
@RequestMapping("item")
@CrossOrigin
public class Controller {
	// Json builder
	private static final Gson gson = new Gson();
	
	// Command line logger
	private static final Logger log = LoggerFactory.getLogger(VendingMachineApplication.class);
	
	@Autowired
	// Business logic for vending machine
	private VendingService vendingService;
	
	/**
	 * Gets a list of all of the items in the vending machine
	 * 
	 * @return A list of all the items in the vending machine
	 */
	@GetMapping
	public List<Item> getAllItems(){
		log.info("Controller request for all items");
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
		log.info("Controller request for item: " + id);
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
		
		// Log
		log.info("Request to vend: " + gson.toJson(request));
		log.info("Response to vend: " + gson.toJson(response));
		
		// Respond to the front end
		return response;
	}
}
