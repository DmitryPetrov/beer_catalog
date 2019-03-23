package com.example.name;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example"})
public class BeerCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeerCatalogApplication.class, args);
	}

}
