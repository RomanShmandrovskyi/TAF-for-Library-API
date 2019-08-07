package ua.com.epam.service;

import lombok.AllArgsConstructor;
import ua.com.epam.core.client.rest.RestClient;
import ua.com.epam.service.author.AuthorService;
import ua.com.epam.service.book.BookService;
import ua.com.epam.service.genre.GenreService;

@AllArgsConstructor
public class ServiceFactory {
    private RestClient client;

    public AuthorService author() {
        return new AuthorService(client);
    }

    public GenreService genre() {
        return new GenreService(client);
    }

    public BookService book() {
        return new BookService(client);
    }
}
