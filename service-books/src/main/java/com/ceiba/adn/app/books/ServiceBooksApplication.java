package com.ceiba.adn.app.books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.ceiba.adn.app.commons.models.entity"})
public class ServiceBooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceBooksApplication.class, args);
	}

}
