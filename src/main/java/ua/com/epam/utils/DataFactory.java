package ua.com.epam.utils;

import ua.com.epam.utils.data.service.AuthorData;
import ua.com.epam.utils.data.service.BookData;
import ua.com.epam.utils.data.service.GenreData;

public class DataFactory {

    public AuthorData authors() {
        return new AuthorData();
    }

    public GenreData genres() {
        return new GenreData();
    }

    public BookData books() {
        return new BookData();
    }
}