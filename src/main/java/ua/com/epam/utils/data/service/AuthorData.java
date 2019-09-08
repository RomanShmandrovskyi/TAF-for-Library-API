package ua.com.epam.utils.data.service;

import lombok.extern.log4j.Log4j;
import ua.com.epam.entity.author.Author;
import ua.com.epam.entity.author.nested.Birth;
import ua.com.epam.entity.author.nested.Name;
import ua.com.epam.utils.data.BaseData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.com.epam.service.config.JsonKeys.*;
import static ua.com.epam.utils.helpers.SqlQuery.*;

@Log4j
public class AuthorData extends BaseData {

    public Author getRandomOne() {
        log.info("Try to find one random Author...");
        execute(String.format(SELECT_RANDOM_ONE, dp.dbName(), AUTHOR));
        Author a = new Author();
        try {
            if (!resultSet.next()) {
                log.error("No one author was found! Author table is empty!");
            } else {
                a = mapResultSetObjToAuthor(resultSet);
                log.info("Author with authorId = " + a.getAuthorId() + " was found!");
            }
        } catch (SQLException e) {
            log.error("DB access error occurs or method is called on a closed ResultSet!!!");
            e.printStackTrace();
        }

        close();
        return a;
    }

    //return 10 authors sortedBy authorId in ascending order
    public List<Author> getDefaultAuthors() {
        log.info("Try to find first 10 authors...");
        execute(String.format(SELECT_DEFAULTS, dp.dbName(), AUTHOR, AUTHOR_ID));
        List<Author> authors = new ArrayList<>();
        try {
            int i = 0;
            if (!resultSet.next()) {
                log.error("No one author was found! Author table is empty!");
            } else {
                do {
                    authors.add(mapResultSetObjToAuthor(resultSet));
                    i++;
                } while (resultSet.next());
                log.info("Found " + i + " authors successfully!");
            }
        } catch (SQLException e) {
            log.error("DB access error occurs or method is called on a closed ResultSet!!!");
            e.printStackTrace();
        }

        close();
        return authors;
    }

    public List<Author> getSorted(String sortBy, String order, int... count) {
        int limit = count.length == 0 ? 10 : count[0];

        log.info("Try to find first " + limit + " authors sorted by " + sortBy + " in " + order + " order...");
        execute(String.format(SELECT_CUSTOMS, dp.dbName(), AUTHOR, sortBy, order, limit));
        List<Author> authors = new ArrayList<>();

        try {
            int i = 0;
            if (!resultSet.next()) {
                log.error("No one author was found! Author table is empty!");
            } else {
                do {
                    authors.add(mapResultSetObjToAuthor(resultSet));
                    i++;
                } while (resultSet.next());
                log.info("Found " + i + " authors successfully!\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        close();
        return authors;
    }

    private Author mapResultSetObjToAuthor(ResultSet rs) throws SQLException {
        return new Author(
                rs.getLong(AUTHOR_ID),
                new Name(
                        rs.getString(AUTHOR_FIRST_NAME),
                        rs.getString(AUTHOR_SECOND_NAME)),
                rs.getString(AUTHOR_NATIONALITY),
                new Birth(
                        rs.getDate(AUTHOR_BIRTH_DATE).toLocalDate(),
                        rs.getString(AUTHOR_BIRTH_COUNTRY),
                        rs.getString(AUTHOR_BIRTH_CITY)
                ),
                rs.getString(AUTHOR_DESCRIPTION)
        );
    }
}
