package ua.com.epam.utils;

import lombok.AllArgsConstructor;
import ua.com.epam.core.client.rest.RestClient;
import ua.com.epam.utils.validator.ArrayValidator;
import ua.com.epam.utils.validator.EntityValidator;
import ua.com.epam.utils.validator.StatusCodeValidator;

@AllArgsConstructor
public class ValidatorFactory {
    private RestClient client;

    public StatusCodeValidator responseCode() {
        return new StatusCodeValidator(client);
    }

    public EntityValidator responseEntity() {
        return new EntityValidator(client);
    }

    public ArrayValidator responseArray() {
        return new ArrayValidator(client);
    }
}
