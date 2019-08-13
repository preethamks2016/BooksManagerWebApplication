package com.mycompany.resources;

import com.mycompany.api.Author;
import com.mycompany.api.Book;
import com.mycompany.core.AuthorRepository;
import io.dropwizard.jersey.params.LongParam;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Path("authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class  AuthorResource {

    @NotNull
    AuthorRepository repository;

    @Inject
    public AuthorResource(AuthorRepository repository) {
        this.repository = repository;
    }

    @GET
    public List<Author> allAuthors() {
        return repository.findAll();
    }

    //get author by id
    @GET
    @Path("{id}")
    public Author author(@PathParam("id") LongParam id) {
        return repository.findById(id.get())
                .orElseThrow(() ->
                        new WebApplicationException("Event not found", 404));
    }

    @POST
    public Author create(Author author) {
        return repository.save(author);
    }

//    @PUT
//    @Path("{id}")
//    public Author update(@PathParam("id") LongParam id, Author author) {
//        return repository.update(id.get(), author)
//                .orElseThrow(() ->
//                        new WebApplicationException("Event not found", 404));
//    }

//    @DELETE
//    @Path("{id}")
//    public Response delete(@PathParam("id") LongParam id) {
//        repository.delete(id.get());
//        return Response.ok().build();
//    }

}
