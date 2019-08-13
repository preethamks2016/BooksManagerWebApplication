package com.mycompany.api;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class Author {

    @NotNull private Long id;
    @NotEmpty private String authorName;

    @JsonGetter
    public long getId() {
        return id;
    }
    @JsonSetter
    public void setId(long id) {
        this.id = id;
    }
    @JsonGetter
    public String getAuthorName() {
        return authorName;
    }
    @JsonSetter
    public void setAuthorName(String name) {
        this.authorName = name;
    }

    public void updateExceptId(Author author) {
        this.authorName = author.authorName;
    }
}