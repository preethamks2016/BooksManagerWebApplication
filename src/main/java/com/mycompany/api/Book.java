package com.mycompany.api;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@Setter
@Getter
@NoArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class Book {

    @NonNull
    Long id;
    @NonNull
    Long authorId;
    @NotEmpty
    String bookName;

//    public void updateExceptId(Book book) {
//        this.bookName = book.bookName;
//    }

}