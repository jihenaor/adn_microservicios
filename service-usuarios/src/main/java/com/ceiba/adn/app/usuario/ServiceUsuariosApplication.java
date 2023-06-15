package com.ceiba.adn.app.usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan({"com.ceiba.adn.app.commons.usuarios.models.entity"})
@SpringBootApplication
public class ServiceUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceUsuariosApplication.class, args);
	}

}
