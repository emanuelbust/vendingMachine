package com.lhlic.vendingMachine.count;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * This class is used to keep track of how many of an item the vending machine
 * has in stock. The orm will map this class to a row in a database called
 * count
 */
@Entity
@Table(name="count")
public class Count {
	
	/*
	 * The primary key for a row of this table will be a composite key made up
	 * of the item id and the count 
	 */
	@EmbeddedId
	private CountKey itemAndCount;
	
	// Default constructor
	public Count() {
		
	}
	
	/**
	 * This constructor makes it easer to make a Count. Instead of using the 
	 * default constructor and using a setter, this constructor lets you just
	 * pass the CountKey.
	 * 
	 * @param itemAndCount A CountKey holding an item id and a count for that
	 * item
	 */
	public Count(CountKey itemAndCount) {
		this.itemAndCount = itemAndCount;
	}

	/*
	 * Getter and setter for CountKey
	 */
	public CountKey getItemAndCount() {
		return itemAndCount;
	}

	public void setItemAndCount(CountKey itemAndCount) {
		this.itemAndCount = itemAndCount;
	}
}
