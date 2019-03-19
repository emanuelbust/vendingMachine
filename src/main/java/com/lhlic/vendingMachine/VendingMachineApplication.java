package com.lhlic.vendingMachine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.lhlic.vendingMachine.count.Count;
import com.lhlic.vendingMachine.count.CountKey;
import com.lhlic.vendingMachine.count.CountRepository;
import com.lhlic.vendingMachine.item.Item;
import com.lhlic.vendingMachine.item.ItemRepository;

@SpringBootApplication
public class VendingMachineApplication {
	private static final Logger log = LoggerFactory.getLogger(VendingMachineApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(VendingMachineApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(ItemRepository itemRepo, CountRepository countRepo) {
		return (ags) -> {
			log.info("\tStarting logging...");
			
			itemRepo.save(new Item("Banana", "Yellow", 1.00f));
			log.info("Number of items: " + itemRepo.count());
			
			
			countRepo.save(new Count(new CountKey(new Long(1), new Long(1))));
			log.info("Number of counts: " + countRepo.count());
			
		};
	}

}
