package com.mycompany.api;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class Book {

    @NotNull private Long id;
    @NotNull private Long authorId;
    @NotEmpty private String bookName;

    @JsonGetter
    public long getAuthorId() {
        return authorId;
    }
    @JsonSetter
    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }
    @JsonGetter
    public long getId() { return id; }
    @JsonSetter
    public void setId(long id) { this.id = id; }
    @JsonGetter
    public String getBookName() {
        return bookName;
    }
    @JsonSetter
    public void setBookName(String name) {
        this.bookName = name;
    }

    public void updateExceptId(Book book) {
        this.bookName = book.bookName;
    }
}