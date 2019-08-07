package ua.com.epam.core.client.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.extern.log4j.Log4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import ua.com.epam.config.DataProp;
import ua.com.epam.entity.Response;
import ua.com.epam.utils.helpers.LocalDateAdapter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

@Log4j
public class RestClient {
    private HttpClient client = HttpClientBuilder.create().build();
    private Response response;
    private DataProp props = new DataProp();
    private Gson g = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

    public Response getResponse() {
        return this.response;
    }

    public void get(URIBuilder uri) {
        HttpGet request = new HttpGet(setBaseProps(uri));
        request.setHeader(HttpHeaders.ACCEPT, "application/json");

        HttpResponse response = null;
        try {
            log.info("Perform GET request to: " + request.getURI().toString());
            response = client.execute(request);
        } catch (ClientProtocolException e) {
            log.error("HTTP protocol error!");
            e.printStackTrace();
        } catch (IOException e) {
            log.error("Some problems occur or the connection was aborted!");
            e.printStackTrace();
        }

        wrapResponse(response);
    }

    public void post(URIBuilder uri, Object body) {
        HttpPost request = new HttpPost(setBaseProps(uri));
        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");

        String reqB = g.toJson(body);

        HttpResponse response = null;
        try {
            StringEntity bodyToPost = new StringEntity(reqB);
            request.setEntity(bodyToPost);

            log.info("Perform POST request to: " + request.getURI().toString());
            //log.info("Request body:\n" + writePretty(reqB));
            response = client.execute(request);
        } catch (UnsupportedEncodingException e) {
            log.error("The Character Encoding is not supported!");
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            log.error("HTTP protocol error!");
            e.printStackTrace();
        } catch (IOException e) {
            log.error("Some problems occur or the connection was aborted!");
            e.printStackTrace();
        }

        wrapResponse(response);
    }

    public void put(URIBuilder uri, Object body) {
        HttpPut request = new HttpPut(setBaseProps(uri));
        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");

        String reqB = g.toJson(body);

        HttpResponse response = null;
        try {
            StringEntity bodyToPut = new StringEntity(reqB);
            request.setEntity(bodyToPut);

            log.info("Perform PUT request to: " + request.getURI().toString());
            //log.info("Request body:\n" + writePretty(reqB));
            response = client.execute(request);
        } catch (UnsupportedEncodingException e) {
            log.error("The character encoding is not supported!");
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            log.error("HTTP protocol error!");
            e.printStackTrace();
        } catch (IOException e) {
            log.error("Some problems occur or the connection was aborted!");
            e.printStackTrace();
        }

        wrapResponse(response);
    }

    public void delete(URIBuilder uri) {
        HttpDelete request = new HttpDelete(setBaseProps(uri));
        request.setHeader(HttpHeaders.ACCEPT, "application/json");

        HttpResponse response = null;

        try {
            log.info("Perform DELETE request to: " + request.getURI().toString());
            response = client.execute(request);
        } catch (ClientProtocolException e) {
            log.error("HTTP protocol error!");
            e.printStackTrace();
        } catch (IOException e) {
            log.error("Some problems occur or the connection was aborted!");
            e.printStackTrace();
        }

        wrapResponse(response);
    }

    private URI setBaseProps(URIBuilder uri) {
        URI built = null;

        uri.setScheme(props.apiProtocol())
                .setHost(props.apiHost())
                .setPort(props.apiPort());
        try {
            built = uri.build();
        } catch (URISyntaxException e) {
            log.error("String could not be parsed as an URI reference!");
            e.printStackTrace();
        }

        return built;
    }

    private void wrapResponse(HttpResponse response) {
        if (response == null) {
            Assert.fail("Response is empty!");
        }

        int statusCode = response.getStatusLine().getStatusCode();
        HttpEntity entity = response.getEntity();

        if (entity == null) {
            this.response = new Response(statusCode, "");
            return;
        }

        String body = "";

        try {
            body = EntityUtils.toString(entity, "UTF-8");
            //log.info("Response body:\n" + writePretty(body));
        } catch (ParseException e) {
            log.error("Header elements cannot be parsed!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.response = new Response(statusCode, body);
    }

    private String writePretty(String jLine) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(jLine);
        return gson.toJson(je);
    }
}
