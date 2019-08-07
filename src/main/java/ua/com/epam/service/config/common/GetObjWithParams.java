package ua.com.epam.service.config.common;

import lombok.NoArgsConstructor;
import ua.com.epam.core.client.rest.RestClient;
import ua.com.epam.service.config.base.GetObj;

import static ua.com.epam.service.config.QueryParameters.*;

@NoArgsConstructor
public class GetObjWithParams extends GetObj {
    public GetObjWithParams(RestClient client, String url) {
        super(client, url);
    }

    public GetObjWithParams orderType(String order) {
        url.setParameter(ORDER_TYPE_PARAM_NAME, order);
        return this;
    }

    public GetObjWithParams page(int pageNum) {
        url.setParameter(PAGE_PARAM_NAME, String.valueOf(pageNum));
        return this;
    }

    public GetObjWithParams disablePagination() {
        url.setParameter(PAGINATION_PARAM_NAME, String.valueOf(false));
        return this;
    }

    public GetObjWithParams countOnPage(int count) {
        url.setParameter(SIZE_PARAM_NAME, String.valueOf(count));
        return this;
    }

    public GetObjWithParams sortBy(String sortBy) {
        url.setParameter(SORT_BY_PARAM_NAME, sortBy);
        return this;
    }
}
