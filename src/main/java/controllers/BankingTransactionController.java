package controllers;

import models.BankingTransaction;

import javax.inject.Inject;
import javax.json.*;
import javax.ws.rs.*;

/**
 * Created by Nader on 29/04/2018.
 */
@Path("/bankingtransactions")
public class BankingTransactionController {
    private final IBankingTransactionFacadeService bankingTransactionFacadeService;

    @Inject
    public BankingTransactionController(IBankingTransactionFacadeService bankingTransactionFacadeService) {
       this.bankingTransactionFacadeService=bankingTransactionFacadeService;
    }

    @Path("/")
    @GET
    @Produces("application/json")
    public JsonArray getAll(){
        return bankingTransactionFacadeService.getAll();
    }

    @Path("/")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public JsonObject post(JsonObject jsonObject) {
        return bankingTransactionFacadeService.create(jsonObject);
    }

    @Path("/{id}")
    @GET
    @Produces("application/json")
    public JsonObject get(@PathParam("id") String id) {
        return  bankingTransactionFacadeService.get(id);
    }
}
