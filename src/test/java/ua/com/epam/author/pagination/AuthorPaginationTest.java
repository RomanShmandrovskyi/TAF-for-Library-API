package ua.com.epam.author.pagination;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.com.epam.BaseTest;
import ua.com.epam.entity.author.Author;

import java.util.List;

import static ua.com.epam.service.config.JsonKeys.AUTHOR_ID;
import static ua.com.epam.service.config.QueryParameters.DESCENDING;

@Test
public class AuthorPaginationTest extends BaseTest {

    private List<Author> authors = testData.authors().getSorted(AUTHOR_ID, DESCENDING, 10);

    @BeforeMethod
    public void fillByAuthors() {
        services.author().postAuthors(authors).perform();
    }

    @Test
    public void paginateAuthors() {
        services.author().getAllAuthors()
                .orderType(DESCENDING)
                .page(2)
                .countOnPage(2)
                .perform();

        validateThat.responseCode().is(200);
        validateThat.responseArray()
                .isPaginated(2, 2, authors, Author.class)
                .and()
                .sizeIs(2);
    }

    @AfterClass(alwaysRun = true)
    public void cleanAuthors() {
        clean.authors();
    }
}