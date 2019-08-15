package com.mycompany.resources;

import com.mycompany.api.Book;
import com.mycompany.core.BookRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

//@Singleton
@Path("books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject
    BookRepository repository;

    @GET
    public List<String> book(@QueryParam("authorId") Long authorId) {
        List<String> bookNames = new ArrayList<>();
        List<Book> books;
        if (authorId != null) {
            books = repository.findBooksByAuthorId(authorId)
                    .orElseThrow(() ->
                            new WebApplicationException("Event not found", 404));
        } else {
            books = repository.findAll();
        }
        for (Book i : books) {
            bookNames.add(i.getBookName());
        }
        if (bookNames.isEmpty()) {
            throw new WebApplicationException("Event not found", 404);
        }
        return bookNames;
    }


    @POST
    public Book create(Book book) {
        return repository.save(book);
    }

//    @PUT
//    @Path("{id}")
//    public Author update(@PathParam("id") LongParam id, Author author) {
//        return repository.update(id.get(), author)
//                .orElseThrow(() ->
//                        new WebApplicationException("Event not found", 404));
//    }
//
//    @DELETE
//    @Path("{id}")
//    public Response delete(@PathParam("id") LongParam id) {
//        repository.delete(id.get());
//        return Response.ok().build();
//    }

}
