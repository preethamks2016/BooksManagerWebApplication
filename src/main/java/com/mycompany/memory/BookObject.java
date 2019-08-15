package com.mycompany.memory;

import com.mycompany.api.Book;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class BookObject {
    private Long authorId;
    private List<Book> books;

}
