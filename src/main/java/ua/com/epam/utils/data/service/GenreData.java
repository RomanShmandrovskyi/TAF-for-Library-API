package ua.com.epam.utils.data.service;

import lombok.extern.log4j.Log4j;
import ua.com.epam.entity.genre.Genre;
import ua.com.epam.utils.data.BaseData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.com.epam.service.config.JsonKeys.*;
import static ua.com.epam.utils.helpers.SqlQuery.*;

@Log4j
public class GenreData extends BaseData {

    public Genre getRandomOne() {
        log.info("Try to find one random Genre...");
        execute(String.format(SELECT_RANDOM_ONE, dp.dbName(), GENRE));
        Genre g = new Genre();
        try {
            if (!resultSet.next()) {
                log.error("No one genre was found! Genre table is empty!");
            } else {
                g = mapResultSetObjToGenre(resultSet);
                log.info("Genre with genreId = " + g.getGenreId() + " was found!\n");
            }
        } catch (SQLException e) {
            log.error("DB access error occurs or method is called on a closed ResultSet!!!");
            e.printStackTrace();
        }

        close();
        return g;
    }

    public List<Genre> getDefaultGenres() {
        log.info("Try to find first 10 genres...");
        execute(String.format(SELECT_DEFAULTS, dp.dbName(), GENRE, GENRE_ID));
        List<Genre> genres = new ArrayList<>();
        try {
            int i = 0;
            if (!resultSet.next()) {
                log.error("No one genre was found! Genre table is empty!");
            } else {
                do {
                    genres.add(mapResultSetObjToGenre(resultSet));
                    i++;
                } while (resultSet.next());
                log.info("Found " + i + " genres successfully!");
            }
        } catch (SQLException e) {
            log.error("DB access error occurs or method is called on a closed ResultSet!!!");
            e.printStackTrace();
        }

        close();
        return genres;
    }

    public List<Genre> getSorted(String sortBy, String order, int... count) {
        int limit = count.length == 0 ? 10 : count[0];

        log.info("Try to find first " + limit + " genres sorted by " + sortBy + " in " + order + " order...");
        execute(String.format(SELECT_CUSTOMS, dp.dbName(), GENRE, sortBy, order, limit));
        List<Genre> genres = new ArrayList<>();

        try {
            int i = 0;
            if (!resultSet.next()) {
                log.error("No one genre was found! Genres table is empty!");
            } else {
                do {
                    genres.add(mapResultSetObjToGenre(resultSet));
                    i++;
                } while (resultSet.next());
                log.info("Found " + i + " genres successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        close();
        return genres;
    }

    private Genre mapResultSetObjToGenre(ResultSet rs) throws SQLException {
        return new Genre(
                rs.getLong(GENRE_ID),
                rs.getString(GENRE_NAME),
                rs.getString(GENRE_DESCRIPTION)
        );
    }
}
