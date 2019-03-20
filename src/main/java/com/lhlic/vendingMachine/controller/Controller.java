package com.lhlic.vendingMachine.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public Map<String, Object> vendItem(@PathVariable long id, @RequestParam float payment) {
		HashMap<String, Object> response = new HashMap<String, Object>();
		String success = "success";
		String message = "status";
		
		Item hit = itemRepo.findById(id).orElse(null);
		
		// Item not found
		if(hit == null) {
			response.put(success , false);
			response.put(message, "Item not found");
			return response;
		}
		
		// Not enough of the item 
		if(hit.getQuantity() <= 0) {
			response.put(success , false);
			response.put(message, hit.getName() + " is out of stock");
			return response;
		}
		
		// Not enough money
		if(hit.getCost() > payment) {
			response.put(success , false);
			response.put(message, "Insufficient funds");
			return response;
		}
		
		// Dispense one of the item
		response.put(success , true);
		response.put(message, "Enjoy your " + hit.getName());
		
		// Update the quantity
		hit.setQuantity(hit.getQuantity() - 1);
		itemRepo.save(hit);
		return response;
	}
}
