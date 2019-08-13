package com.mycompany;

import com.google.inject.AbstractModule;
import com.mycompany.core.AuthorRepository;
import com.mycompany.core.BookRepository;
import com.mycompany.core.DummyAuthorRepository;
import com.mycompany.core.DummyBookRepository;

public class LibraryModule extends AbstractModule {
    @Override
    protected void configure() {

        bind(BookRepository.class).to(DummyBookRepository.class);
        bind(AuthorRepository.class).to(DummyAuthorRepository.class);

    }
}
