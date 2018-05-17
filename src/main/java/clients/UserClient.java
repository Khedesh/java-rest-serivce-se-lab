package clients;

import models.User;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import javax.json.*;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nader on 12/05/2018.
 */
public class UserClient {
    private static final String RELATIVE_PATH = "users";
    public static List<String> getAllUsers() throws IOException, URISyntaxException {
        List<String> allUsers = new ArrayList<String>();
        JsonArray result = RestClient.get(RELATIVE_PATH);
        result.forEach( u-> allUsers.add(((JsonObject)u).getString("email")));
        return allUsers;
    }

    public static String createUser(String email) throws UnsupportedEncodingException {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("email", email);
        JsonObject result = RestClient.post(RELATIVE_PATH, builder.build());
        return result.getString("email");
    }
}
