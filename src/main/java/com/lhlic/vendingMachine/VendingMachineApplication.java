package com.lhlic.vendingMachine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VendingMachineApplication {
	private static final Logger log = LoggerFactory.getLogger(VendingMachineApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(VendingMachineApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(ItemRepository repository) {
		return (ags) -> {
			log.info("\tStarting logging...");
			repository.save(new Item("Banana", "Yellow", 1.00f));
			
			
			long items = repository.count();
			log.info("Number of items: " + items);
		};
	}

}
