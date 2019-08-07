package ua.com.epam.entity;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Fault {
    @EqualsAndHashCode.Exclude
    private String timeStamp;
    private int statusCode;
    private String error;
    private String errorMessage;
}
