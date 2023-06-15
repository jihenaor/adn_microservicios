package com.ceiba.adn.app.books.models.service;

import java.util.List;

import com.ceiba.adn.app.commons.models.entity.Book;

public interface IBookService {

	public List<Book> findAll();
	public Book findById(Long id);
	
	public Book save(Book book);
	
	public void deleteById(Long id);

	public void addStar(Long id);

	public void removeStar(Long id);
}
