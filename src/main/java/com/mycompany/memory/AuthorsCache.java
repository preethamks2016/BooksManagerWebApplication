package com.mycompany.memory;

import com.mycompany.api.Author;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorsCache {
    final int cacheSize = 3;
    HashMap<Long, Author> authorMap = new HashMap<>();
    int cacheRead;

    public boolean authorExists(Long id) {

        if (authorMap.containsKey(id % cacheSize)) {
            return authorMap.get(id % cacheSize).getId() == id;
        } else {
            return false;
        }
    }

    public Optional<Author> getAuthor(Long id) {
        System.out.println("cacheRead (Authors) : " + ++cacheRead);
        return Optional.of(authorMap.get(id % cacheSize));
    }

    public void addAuthor(Optional<Author> newAuthor) {
        authorMap.put(newAuthor.get().getId() % cacheSize, newAuthor.get());
    }
}
