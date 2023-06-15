package com.ceiba.adn.app.rating.clientes;

import java.util.List;

import com.ceiba.adn.app.commons.models.entity.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "service-book")
public interface RatingClienteRest {
	
	@GetMapping("/book")
	public List<Book> listar();
	
	@GetMapping("/book/{id}")
	public Book detalle(@PathVariable Long id);
	
	@PostMapping("/book")
	public Book crear(@RequestBody Book producto);
	
	@PutMapping("/book/{id}")
	public Book update(@RequestBody Book producto, @PathVariable Long id);
	
	@DeleteMapping("/book/{id}")
	public void eliminar(@PathVariable Long id);

	@PutMapping("/book/{id}/addstar")
	public Book addStar(@PathVariable Long id);

	@PutMapping("/book/{id}/removestart")
	public Book removeStar(@PathVariable Long id);
}
