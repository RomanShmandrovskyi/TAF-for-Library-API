package ua.com.epam.service.author;

import lombok.AllArgsConstructor;
import ua.com.epam.core.client.rest.RestClient;
import ua.com.epam.entity.author.Author;
import ua.com.epam.service.config.base.GetObj;
import ua.com.epam.service.config.base.PostArr;
import ua.com.epam.service.config.base.PostObj;
import ua.com.epam.service.config.base.PutObj;
import ua.com.epam.service.config.common.*;

import java.util.List;
import java.util.stream.Collectors;

import static ua.com.epam.config.URI.*;

@AllArgsConstructor
public class AuthorService {
    private RestClient client;

    public GetObj getAuthor(long authorId) {
        return new GetObj(client, String.format(GET_AUTHOR_SINGLE_OBJ, authorId));
    }

    public GetObj getAuthorOfBook(long bookId) {
        return new GetObj(client, String.format(GET_AUTHOR_OF_BOOK_OBJ, bookId));
    }

    public GetObjWithParams getAllAuthors() {
        return new GetObjWithParams(client, GET_ALL_AUTHORS_ARR);
    }

    public SearchForObjArr searchForAuthors() {
        return new SearchForObjArr(client, SEARCH_FOR_EXISTED_AUTHORS_ARR);
    }

    public GetObjWithParams getAllAuthorsInGenre(long genreId) {
        return new GetObjWithParams(client, String.format(GET_ALL_AUTHORS_IN_GENRE_ARR, genreId));
    }

    public PostObj postAuthor(Author author) {
        return new PostObj(client, POST_AUTHOR_SINGLE_OBJ, author);
    }

    public PostArr postAuthors(List<Author> authorsToPost) {
        List<Object> objs = authorsToPost.stream()
                .map(a -> (Object) a)
                .collect(Collectors.toList());
        return new PostArr(client, POST_AUTHOR_SINGLE_OBJ, objs);
    }

    public PutObj updateAuthor(long authorId, Author updated) {
        return new PutObj(client, String.format(PUT_AUTHOR_SINGLE_OBJ, authorId), updated);
    }

    public DeleteObjWithForce deleteAuthor(long authorId) {
        return new DeleteObjWithForce(client, String.format(DELETE_AUTHOR_SINGLE_OBJ, authorId));
    }
}