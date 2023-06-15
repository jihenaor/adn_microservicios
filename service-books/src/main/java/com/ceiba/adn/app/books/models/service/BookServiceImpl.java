package com.ceiba.adn.app.books.models.service;

import java.util.List;

import com.ceiba.adn.app.commons.models.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.adn.app.books.models.dao.BookDao;

@Service
public class BookServiceImpl implements IBookService {

	@Autowired
	private BookDao bookDao;

	@Override
	@Transactional(readOnly = true)
	public List<Book> findAll() {
		return (List<Book>) bookDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Book findById(Long id) {
		return bookDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Book save(Book book) {
		return bookDao.save(book);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		bookDao.deleteById(id);
	}

	@Override
	@Transactional
	public void addStar(Long id) {
		bookDao.addStart(id);
	}

	@Override
	@Transactional
	public void removeStar(Long id) {
		bookDao.removeStart(id);
	}
}
