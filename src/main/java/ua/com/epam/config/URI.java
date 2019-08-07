package ua.com.epam.config;

public interface URI {
    //Author
    String GET_AUTHOR_SINGLE_OBJ = "/api/library/author/%s";
    String GET_AUTHOR_OF_BOOK_OBJ = "/api/library/book/%s/author";
    String GET_ALL_AUTHORS_ARR = "/api/library/authors";
    String POST_AUTHOR_SINGLE_OBJ = "/api/library/author/new";
    String PUT_AUTHOR_SINGLE_OBJ = "/api/library/author/%s/update";
    String DELETE_AUTHOR_SINGLE_OBJ = "/api/library/author/%s/delete";

    //Genre
    String GET_GENRE_OF_BOOK_OBJ = "/api/library/book/%s/genre";
    String GET_GENRE_SINGLE_OBJ = "/api/library/genre/%s";
    String GET_ALL_GENRES_ARR = "/api/library/genres";
    String POST_GENRE_SINGLE_OBJ = "/api/library/genre/new";
    String PUT_GENRE_SINGLE_OBJ = "/api/library/genre/%s/update";
    String DELETE_GENRE_SINGLE_OBJ = "/api/library/genre/%s/delete";

    //Book
    String GET_BOOK_SINGLE_OBJ = "/api/library/book/%s";
    String GET_ALL_BOOKS_ARR = "/api/library/books";
    String GET_ALL_BOOKS_IN_GENRE = "/api/library/genre/%s/books";
    String GET_ALL_BOOKS_OF_AUTHOR = "/api/library/author/%s/books";
    String GET_ALL_BOOKS_IN_GENRE_OF_AUTHOR = "/api/library/author/%s/genre/%s/books";
    String POST_BOOK_SINGLE_OBJ = "/api/library/book/%s/%s/new";
    String PUT_BOOK_SINGLE_OBJ = "/api/library/book/%s/update";
    String DELETE_BOOK_SINGLE_OBJ = "/api/library/book/%s/delete";
}
