package ua.com.epam.service.config.base;

import lombok.NoArgsConstructor;
import org.apache.http.client.utils.URIBuilder;
import ua.com.epam.core.client.rest.RestClient;

@NoArgsConstructor
public class PostObj {
    private RestClient client;
    private Object objToPost;
    protected URIBuilder url;

    public PostObj(RestClient client, String baseUrl, Object objToPost) {
        this.url = new URIBuilder().setPath(baseUrl);
        this.objToPost = objToPost;
        this.client = client;
    }

    public void perform() {
        client.post(url, objToPost);
    }
}