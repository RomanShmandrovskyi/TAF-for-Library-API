package ua.com.epam.service.genre;

import lombok.AllArgsConstructor;
import ua.com.epam.core.client.rest.RestClient;
import ua.com.epam.entity.genre.Genre;
import ua.com.epam.service.book.config.custom.GetAuthorRelatedDataArr;
import ua.com.epam.service.config.base.GetObj;
import ua.com.epam.service.config.base.PostArr;
import ua.com.epam.service.config.base.PostObj;
import ua.com.epam.service.config.base.PutObj;
import ua.com.epam.service.config.common.DeleteObjWithForce;
import ua.com.epam.service.config.common.GetObjWithParams;
import ua.com.epam.service.config.common.SearchForObjArr;

import java.util.List;
import java.util.stream.Collectors;

import static ua.com.epam.config.URI.*;

@AllArgsConstructor
public class GenreService {
    private RestClient client;

    public GetObj getGenre(long genreId) {
        return new GetObj(client, String.format(GET_GENRE_SINGLE_OBJ, genreId));
    }

    public GetObj getBookGenre(long bookId) {
        return new GetObj(client, String.format(GET_GENRE_OF_BOOK_OBJ, bookId));
    }

    public GetAuthorRelatedDataArr getAuthorGenres(long authorId) {
        return new GetAuthorRelatedDataArr(client, String.format(GET_ALL_AUTHOR_GENRES_ARR, authorId));
    }

    public GetObjWithParams getAllGenres() {
        return new GetObjWithParams(client, GET_ALL_GENRES_ARR);
    }

    public SearchForObjArr searchForGenres() {
        return new SearchForObjArr(client, SEARCH_FOR_EXISTED_BOOKS_ARR);
    }

    public PostObj postGenre(Genre genre) {
        return new PostObj(client, POST_GENRE_SINGLE_OBJ, genre);
    }

    public PostArr postGenres(List<Genre> genresToPost) {
        List<Object> objs = genresToPost.stream()
                .map(g -> (Object) g)
                .collect(Collectors.toList());
        return new PostArr(client, POST_GENRE_SINGLE_OBJ, objs);
    }

    public PutObj updateGenre(long genreId, Genre genre) {
        return new PutObj(client, String.format(PUT_GENRE_SINGLE_OBJ, genreId), genre);
    }

    public DeleteObjWithForce deleteGenre(long genreId) {
        return new DeleteObjWithForce(client, String.format(DELETE_GENRE_SINGLE_OBJ, genreId));
    }
}