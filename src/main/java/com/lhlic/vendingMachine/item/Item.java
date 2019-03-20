package com.lhlic.vendingMachine.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="item")
public class Item {
	/*
	 * Items with null ids and duplicate ids will have a valid id
	 * assigned to them 
	 */
	@Id
	@NotNull
	@Column(unique = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@NotNull
	@Size(min = 1, message = "Item must have a name")
	private String name;
	
	@NotNull
	@Size(min = 1)
	private String description;
	
	@NotNull
	@Min(value = 0, message = "Cost should be non negative")
	private Float cost;
	
	@NotNull
	@Min(value = 0, message = "Quantity should be non negative")
	private Integer quantity;
	
	// Default constructor
	public Item() {}
	
	/**
	 * Constructor that allows you to avoid calling the setter for name,
	 * description and price 
	 */
	public Item(String name, String description, Float cost, Integer quantity) {
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.quantity = quantity;
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
