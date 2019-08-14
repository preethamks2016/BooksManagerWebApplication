package com.mycompany.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.mycompany.api.Author;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class DummyAuthorRepository implements AuthorRepository {

    static final String DATA_SOURCE = "dummy_authorData.json";

    List<Author> authors;

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
        authors = mapper.readValue(json, new TypeReference<List<Author>>() {
        });
    }

    @Override
    public List<Author> findAll() {
        return authors;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authors.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    @Override
    public Author save(Author author) {
        authors.add(author);
        return author;
    }
//    @Override
//    public Optional<Author> update(Long id, Author author) {
//        Optional<Author> existingEvent = findById(id);
//        existingEvent.ifPresent(e -> e.updateExceptId(author));
//        return existingEvent;
//    }

//    @Override
//    public void delete(Long id) {
//        authors.removeIf(e -> e.getId() == id);
//    }

}

