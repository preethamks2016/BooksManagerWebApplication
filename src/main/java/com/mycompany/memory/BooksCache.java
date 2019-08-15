package com.mycompany.memory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mycompany.LibraryModule;
import com.mycompany.api.Book;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class BooksCache {
    final int cacheSize = 3;
    HashMap<Long, BookObject> bookMap = new HashMap<>();
    int cacheRead;

    public boolean booksByAuthorIdExists(Long id) {
        if (bookMap.containsKey(id % cacheSize)) {
            return bookMap.get(id % cacheSize).getAuthorId() == id;
        } else {
            return false;
        }
    }

    public List<Book> getBooksByAuthorId(Long id) {
        System.out.println("cacheRead (Books) : " + ++cacheRead);
        return bookMap.get(id % cacheSize).getBooks();
    }

    public void addBooksByAuthorId(Long authorId, List<Book> newBookList) {
//        bookMap.put(authorId%cacheSize, injector.getInstance(BookObject.class));
        bookMap.put(authorId % cacheSize, new BookObject(authorId, newBookList));
    }
}
