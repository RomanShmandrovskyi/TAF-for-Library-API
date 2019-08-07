package ua.com.epam.crud;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import ua.com.epam.BaseTest;
import ua.com.epam.entity.author.Author;

import static ua.com.epam.service.config.JsonKeys.AUTHOR_NATIONALITY;

@Test
public class CRUDAuthorTest extends BaseTest {
    private Author a = testData.authors().getRandomOne();

    @Test
    public void createAuthor() {
        services.author().postAuthor(a).perform();
        validateThat.responseCode().is(201);
        validateThat.responseEntity().equalsTo(a, Author.class);
    }

    @Test(dependsOnMethods = "createAuthor")
    public void getAuthor() {
        services.author().getAuthor(a.getAuthorId()).perform();
        validateThat.responseCode().is(200);
        validateThat.responseEntity().equalsTo(a, Author.class);
    }

    @Test(dependsOnMethods = "getAuthor")
    public void updateAuthor() {
        a.setNationality("blabla");

        services.author().updateAuthor(a.getAuthorId(), a).perform();
        validateThat.responseCode().is(200);
        validateThat.responseEntity().propertyEqualsTo(AUTHOR_NATIONALITY, "blabla");
    }

    @Test(dependsOnMethods = "updateAuthor")
    public void deleteAuthor() {
        services.author().deleteAuthor(a.getAuthorId()).perform();
        validateThat.responseCode().is(204);
        validateThat.responseEntity().isAbsent();
    }

    @AfterClass(alwaysRun = true)
    public void clean() {
        clean.authors();
    }
}
