package ua.com.epam.service.book.config.custom;

import ua.com.epam.core.client.rest.RestClient;
import ua.com.epam.service.config.base.GetObj;

import static ua.com.epam.service.config.QueryParameters.ORDER_TYPE_PARAM_NAME;
import static ua.com.epam.service.config.QueryParameters.SORT_BY_PARAM_NAME;

public class GetBooksOfAuthorArr extends GetObj {

    public GetBooksOfAuthorArr(RestClient client, String baseUrl) {
        super(client, baseUrl);
    }

    public GetBooksOfAuthorArr orderType(String order) {
        url.setParameter(ORDER_TYPE_PARAM_NAME, order);
        return this;
    }

    public GetBooksOfAuthorArr sortBy(String sortBy) {
        url.setParameter(SORT_BY_PARAM_NAME, sortBy);
        return this;
    }
}
