package com.mycompany.api;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class Author {

    @NonNull
    Long id;

    @NotEmpty
    String authorName;

//    public void updateExceptId(Author author) {
//        this.authorName = author.authorName;
//    }
}