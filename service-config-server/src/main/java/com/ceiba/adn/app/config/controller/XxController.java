package com.ceiba.adn.app.config.controller;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class XxController {

	@GetMapping
	public String listar(){
		return "Hola mundo";
	}
	

}
