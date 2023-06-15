package com.ceiba.adn.app.rating.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ceiba.adn.app.commons.models.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


//@Service("serviceRestTemplate")
public class RatingServiceImpl { //implements RatingService

	@Autowired
	private RestTemplate clienteRest;
	
//	@Override
	public List<Book> findAll() {
		List<Book> books = Arrays.asList(clienteRest.getForObject("http://service-book/listar", Book[].class));
		
		return books;
	}

//	@Override
	public Book findById(Long id) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		return clienteRest.getForObject("http://service-book/ver/{id}", Book.class, pathVariables);
	}

//	@Override
	public Book save(Book book) {
		HttpEntity<Book> body = new HttpEntity<Book>(book);
		
		ResponseEntity<Book> response = clienteRest.exchange("http://service-book/crear", HttpMethod.POST, body, Book.class);
		Book productoResponse = response.getBody();
		return productoResponse;
	}

//	@Override
	public Book update(Book book, Long id) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		
		HttpEntity<Book> body= new HttpEntity<Book>(book);
		ResponseEntity<Book> response = clienteRest.exchange("http://service-book/editar/{id}",
				HttpMethod.PUT, body, Book.class, pathVariables);
		
		return response.getBody();
	}

//	@Override
	public void delete(Long id) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		clienteRest.delete("http://service-book/eliminar/{id}", pathVariables);
	}

//	@Override
	public void addStar(Long id) {

	}

//	@Override
	public void removeStar(Long id) {

	}

}
