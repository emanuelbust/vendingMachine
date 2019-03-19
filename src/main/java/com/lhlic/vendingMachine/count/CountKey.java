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
	
	public CountKey() {
		
	}
	
	public CountKey(Long itemID, Long count) {
		this.itemID = itemID;
		this.count = count;
	}

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
	
	// TODO test
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
	
	
	// TODO test
	@Override
	public int hashCode() {
		return (int) (Math.pow(2, this.getItemID()) * Math.pow(3, this.getCount()));
	}
}
