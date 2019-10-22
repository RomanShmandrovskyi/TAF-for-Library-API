package ua.com.epam.service.config.common;

import ua.com.epam.core.client.rest.RestClient;
import ua.com.epam.service.config.base.GetObj;

import static ua.com.epam.service.config.QueryParameters.SEARCH_QUERY;

public class SearchForObjArr extends GetObj {

    public SearchForObjArr(RestClient client, String baseUrl) {
        super(client, baseUrl);
    }

    public SearchForObjArr query(String searchQuery) {
        url.addParameter(SEARCH_QUERY, searchQuery);
        return this;
    }
}
