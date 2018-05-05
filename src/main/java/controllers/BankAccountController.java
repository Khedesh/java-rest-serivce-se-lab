package controllers;

import models.BankAccount;
import models.User;
import services.BankAccountService;
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
@Path("/bankaccounts")
public class BankAccountController {
    @Inject
    BankAccountService bankAccountService;

    @Path("/")
    @GET
    @Produces("application/json")
    public JsonArray getAll(){
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (BankAccount bankAccount : bankAccountService.getAll()) {
            builder.add(Json.createObjectBuilder()
                    .add("accountNumber", bankAccount.getAccountNumber())
                    .add("user", bankAccount.getUser())
                    .add("balance", bankAccount.getBalance()));
        }
        return builder.build();
    }
}
