package ua.com.epam.config;

public interface URI {
    //Author
    String GET_AUTHOR_SINGLE_OBJ = "/api/library/author/%s";
    String GET_AUTHOR_OF_BOOK_OBJ = "/api/library/book/%s/author";
    String GET_ALL_AUTHORS_ARR = "/api/library/authors";
    String SEARCH_FOR_EXISTED_AUTHORS_ARR = "/api/library/authors/search";
    String GET_ALL_AUTHORS_IN_GENRE_ARR = "/api/library/genre/%s/authors";
    String POST_AUTHOR_SINGLE_OBJ = "/api/library/author";
    String PUT_AUTHOR_SINGLE_OBJ = "/api/library/author/%s";
    String DELETE_AUTHOR_SINGLE_OBJ = "/api/library/author/%s";

    //Genre
    String GET_GENRE_OF_BOOK_OBJ = "/api/library/book/%s/genre";
    String GET_GENRE_SINGLE_OBJ = "/api/library/genre/%s";
    String GET_ALL_AUTHOR_GENRES_ARR = "/api/library/author/%s/genres";
    String GET_ALL_GENRES_ARR = "/api/library/genres";
    String SEARCH_FOR_EXISTED_GENRES_ARR = "/api/library/genres/search";
    String POST_GENRE_SINGLE_OBJ = "/api/library/genre";
    String PUT_GENRE_SINGLE_OBJ = "/api/library/genre/%s";
    String DELETE_GENRE_SINGLE_OBJ = "/api/library/genre/%s";

    //Book
    String GET_BOOK_SINGLE_OBJ = "/api/library/book/%s";
    String GET_ALL_BOOKS_ARR = "/api/library/books";
    String SEARCH_FOR_EXISTED_BOOKS_ARR = "/api/library/books/search";
    String GET_ALL_BOOKS_IN_GENRE = "/api/library/genre/%s/books";
    String GET_ALL_BOOKS_OF_AUTHOR = "/api/library/author/%s/books";
    String GET_ALL_BOOKS_IN_GENRE_OF_AUTHOR = "/api/library/author/%s/genre/%s/books";
    String POST_BOOK_SINGLE_OBJ = "/api/library/book/%s/%s";
    String PUT_BOOK_SINGLE_OBJ = "/api/library/book/%s";
    String DELETE_BOOK_SINGLE_OBJ = "/api/library/book/%s";
}