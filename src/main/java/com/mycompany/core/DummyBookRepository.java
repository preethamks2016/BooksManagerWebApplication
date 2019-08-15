package com.mycompany.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.mycompany.annotations.dataSourceBook;
import com.mycompany.api.Book;
import com.mycompany.memory.BooksCache;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class DummyBookRepository implements BookRepository {

    static String DATA_SOURCE;

    List<Book> books;

    @Inject
    BooksCache cache;

    @Inject
    public DummyBookRepository(@dataSourceBook String DATA_SOURCE) {
        this.DATA_SOURCE = DATA_SOURCE;
        try {
            initData();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(DATA_SOURCE +
                    " is missing or is unreadable");
        }
    }

    private void initData() throws IOException {
        URL url = Resources.getResource(DATA_SOURCE);
        String json = Resources.toString(url, Charsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        books = mapper.readValue(json, new TypeReference<List<Book>>() {
        });
    }

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return books.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<List<Book>> findBooksByAuthorId(Long authorId) {

        if (cache.booksByAuthorIdExists(authorId)) {
            return Optional.of(cache.getBooksByAuthorId(authorId));
        } else {
            Optional<List<Book>> booksByAuthor =
                    Optional.of(books.stream().filter(e -> e.getAuthorId() == authorId)
                            .collect(Collectors.toList()));
            if (booksByAuthor.isPresent()) {
                cache.addBooksByAuthorId(authorId, booksByAuthor.get());
            }
            return booksByAuthor;
        }
    }

    @Override
    public Book save(Book book) {
        books.add(book);
        return book;
    }
//    @Override
//    public Optional<Book> findBooksByAuthorName(String authorName) {
//        return books.stream().filter(e -> e.getAuthorName().equals(authorName)).findFirst();
//    }
//    @Override
//    public Optional<Book> update(Long id, Author author) {
//        Optional<Book> existingEvent = findById(id);
//        existingEvent.ifPresent(e -> e.updateExceptId(author));
//        return existingEvent;
//    }
//
//    @Override
//    public void delete(Long id) {
//        books.removeIf(e -> e.getId() == id);
//    }

}

