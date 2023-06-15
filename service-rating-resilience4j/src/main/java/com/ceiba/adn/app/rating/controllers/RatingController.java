package com.ceiba.adn.app.rating.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.ceiba.adn.app.commons.models.entity.Book;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.ceiba.adn.app.rating.models.service.RatingService;


@RefreshScope
@RestController
@RequestMapping("/rating")
public class RatingController {

	private static Logger logger = LoggerFactory.getLogger(RatingController.class);

	@Autowired
	private CircuitBreakerFactory cbFactory;

	@Autowired
	@Qualifier("serviceFeign")
	private RatingService ratingService;

	@GetMapping()
	public List<Book> listar(){
		return ratingService.findAll();
	}

	@GetMapping("/{id}")
	public Book detalle(@PathVariable Long id) {
		return cbFactory.create("rating")
				.run(()-> ratingService.findById(id), e -> metodoAlternativo(id, e));
	}

	@CircuitBreaker(name="rating", fallbackMethod = "metodoAlternativo")
	@GetMapping("/ver2/{id}/cantidad/{cantidad}")
	public Book detalle2(@PathVariable Long id, @PathVariable Integer cantidad) {
		return ratingService.findById(id);
	}

	@CircuitBreaker(name="rating", fallbackMethod = "metodoAlternativo2")
	@TimeLimiter(name="rating")
	@GetMapping("/ver3/{id}/cantidad/{cantidad}")
	public CompletableFuture<Book> detalle3(@PathVariable Long id, @PathVariable Integer cantidad) {
		return CompletableFuture.supplyAsync(()-> ratingService.findById(id));
	}

	public Book metodoAlternativo(Long id, Throwable e) {
		logger.info(e.getMessage());

		Book book = new Book();

		book.setId(id);
		book.setAuthor("Autor");
		book.setTitle("titulo");
		return book;
	}

	public CompletableFuture<Book> metodoAlternativo2(Long id, Throwable e) {
		logger.info(e.getMessage());
		Book book = new Book();

		book.setId(id);
		book.setAuthor("Autor");
		book.setTitle("titulo");
		return CompletableFuture.supplyAsync(()-> book);
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
}
