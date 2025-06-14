package com.example.accessingdatajpa;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.logging.Logger;

@SpringBootApplication
public class AccessingDataJpaApplication {

	//private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);
	private final Logger log = Logger.getLogger(AccessingDataJpaApplication.class.getName());
	public static void main(String[] args) {
		SpringApplication.run(AccessingDataJpaApplication.class);
	}

	// This bean is executed after the application context is loaded and right before the Spring Boot application starts.
	// because it implements CommandLineRunner, it will run the code inside the run method after the application context is loaded.
	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save a few customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			repository.findAll().forEach(customer -> {
				log.info(customer.toString());
			});
			log.info("");

			// fetch an individual customer by ID
			Customer customer = repository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("1L => long 1 => integer 1 btw");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(customerFound -> {
				log.info(customerFound.toString());
			});
			log.info("");
		};
	}

}