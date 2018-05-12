package clients;

import javax.json.JsonArray;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nader on 12/05/2018.
 */
public class UserClient {
    public List<String> getAllUsers() {
        List<String> allUsers = new ArrayList<>();
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/SimpleRestService-1.0-SNAPSHOT/api/users");
        JsonArray response = target.request(MediaType.APPLICATION_JSON).get(JsonArray.class);
        for (JsonValue user : response) {
            allUsers.add(((JsonString) user).getString());
        }
        return allUsers;
    }
}
