package ua.com.epam.service.config.base;

import lombok.NoArgsConstructor;
import org.apache.http.client.utils.URIBuilder;
import ua.com.epam.core.client.rest.RestClient;

import java.util.List;

@NoArgsConstructor
public class PostArr {
    private RestClient client;
    private List<Object> listToPost;
    protected URIBuilder url;

    public PostArr(RestClient client, String baseUrl, List<Object> listToPost) {
        this.url = new URIBuilder().setPath(baseUrl);
        this.listToPost = listToPost;
        this.client = client;
    }

    public void perform() {
        listToPost.forEach(s -> client.post(url, s));
    }
}
