package com.ceiba.adn.app.books.models.dao;

import com.ceiba.adn.app.commons.models.entity.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BookDao extends CrudRepository<Book, Long>{

    @Modifying
    @Query("update Book s SET s.stars = s.stars + 1 WHERE s.id = :id")
    public void addStart(@Param("id") long id);

    @Modifying
    @Query("update Book s SET s.stars = s.stars - 1 WHERE s.id = :id")
    public void removeStart(@Param("id") long id);
}
