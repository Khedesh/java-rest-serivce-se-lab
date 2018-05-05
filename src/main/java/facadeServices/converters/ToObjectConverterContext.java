package facadeServices.converters;

import models.BankingTransaction;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

/**
 * Created by Nader on 05/05/2018.
 */
public class ToObjectConverterContext<T extends BankingTransaction> {

    private JsonObjectBuilder builder;
    private T bankingTransaction;

    public ToObjectConverterContext(T bankingTransaction) {
        this.builder = Json.createObjectBuilder();
        this.bankingTransaction = bankingTransaction;
    }

    public JsonObjectBuilder getBuilder() {
        return builder;
    }

    public T getBankingTransaction() {
        return bankingTransaction;
    }
}
