package com.lhlic.vendingMachine.item;

import org.springframework.data.repository.CrudRepository;

/** 
 * This interface will get Spring to support basic CRUD operations for
 * Items
 */
public interface ItemRepository extends CrudRepository<Item, Long>{

}
