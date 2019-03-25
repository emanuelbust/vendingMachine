package com.lhlic.vendingMachine.item;

import java.text.NumberFormat;

public class Item {
    private Long id;
	
	private String name;
	
	private String description;
	
	private Float cost;
	
	private Integer quantity;
	
	// Default constructor
	public Item() {}
	
	/**
	 * Constructor that allows you to avoid calling the setter for name,
	 * description and price 
	 */
	public Item(Long id, String name, String description, Float cost, Integer quantity) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.quantity = quantity;
	}
	
	public String getFormattedCost() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		return formatter.format(cost);
	}

	/*
	 * Getters and setters
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
