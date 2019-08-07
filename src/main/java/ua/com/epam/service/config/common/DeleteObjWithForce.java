package ua.com.epam.service.config.common;

import lombok.NoArgsConstructor;
import ua.com.epam.core.client.rest.RestClient;
import ua.com.epam.service.config.base.DeleteObj;

import static ua.com.epam.service.config.QueryParameters.FORCIBLY_PARAM_NAME;

@NoArgsConstructor
public class DeleteObjWithForce extends DeleteObj {

    public DeleteObjWithForce(RestClient client, String baseUrl) {
        super(client, baseUrl);
    }

    public DeleteObjWithForce withForce() {
        url.setParameter(FORCIBLY_PARAM_NAME, String.valueOf(true));
        return this;
    }
}
