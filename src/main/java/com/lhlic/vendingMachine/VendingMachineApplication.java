package com.lhlic.vendingMachine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** 
 * This class is the entry point for the vending machine server. The server has
 * two main functions: dispensing information on items including their name,
 * cost, etc and validating customer payments.
 */
@SpringBootApplication
public class VendingMachineApplication {
	// Command line logger
	private static final Logger log = LoggerFactory.getLogger(VendingMachineApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(VendingMachineApplication.class, args);
	}
}
