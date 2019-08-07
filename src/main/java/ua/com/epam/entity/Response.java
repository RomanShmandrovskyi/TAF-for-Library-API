package ua.com.epam.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Response {
    private int statusCode;
    private String body;
}
