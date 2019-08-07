package ua.com.epam.entity.book;

import lombok.*;
import ua.com.epam.entity.book.nested.Additional;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Book {
    @Setter(AccessLevel.NONE)
    private Long bookId;
    private String bookName;
    private String bookLanguage;
    private String bookDescription;
    private Additional additional;
    private Integer publicationYear;
}
