package com.lhlic.vendingMachine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.lhlic.vendingMachine.item.Item;
import com.lhlic.vendingMachine.item.ItemRepository;

@SpringBootApplication
public class VendingMachineApplication {
	// Command line logger
	private static final Logger log = LoggerFactory.getLogger(VendingMachineApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(VendingMachineApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(ItemRepository itemRepo) {
		return (ags) -> {
			log.info("\tStarting logging...");
			
			itemRepo.save(new Item("Banana", "The funniest fruit", 1.00f, 8));
			itemRepo.save(new Item("Hot Cheetos", "The best", 1.00f, 4));
			itemRepo.save(new Item("Arizona Iced Tea", "Have to get this with Hot Cheetos", 1.00f, 3));
			itemRepo.save(new Item("Gum", "Stay fresh with mint gum", 1.00f, 12));
			itemRepo.save(new Item("Danish", "This danish is pure protein", 1.00f, 1));
			log.info("Number of items: " + itemRepo.count());
		};
	}

}
