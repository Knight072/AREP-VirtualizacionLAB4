package edu.eci.arep.virtualization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Main class for the virtualization application.
 * @author Andrew Arias
 */
@SpringBootApplication
@EnableMongoRepositories
public class VirtualizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtualizationApplication.class, args);
	}

}
