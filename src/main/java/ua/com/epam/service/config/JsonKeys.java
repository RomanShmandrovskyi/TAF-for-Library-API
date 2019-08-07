package ua.com.epam.service.config;

public interface JsonKeys {
    String AUTHOR_ID = "authorId";
    String AUTHOR_FIRST_NAME = "authorName.first";
    String AUTHOR_SECOND_NAME = "authorName.second";
    String AUTHOR_BIRTH_DATE = "birth.date";
    String AUTHOR_BIRTH_COUNTRY = "birth.country";
    String AUTHOR_BIRTH_CITY = "birth.city";
    String AUTHOR_DESCRIPTION = "authorDescription";
    String AUTHOR_NATIONALITY = "nationality";

    String GENRE_ID = "genreId";
    String GENRE_NAME = "genreName";
    String GENRE_DESCRIPTION = "genreDescription";

    String BOOK_ID = "bookId";
    String BOOK_NAME = "bookName";
    String BOOK_LANGUAGE = "bookLanguage";
    String BOOK_DESCRIPTION = "bookDescription";
    String BOOK_PAGE_COUNT = "additional.pageCount";
    String BOOK_HEIGHT = "additional.size.height";
    String BOOK_WIDTH = "additional.size.width";
    String BOOK_LENGTH = "additional.size.length";
    String BOOK_PUBLICATION_YEAR = "publicationYear";
}