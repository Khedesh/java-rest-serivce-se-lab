package controllers;

import models.User;
import services.UserService;

import javax.inject.Inject;
import javax.json.*;
import javax.ws.rs.*;

/**
 * Created by Nader on 29/04/2018.
 */
@Path("/users")
public class UserController {
    @Inject
    UserService userService;

    private static JsonObject convertToJson(User user) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("email", user.getEmail());
        return builder.build();
    }

    @Path("/")
    @GET
    @Produces("application/json")
    public JsonArray getAll(){
        JsonArrayBuilder builder = Json.createArrayBuilder();
        userService.getAll().forEach(user->builder.add(convertToJson(user)));
        return builder.build();
    }

    @Path("/{email}")
    @GET
    @Produces("application/json")
    public JsonObject get(@PathParam("email") String email) {
        User user = userService.get(email);
        return convertToJson(user);

    }

    @Path("/")
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public JsonObject post(JsonObject request) {
        String email=request.getString("email");
        User user = new User(email);
        userService.create(user);
        return convertToJson(user);
    }
}
