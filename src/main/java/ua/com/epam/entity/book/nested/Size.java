package ua.com.epam.entity.book.nested;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Size {
    private Double height;
    private Double width;
    private Double length;
}
