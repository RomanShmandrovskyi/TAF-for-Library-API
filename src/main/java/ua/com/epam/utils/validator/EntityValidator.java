package ua.com.epam.utils.validator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.log4j.Log4j;
import ua.com.epam.core.client.rest.RestClient;
import ua.com.epam.utils.helpers.LocalDateAdapter;

import java.time.LocalDate;

@Log4j
public class EntityValidator {
    private RestClient client;
    private Gson g;

    public EntityValidator(RestClient client) {
        this.client = client;
        this.g = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
    }

    public <T> void equalsTo(T expectedObj, Class<T> clazz) {
        String responseEntity = client.getResponse().getBody();
        T parsed = g.fromJson(responseEntity, clazz);

        if (!expectedObj.equals(parsed)) {
            log.error("!!! Objects are different !!!");
            throw new AssertionError();
        }

        log.info("Response object is equal to expected!");
    }

    public void propertyEqualsTo(String propertyName, String propertyValue) {
        String respJson = client.getResponse().getBody();
        String actualValue = JsonPath.read(respJson, "$." + propertyName).toString();

        if (!propertyValue.equals(actualValue)) {
            log.error("!!! Property by key \'" + propertyName + "\' mismatch !!! " +
                    "Expected: " + propertyValue + ", but found " + actualValue);
            throw new AssertionError();
        }

        log.info("Property value is equal to expected!");
    }

    public void isAbsent() {
        String body = client.getResponse().getBody();

        if (!body.isEmpty()) {
            log.error("!!! Response body is not absent !!! Actual result: " + body);
        }

        log.info("Response body is absent!");
    }
}
