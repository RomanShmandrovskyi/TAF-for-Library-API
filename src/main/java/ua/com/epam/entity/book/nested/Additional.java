package ua.com.epam.entity.book.nested;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Additional {
    private Integer pageCount;
    private Size size;
}
