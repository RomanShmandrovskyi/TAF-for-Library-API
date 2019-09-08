package ua.com.epam.book.crud;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ua.com.epam.BaseTest;
import ua.com.epam.entity.author.Author;
import ua.com.epam.entity.book.Book;
import ua.com.epam.entity.genre.Genre;

import static ua.com.epam.service.config.JsonKeys.BOOK_DESCRIPTION;

@Test(description = "CRUD with Book")
public class CRUDBookTest extends BaseTest {
    private Author a = testData.authors().getRandomOne();
    private Genre g = testData.genres().getRandomOne();
    private Book b = testData.books().getRandomOne();

    private String updProp = "blabla";

    @BeforeClass
    public void preconditions() {
        services.author().postAuthor(a).perform();
        services.genre().postGenre(g).perform();
    }

    @Test(description = "Post single Book")
    public void postBook() {
        services.book().postBook(b, a.getAuthorId(), g.getGenreId()).perform();
        validateThat.responseCode().is(201);
        validateThat.responseEntity().equalsTo(b, Book.class);
    }

    @Test(description = "Get single Book", dependsOnMethods = "postBook")
    public void getBook() {
        services.book().getBook(b.getBookId()).perform();
        validateThat.responseCode().is(200);
        validateThat.responseEntity().equalsTo(b, Book.class);
    }

    @Test(description = "Update single Book", dependsOnMethods = "getBook")
    public void updateBook() {
        b.setBookDescription(updProp);

        services.book().updateBook(b.getBookId(), b).perform();
        validateThat.responseCode().is(200);
        validateThat.responseEntity().propertyEqualsTo(BOOK_DESCRIPTION, updProp);
    }

    @Test(description = "Delete single Book", dependsOnMethods = "updateBook")
    public void deleteBook() {
        services.book().deleteBook(b.getBookId()).perform();
        validateThat.responseCode().is(204);
        validateThat.responseEntity().isAbsent();
    }

    @AfterClass(alwaysRun = true)
    public void clean() {
        clean.authors();
        clean.genres();
    }
}