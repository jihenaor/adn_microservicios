package com.ceiba.adn.app.rating.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ceiba.adn.app.commons.models.entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ceiba.adn.app.rating.models.service.RatingService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RefreshScope
@RestController
@RequestMapping("/rating")
public class RatingController {
	
	private static Logger log = LoggerFactory.getLogger(RatingController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")
	private RatingService ratingService;

//	@Value("${configuracion.texto}")
//	private String texto;

	@GetMapping()
	public List<Book> listar(){
		return ratingService.findAll();
	}

	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/{id}")
	public Book detalle(@PathVariable Long id) {
		return ratingService.findById(id);
	}

	public Book metodoAlternativo(Long id) {
		Book book = new Book();

		book.setId(id);
		book.setAuthor("Autor");
		book.setTitle("titulo");
		return book;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Book crear(@RequestBody Book book) {
		return ratingService.save(book);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Book editar(@RequestBody Book book, @PathVariable Long id) {
		return ratingService.update(book, id);
	}

	@PutMapping("/{id}/addstar")
	@ResponseStatus(HttpStatus.CREATED)
	public void addStar(@PathVariable Long id) {
		ratingService.addStar(id);
	}

	@PutMapping("/{id}/removestar")
	@ResponseStatus(HttpStatus.CREATED)
	public void removeStar(@PathVariable Long id) {
		ratingService.removeStar(id);
	}

	@GetMapping("/obtener-config")
	public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String puerto){

//		log.info(texto);

		Map<String, String> json = new HashMap<>();
//		json.put("texto", texto);
		json.put("puerto", puerto);

		if(env.getActiveProfiles().length>0 && env.getActiveProfiles()[0].equals("dev")) {
			json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
			json.put("autor.email", env.getProperty("configuracion.autor.email"));
		}

		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}

}
