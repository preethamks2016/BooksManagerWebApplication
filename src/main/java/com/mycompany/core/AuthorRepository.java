package com.mycompany.core;

import com.mycompany.api.Author;
import com.mycompany.api.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    List<Author> findAll();

    Optional<Author> findById(Long id);

    Author save(Author author);

    Optional<Author> update(Long id, Author author);

    void delete(Long id);

}
