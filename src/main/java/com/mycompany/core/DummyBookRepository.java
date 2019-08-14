package com.mycompany.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.mycompany.api.Book;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class DummyBookRepository implements BookRepository {

    static final String DATA_SOURCE = "dummy_bookData.json";

    List<Book> books;

    public DummyBookRepository() {
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
//        CollectionType type = mapper.getTypeFactory()
//                .constructCollectionType(List.class, Author.class);
//        authors = mapper.readValue(json, type);
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
        return Optional.of(books.stream().filter(e -> e.getAuthorId() == authorId).collect(Collectors.toList()));

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

