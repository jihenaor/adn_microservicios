package com.ceiba.adn.app.books.controllers;

import java.util.List;

import com.ceiba.adn.app.commons.models.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.ceiba.adn.app.books.models.service.IBookService;

@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	private IBookService bookService;
	
	@GetMapping
	public List<Book> listar(){
		return bookService.findAll();
	}
	
	@GetMapping("/{id}")
	public Book detalle(@PathVariable Long id) {
		if (id == 0) {
			throw new RuntimeException(String.format("No se pudo cargar el libro con id %s", id));
		}

		return bookService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Book crear(@RequestBody Book producto) {
		return bookService.save(producto);
		
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Book editar(@RequestBody Book book, @PathVariable Long id) {
		Book bookDb = bookService.findById(id);

		bookDb.setTitle(book.getTitle());
		bookDb.setAuthor(book.getAuthor());
        
        return bookService.save(bookDb);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		bookService.deleteById(id);
	}

	@PutMapping("/{id}/addstar")
	@ResponseStatus(HttpStatus.CREATED)
	public void addStar(@RequestBody Book book, @PathVariable Long id) {
		bookService.addStar(book.getId());
	}

	@PutMapping("/{id}/removestar")
	@ResponseStatus(HttpStatus.CREATED)
	public void removeStar(@RequestBody Book book, @PathVariable Long id) {
		bookService.addStar(book.getId());
	}
}
