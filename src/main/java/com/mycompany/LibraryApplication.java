package com.mycompany;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mycompany.resources.AuthorResource;
import com.mycompany.resources.BookResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class LibraryApplication extends Application<LibraryConfiguration> {

    public static void main(final String[] args) throws Exception {
        new LibraryApplication().run(args);
    }

    @Override
    public String getName() {
        return "Authors";
    }


    @Override
    public void initialize(final Bootstrap<LibraryConfiguration> bootstrap) {
        super.initialize(bootstrap);
        // TODO: application initialization
    }

    @Override
    public void run(final LibraryConfiguration configuration,
                    final Environment environment) {
        // Guice Injection
        Injector injector = Guice.createInjector(new LibraryModule());
        //registering author resource
        // AuthorRepository repository1 = new DummyAuthorRepository();
        // AuthorResource authorResource = new AuthorResource(repository1);
        AuthorResource authorResource = injector.getInstance(AuthorResource.class);
        environment.jersey().register(authorResource);
        //registering book resource
        // BookRepository repository2 = new DummyBookRepository();
        // BookResource bookResource = new BookResource(repository2);
        BookResource bookResource = injector.getInstance(BookResource.class);
        environment.jersey().register(bookResource);
    }

}
