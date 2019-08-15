package com.mycompany;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.mycompany.annotations.dataSourceAuthor;
import com.mycompany.annotations.dataSourceBook;
import com.mycompany.core.AuthorRepository;
import com.mycompany.core.BookRepository;
import com.mycompany.core.DummyAuthorRepository;
import com.mycompany.core.DummyBookRepository;

public class LibraryModule extends AbstractModule {
    @Override
    protected void configure() {

        bind(BookRepository.class).to(DummyBookRepository.class).in(Singleton.class);
        bind(AuthorRepository.class).to(DummyAuthorRepository.class).in(Singleton.class);
        bind(String.class).annotatedWith(dataSourceAuthor.class).toInstance("dummy_authorData.json");
        bind(String.class).annotatedWith(dataSourceBook.class).toInstance("dummy_bookData.json");

    }
}
