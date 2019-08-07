package ua.com.epam.entity.author.nested;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Birth {
    private LocalDate date;
    private String country;
    private String city;
}
