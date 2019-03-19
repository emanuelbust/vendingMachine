package com.lhlic.vendingMachine.count;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class CountKey implements Serializable{
	@NotNull
	private Long itemID;
	
	@NotNull
	private Long count;
	
	// Default constructor
	public CountKey() {
		
	}
	
	/**
	 * This constructor lets you pass itemID and count directly as opposed
	 * to creating the CountKey and then calling each setter.
	 * 
	 * @param itemID Id of the item
	 * @param count How many of the item are in the vending machine
	 */
	public CountKey(Long itemID, Long count) {
		this.itemID = itemID;
		this.count = count;
	}

	/*
	 * Getters and setters
	 */
	public Long getItemID() {
		return itemID;
	}

	public void setItemID(Long itemID) {
		this.itemID = itemID;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	
	/**
	 * Returns true if and only if the other object is a CountKey with the same
	 * itemID and count.
	 */
	@Override
	public boolean equals(Object o) {
		if(o.getClass() != CountKey.class) {
			return false;
		}
		
		CountKey other = (CountKey) o;
		boolean equals = this.getCount().equals(other.getCount());
		equals &= this.getItemID().equals(other.getItemID());
		
		return equals;
	}
	
	
	/**
	 * Makes sure CountKeys with the same itemID and count are mapped to the
	 * same spot in a hash table. For more info on the express, see Google
	 * Godel numbering
	 */
	@Override
	public int hashCode() {
		return (int) (Math.pow(2, this.getItemID()) * Math.pow(3, this.getCount()));
	}
}
