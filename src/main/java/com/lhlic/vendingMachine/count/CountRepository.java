package com.lhlic.vendingMachine.count;

import org.springframework.data.repository.CrudRepository;

/** 
 * This interface will get Spring to support basic CRUD operations for
 * Counts
 */
public interface CountRepository extends CrudRepository<Count, CountKey>{
	
}
