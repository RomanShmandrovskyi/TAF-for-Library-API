package ua.com.epam.utils.validator;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import ua.com.epam.core.client.rest.RestClient;
import ua.com.epam.utils.helpers.StatusCode;

@Log4j
@AllArgsConstructor
public class StatusCodeValidator {
    private RestClient client;

    public void is(int expected) {
        int actual = client.getResponse().getStatusCode();

        if (actual != expected) {
            log.error("!!! Invalid Status Code !!! Expected: " + expected + ", but found " + actual);
            throw new AssertionError();
        }

        log.info("Status code is valid: " + expected + " - " + StatusCode.resolve(expected).getReasonPhrase() + "!");
    }

    public void is1xxInformational() {
        int statusCode = client.getResponse().getStatusCode();
        if (!StatusCode.resolve(statusCode).is1xxInformational()) {
            log.error("!!! Expected status code: " + statusCode
                    + " not belongs to Informational HTTP Status codes group !!!");
            throw new AssertionError();
        }

        log.info("Status code: " + statusCode + " belongs to Informational HTTP Status codes group!");
    }

    public void is2xxSuccessful() {
        int statusCode = client.getResponse().getStatusCode();
        if (!StatusCode.resolve(statusCode).is2xxSuccessful()) {
            log.error("!!! Expected status code: " + statusCode
                    + " not belongs to Successful HTTP Status codes group !!!");
            throw new AssertionError();
        }

        log.info("Status code: " + statusCode + " belongs to Successful HTTP Status codes group!");
    }

    public void is3xxRedirection() {
        int statusCode = client.getResponse().getStatusCode();
        if (!StatusCode.resolve(statusCode).is3xxRedirection()) {
            log.error("!!! Expected status code: " + statusCode
                    + " not belongs to Redirection HTTP Status codes group !!!");
            throw new AssertionError();
        }

        log.info("Status code: " + statusCode + " belongs to Redirection HTTP Status codes group!");
    }

    public void is4xxClientError() {
        int statusCode = client.getResponse().getStatusCode();
        if (!StatusCode.resolve(statusCode).is4xxClientError()) {
            log.error("!!! Expected status code: " + statusCode
                    + " not belongs to Client Error HTTP Status codes group !!!");
            throw new AssertionError();
        }

        log.info("Status code: " + statusCode + " belongs to Client Error HTTP Status codes group!");
    }

    public void is5xxServerError() {
        int statusCode = client.getResponse().getStatusCode();
        if (!StatusCode.resolve(statusCode).is5xxServerError()) {
            log.error("!!! Expected status code: " + statusCode
                    + " not belong to Server Error HTTP Status codes group !!!");
            throw new AssertionError();
        }

        log.info("Status code: " + statusCode + " belongs to Server Error HTTP Status codes group!");
    }

    public void isError() {
        int statusCode = client.getResponse().getStatusCode();
        if (!StatusCode.resolve(statusCode).isError()) {
            log.error("!!! Status code " + statusCode + " is not from error HTTP Group codes !!!");
            throw new AssertionError();
        }

        log.info("Status code: " + statusCode + " belongs to ERROR HTTP Groups codes!");
    }
}
