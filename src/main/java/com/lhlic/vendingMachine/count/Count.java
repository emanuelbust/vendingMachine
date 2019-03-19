package com.lhlic.vendingMachine.count;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="count")
public class Count {
	@EmbeddedId
	private CountKey itemAndCount;
	
	public Count() {
		
	}
	
	public Count(CountKey itemAndCount) {
		this.itemAndCount = itemAndCount;
	}

	public CountKey getItemAndCount() {
		return itemAndCount;
	}

	public void setItemAndCount(CountKey itemAndCount) {
		this.itemAndCount = itemAndCount;
	}
}
