package com.mycompany.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.mycompany.api.Author;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

public class DummyAuthorRepository implements AuthorRepository {

    private static final String DATA_SOURCE = "dummy_authorData.json";

    private List<Author> authors;

    public DummyAuthorRepository() {
        try {
            initData();
        } catch (IOException e) {
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
        authors = mapper.readValue(json, new TypeReference<List<Author>>(){});
    }

    @Override
    public List<Author> findAll() {
        return authors;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authors.stream().filter(e -> e.getId() == id).findFirst();
    }


//    @Override
//    public Author save(Author event) {
//        Optional<Long> maxId = authors.stream()
//                .map(Author::getId)
//                .max(Long::compare);
//        long nextId = maxId.map(x -> x + 1).orElse(1L);
//        event.setId(nextId);
//        authors.add(event);
//        return event;
//    }
    @Override
    public Author save(Author author) {
        authors.add(author);
        return author;
    }
    @Override
    public Optional<Author> update(Long id, Author author) {
        Optional<Author> existingEvent = findById(id);
        existingEvent.ifPresent(e -> e.updateExceptId(author));
        return existingEvent;
    }

    @Override
    public void delete(Long id) {
        authors.removeIf(e -> e.getId() == id);
    }

}

