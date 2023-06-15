package com.ceiba.adn.app.rating.models.service;

import java.util.List;

import com.ceiba.adn.app.commons.models.entity.Book;

public interface RatingService {

	public List<Book> findAll();
	public Book findById(Long id);
	
	public Book save(Book book);
	
	public Book update(Book book, Long id);
	
	public void delete(Long id);

	public void addStar(Long id);

	public void removeStar(Long id);
}
