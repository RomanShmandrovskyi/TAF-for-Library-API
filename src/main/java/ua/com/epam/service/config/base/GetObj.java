package ua.com.epam.service.config.base;

import lombok.NoArgsConstructor;
import org.apache.http.client.utils.URIBuilder;
import ua.com.epam.core.client.rest.RestClient;

@NoArgsConstructor
public class GetObj {
    private RestClient client;
    protected URIBuilder url;

    public GetObj(RestClient client, String baseUrl) {
        url = new URIBuilder().setPath(baseUrl);
        this.client = client;
    }

    public void perform() {
        client.get(url);
    }
}