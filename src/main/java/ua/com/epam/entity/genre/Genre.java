package ua.com.epam.entity.genre;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Genre {
    @Setter(AccessLevel.NONE)
    private Long genreId;
    private String genreName;
    private String genreDescription;
}
