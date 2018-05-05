package controllers;

import models.User;
import services.UserService;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by Nader on 29/04/2018.
 */
@Path("/users")
public class UserController {
    @Inject
    UserService userService;

    @Path("/")
    @GET
    @Produces("application/json")
    public JsonArray getAll(){
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (User user : userService.getAll()) {
            builder.add(Json.createObjectBuilder().add("email",user.getEmail()));
        }
        return builder.build();
    }
}
