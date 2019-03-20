package com.lhlic.vendingMachine.item;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// Tell Junit Spring is running the test
@RunWith(SpringRunner.class)

// Set up the database
@DataJpaTest

public class ItemRepositoryTest {
	// For making commits
	@Autowired
	private TestEntityManager entityManager;
	
	// For doing crud operations
	@Autowired
	private ItemRepository itemRepo;
	
	@Test
	// Make sure five items were added during initialization
	public void init() {
		assertEquals(5L, itemRepo.count());
	}
	
	@Test
	public void nullQuantityException() {
		// No quantity
		Item item = new Item("Test", "Fake fruit", 1.00f, null);
		boolean exception = false;
		try {
			itemRepo.save(item);
			entityManager.flush();
		} catch (ConstraintViolationException e) {
			exception = true;
		}
		assertTrue(exception);
	}
	
	@Test
	public void negativeQuantityException() {
		Item item = new Item("Test", "Fake fruit", 1.00f, null);
		boolean exception = false;
		item.setQuantity(-1);
		try {
			itemRepo.save(item);
			entityManager.flush();
		} catch (ConstraintViolationException e) {
			exception = true;
		}
		assertTrue(exception);
	}
	
	@Test
	public void nullCostException() {
		// No cost
		Item item = new Item("Test", "Fake fruit", null, 3);
		boolean exception = false;
		try {
			itemRepo.save(item);
			entityManager.flush();
		} catch (ConstraintViolationException e) {
			exception = true;
		}
		assertTrue(exception);
	}
	
	@Test
	public void negativeCostException() {
		// Negative cost
		Item item = new Item("Test", "Fake fruit", null, 3);
		boolean exception = false;
		item.setCost(-1f);;
		try {
			itemRepo.save(item);
			entityManager.flush();
		} catch (ConstraintViolationException e) {
			exception = true;
		}
		
		assertTrue(exception);
	}
	
	@Test
	public void nullNameException() {
		// No name
		Item item = new Item(null, "Fake fruit", 2.36f, 3);
		boolean exception = true;
		try {
			itemRepo.save(item);
			entityManager.flush();
		} catch (ConstraintViolationException e) {
			exception = true;
		}
		assertTrue(exception);
	}
	
	@Test
	public void emptyNameException() {
		// Empty name
		Item item = new Item(null, "Fake fruit", 2.36f, 3);
		boolean exception = false;
		item.setName("");
		try {
			itemRepo.save(item);
			entityManager.flush();
		} catch (ConstraintViolationException e) {
			exception = true;
		}
		
		assertTrue(exception);
	}
	
	
	@Test
	public void nullDescriptionException() {
		// No description
		Item item = new Item("Test", null, 2.36f, 3);
		boolean exception = true;
		try {
			itemRepo.save(item);
			entityManager.flush();
		} catch (ConstraintViolationException e) {
			exception = true;
		}
		assertTrue(exception);
	}

	@Test
	public void emptyDescriptionException() {
		// Empty description
		Item item = new Item("Test", null, 2.36f, 3);
		boolean exception = true;
		item.setDescription("");
		try {
			itemRepo.save(item);
			entityManager.flush();
		} catch (ConstraintViolationException e) {
			exception = true;
		}
		
		assertTrue(exception);
	}
}
