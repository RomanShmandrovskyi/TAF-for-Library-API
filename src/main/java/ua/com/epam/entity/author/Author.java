package ua.com.epam.entity.author;

import lombok.*;
import ua.com.epam.entity.author.nested.Birth;
import ua.com.epam.entity.author.nested.Name;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Author {
    @Setter(AccessLevel.NONE)
    private Long authorId;
    private Name authorName;
    private String nationality;
    private Birth birth;
    private String authorDescription;
}
