package ua.com.epam.service;

import com.jayway.jsonpath.JsonPath;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.json.JSONArray;
import ua.com.epam.core.client.rest.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ua.com.epam.service.config.JsonKeys.*;

@Log4j
@AllArgsConstructor
public class CleanUpService {
    private RestClient client;
    private ServiceFactory services;

    public void authors() {
        System.out.println("\n=======================================================================");
        log.info("Start to get all authors...");

        services.author().getAllAuthors()
                .disablePagination()
                .perform();

        List<Long> authorIds = getObjectIdsToDelete(AUTHOR_ID, client.getResponse().getBody());
        int size = authorIds.size();

        if (size != 0) {
            log.info(size + " authors found!");
            log.info("Start to delete authors...");
            authorIds.forEach(id -> services.author()
                    .deleteAuthor(id)
                    .withForce()
                    .perform());

            log.info("All Authors was successfully cleaned up!");
            return;
        }

        log.info("Nothing to delete! Author table is empty!");
    }

    public void genres() {
        System.out.println("\n=======================================================================");
        log.info("Start to get all genres...");

        services.genre().getAllGenres()
                .disablePagination()
                .perform();

        List<Long> genreIds = getObjectIdsToDelete(GENRE_ID, client.getResponse().getBody());
        int size = genreIds.size();

        if (size != 0) {
            log.info(size + " genres found!");
            log.info("Start to delete genres...");
            genreIds.forEach(id -> services.genre()
                    .deleteGenre(id)
                    .withForce()
                    .perform());

            log.info("All Genres was successfully cleaned up!");
            return;
        }

        log.info("Nothing to delete! Genre table is empty!");
    }

    public void books() {
        System.out.println("\n=======================================================================");
        log.info("Start to get all books...");

        services.book().getAllBooks()
                .disablePagination()
                .perform();

        List<Long> booksIds = getObjectIdsToDelete(BOOK_ID, client.getResponse().getBody());
        int size = booksIds.size();

        if (size != 0) {
            log.info(size + " books found!");
            log.info("Start to delete books...");
            booksIds.forEach(id -> services.genre()
                    .deleteGenre(id)
                    .withForce()
                    .perform());

            log.info("All Books was successfully cleaned up!");
            return;
        }

        log.info("Nothing to delete! Book table is empty!");
    }

    private List<Long> getObjectIdsToDelete(String keyName, String json) {
        JSONArray a = new JSONArray(json);

        if (a.length() == 0) return new ArrayList<>();

        return a.toList()
                .stream()
                .map(o -> Long.valueOf(JsonPath.read(o, "$." + keyName).toString()))
                .collect(Collectors.toList());
    }
}
