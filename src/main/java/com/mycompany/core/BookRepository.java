package com.mycompany.core;

import com.mycompany.api.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<List<Book>> findBooksByAuthorId(Long id);

    Book save(Book book);

//    Optional<Book> update(Long id, Book book);
//
//    void delete(Long id);

}
