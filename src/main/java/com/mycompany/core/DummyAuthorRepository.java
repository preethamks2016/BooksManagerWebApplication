package com.mycompany.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.mycompany.annotations.dataSourceAuthor;
import com.mycompany.api.Author;
import com.mycompany.memory.AuthorsCache;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class DummyAuthorRepository implements AuthorRepository {


    static String DATA_SOURCE;

    List<Author> authors;
    @Inject
    AuthorsCache cache;

    @Inject
    public DummyAuthorRepository(@dataSourceAuthor String DATA_SOURCE) {
        this.DATA_SOURCE = DATA_SOURCE;
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
        authors = mapper.readValue(json, new TypeReference<List<Author>>() {
        });
    }

    @Override
    public List<Author> findAll() {
        return authors;
    }

    @Override
    public Optional<Author> findById(Long id) {
        if (cache.authorExists(id)) {
            return cache.getAuthor(id);
        } else {
            Optional<Author> newAuthor = authors.stream().filter(e -> e.getId().equals(id)).findFirst();
            if (newAuthor.isPresent()) {
                cache.addAuthor(newAuthor);
            }
            return newAuthor;
        }
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

