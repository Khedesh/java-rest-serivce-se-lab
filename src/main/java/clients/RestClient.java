package clients;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;

import javax.json.*;
import javax.validation.constraints.Null;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by Nader on 15/05/2018.
 */
public class RestClient {
    private static final String BASE_PATH = "http://localhost:8080/SimpleRestService-1.0-SNAPSHOT/api/";

    private static JsonStructure getJson(String address, ArrayList<NameValuePair> params) throws URISyntaxException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet getRequest = new HttpGet(address);
        updateUri(getRequest,params);
        getRequest.addHeader(new BasicHeader("Accept", "application/json"));
        return executeRequest(httpClient, getRequest);
    }

    private static JsonStructure executeRequest(HttpClient httpClient, HttpUriRequest request) {
        InputStream instream = null;
        JsonStructure result = null;
        try {
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                instream = entity.getContent();
                result = convertStreamToJson(instream);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (instream != null) {
                try {
                    instream.close();
                } catch (Exception exc) {

                }
            }
        }
        return result;
    }

    private static JsonStructure getJson(String address) throws URISyntaxException {
        return getJson(address,null);
    }

    private static void updateUri(HttpGet request, ArrayList<NameValuePair> params) throws URISyntaxException {
        if(params==null)return;
        if(params.isEmpty())return;
        for (NameValuePair param:params) {
            URI uri = new URIBuilder(request.getURI()).addParameters(params).build();
            request.setURI(uri);
        }
    }

    private static JsonStructure postJson(String address, JsonStructure request) throws UnsupportedEncodingException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost postRequest = new HttpPost(address);
        postRequest.addHeader(new BasicHeader("Accept", "application/json"));
        postRequest.addHeader(new BasicHeader("Content-Type", "application/json"));
        postRequest.setEntity(new StringEntity(request.toString()));
        return executeRequest(httpClient, postRequest);
    }

    private static JsonStructure convertStreamToJson(InputStream is) throws IOException {
        JsonReader jsonReader = Json.createReader(is);
        JsonStructure result = jsonReader.read();
        return result;
    }

    public static <T extends JsonStructure> T get(String relativePath) throws URISyntaxException {
        return (T) getJson(BASE_PATH + relativePath, null);
    }

    public static <T extends JsonStructure> T get(String relativePath, ArrayList<NameValuePair> params) throws URISyntaxException {
        return (T) getJson(BASE_PATH + relativePath, params);
    }

    public static <T extends JsonStructure> T post(String relativePath, JsonStructure request) throws UnsupportedEncodingException {
        return (T) postJson(BASE_PATH + relativePath,request);
    }
}
