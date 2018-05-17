package controllers;

import models.BankAccount;
import models.User;
import services.BankAccountService;
import services.UserService;

import javax.inject.Inject;
import javax.json.*;
import javax.ws.rs.*;

/**
 * Created by Nader on 29/04/2018.
 */
@Path("/bankaccounts")
public class BankAccountController {

    private final UserService userService;
    BankAccountService bankAccountService;

    @Inject
    public BankAccountController(BankAccountService bankAccountService, UserService userService) {
        this.bankAccountService = bankAccountService;
        this.userService = userService;
    }

    @Path("/")
    @GET
    @Produces("application/json")
    public JsonArray getAll(){
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (BankAccount bankAccount : bankAccountService.getAll()) {
            builder.add(convertToJson(bankAccount));
        }
        return builder.build();
    }

    @Path("/")
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public JsonObject post(JsonObject request) {
        String accountNumber=request.getString("accountNumber");
        String email =request.getString("user");
        User user= userService.get(email);

        BankAccount bankAccount = new BankAccount(user,accountNumber);

        bankAccountService.create(bankAccount);
        return convertToJson(bankAccount).build();
    }

    private JsonObjectBuilder convertToJson(BankAccount bankAccount) {
        return Json.createObjectBuilder()
                .add("accountNumber", bankAccount.getAccountNumber())
                .add("user", bankAccount.getUser())
                .add("balance", bankAccount.getBalance());
    }
}
