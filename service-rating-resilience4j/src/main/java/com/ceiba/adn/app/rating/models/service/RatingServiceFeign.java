package com.ceiba.adn.app.rating.models.service;

import java.util.List;

import com.ceiba.adn.app.commons.models.entity.Book;
import com.ceiba.adn.app.rating.clientes.RatingClienteRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("serviceFeign")
public class RatingServiceFeign implements RatingService {
	
	@Autowired
	private RatingClienteRest clienteFeign;

	@Override
	public List<Book> findAll() {
		return clienteFeign.listar();
	}

	@Override
	public Book findById(Long id) {
		return clienteFeign.detalle(id);
	}

	@Override
	public Book save(Book producto) {
		return clienteFeign.crear(producto);
	}

	@Override
	public Book update(Book producto, Long id) {
		return clienteFeign.update(producto, id);
	}

	@Override
	public void delete(Long id) {
		clienteFeign.eliminar(id);
	}

	@Override
	public void addStar(Long id) {
		clienteFeign.addStar(id);
	}

	@Override
	public void removeStar(Long id) {
		clienteFeign.removeStar(id);
	}
}
