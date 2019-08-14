package com.mycompany;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mycompany.resources.AuthorResource;
import com.mycompany.resources.BookResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ru.vyarus.dropwizard.guice.GuiceBundle;

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
        bootstrap.addBundle(GuiceBundle.builder()
                .modules(new LibraryModule())
                .enableAutoConfig(getClass().getPackage().getName())
                .build());
    }

    @Override
    public void run(final LibraryConfiguration configuration,
                    final Environment environment) {

//        // Guice Injection
//        Injector injector = Guice.createInjector(new LibraryModule());
//        AuthorResource authorResource = injector.getInstance(AuthorResource.class);
//        // environment.jersey().register(authorResource);
//        BookResource bookResource = injector.getInstance(BookResource.class);
//        // environment.jersey().register(bookResource);
    }

}
