package ua.com.epam.author.crud;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import ua.com.epam.BaseTest;
import ua.com.epam.entity.author.Author;

import static ua.com.epam.service.config.JsonKeys.AUTHOR_NATIONALITY;

@Test(description = "CRUD operations with Author")
public class CRUDAuthorTest extends BaseTest {
    private Author a = testData.authors().getRandomOne();

    private String updProp = "blabla";

    @Test(description = "Post one Author")
    public void createAuthor() {
        services.author().postAuthor(a).perform();
        validateThat.responseCode().is(201);
        validateThat.responseEntity().equalsTo(a, Author.class);
    }

    @Test(description = "Get one Author", dependsOnMethods = "createAuthor")
    public void getAuthor() {
        services.author().getAuthor(a.getAuthorId()).perform();
        validateThat.responseCode().is(200);
        validateThat.responseEntity().equalsTo(a, Author.class);
    }

    @Test(description = "Update one Author", dependsOnMethods = "getAuthor")
    public void updateAuthor() {
        a.setNationality(updProp);

        services.author().updateAuthor(a.getAuthorId(), a).perform();
        validateThat.responseCode().is(200);
        validateThat.responseEntity().propertyEqualsTo(AUTHOR_NATIONALITY, updProp);
    }

    @Test(description = "Delete one Author", dependsOnMethods = "updateAuthor")
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