package ua.com.epam.utils.data.service;

import lombok.extern.log4j.Log4j;
import ua.com.epam.entity.book.Book;
import ua.com.epam.entity.book.nested.Additional;
import ua.com.epam.entity.book.nested.Size;
import ua.com.epam.utils.data.BaseData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.com.epam.service.config.JsonKeys.*;
import static ua.com.epam.utils.helpers.SqlQuery.*;

@Log4j
public class BookData extends BaseData {

    public Book getRandomOne() {
        log.info("Try to find one random Book...");
        execute(String.format(SELECT_RANDOM_ONE, dp.dbName(), BOOK));
        Book b = new Book();
        try {
            if (!resultSet.next()) {
                log.error("No one book was found! Book table is empty!");
            } else {
                b = mapResultSetObjToBook(resultSet);
                log.info("Book with bookId = " + b.getBookId() + " was found!");
            }
        } catch (SQLException e) {
            log.error("DB access error occurs or method is called on a closed ResultSet!!!");
            e.printStackTrace();
        }

        close();
        return b;
    }

    public List<Book> getDefaulBooks() {
        log.info("Try to find first 10 books...");
        execute(String.format(SELECT_DEFAULTS, dp.dbName(), BOOK, BOOK_ID));
        List<Book> books = new ArrayList<>();
        try {
            int i = 0;
            if (!resultSet.next()) {
                log.error("No one book was found! Book table is empty!");
            } else {
                do {
                    books.add(mapResultSetObjToBook(resultSet));
                    i++;
                } while (resultSet.next());
                log.info("Found " + i + " books successfully!");
            }
        } catch (SQLException e) {
            log.error("DB access error occurs or method is called on a closed ResultSet!!!");
            e.printStackTrace();
        }

        close();
        return books;
    }

    public List<Book> getSorted(String sortBy, String order, int... count) {
        int limit = count.length == 0 ? 10 : count[0];

        log.info("Try to find first " + limit + " books sorted by " + sortBy + " in " + order + " order...");
        execute(String.format(SELECT_CUSTOMS, dp.dbName(), BOOK, sortBy, order, limit));
        List<Book> books = new ArrayList<>();

        try {
            int i = 0;
            if (!resultSet.next()) {
                log.error("No one book was found! Books table is empty!");
            } else {
                do {
                    books.add(mapResultSetObjToBook(resultSet));
                    i++;
                } while (resultSet.next());
                log.info("Found " + i + " books successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        close();
        return books;
    }

    private Book mapResultSetObjToBook(ResultSet rs) throws SQLException {
        return new Book(
                rs.getLong(BOOK_ID),
                rs.getString(BOOK_NAME),
                rs.getString(BOOK_LANGUAGE),
                rs.getString(BOOK_DESCRIPTION),
                new Additional(
                        rs.getInt(BOOK_PAGE_COUNT),
                        new Size(
                                rs.getDouble(BOOK_HEIGHT),
                                rs.getDouble(BOOK_WIDTH),
                                rs.getDouble(BOOK_LENGTH)
                        )
                ),
                rs.getInt(BOOK_PUBLICATION_YEAR)
        );
    }
}
