package ua.com.epam.service.config.base;

import lombok.NoArgsConstructor;
import org.apache.http.client.utils.URIBuilder;
import ua.com.epam.core.client.rest.RestClient;

@NoArgsConstructor
public class PutObj {
    private RestClient client;
    private Object objToPut;
    protected URIBuilder url;

    public PutObj(RestClient client, String baseUrl, Object objToPut) {
        this.url = new URIBuilder().setPath(baseUrl);
        this.objToPut = objToPut;
        this.client = client;
    }

    public void perform() {
        client.put(url, objToPut);
    }
}