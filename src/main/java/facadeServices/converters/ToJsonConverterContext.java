package facadeServices.converters;

import facadeServices.IBankingTransactionService;
import models.BankingTransaction;

import javax.json.JsonObject;

/**
 * Created by Nader on 05/05/2018.
 */
public class ToJsonConverterContext {
    private JsonObject jsonObject;
    private IBankingTransactionService bankingTransactionService;
    private int amount;

    public ToJsonConverterContext(JsonObject jsonObject, IBankingTransactionService bankingTransactionService, int amount) {
        this.jsonObject = jsonObject;
        this.bankingTransactionService = bankingTransactionService;
        this.amount = amount;
    }

    public IBankingTransactionService getBankingTransactionService() {
        return bankingTransactionService;
    }

    public int getAmount() {
        return amount;
    }

    public JsonObject getJsonObject() {

        return jsonObject;
    }
}
