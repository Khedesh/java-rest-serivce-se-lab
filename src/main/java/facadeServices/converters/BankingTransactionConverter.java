package facadeServices.converters;

import facadeServices.IBankingTransactionService;
import models.BankingTransaction;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public abstract class BankingTransactionConverter<T extends BankingTransaction> implements IBankingTransactionConverter{

    public JsonObject convert(T bankingTransaction) {
        ToObjectConverterContext context = new ToObjectConverterContext(bankingTransaction);
        JsonObjectBuilder builder = context.getBuilder();
        builder.add("id", bankingTransaction.getId())
                .add("amount", bankingTransaction.getAmount())
                .add("transactionType", bankingTransaction.getType().name());
        specificConvert(context);
        return builder.build();
    }

    public T convert(JsonObject jsonObject, IBankingTransactionService bankingTransactionService){
        int amount = jsonObject.getInt("amount");
        ToJsonConverterContext context = new ToJsonConverterContext(jsonObject,bankingTransactionService,amount);
        return specificConvert(context);
    }

    public JsonObject convertBankingTransaction(BankingTransaction bankingTransaction){
        return convert((T)bankingTransaction);
    }

    public BankingTransaction convertJsonObject(JsonObject jsonObject, IBankingTransactionService bankingTransactionService) {
        return convert(jsonObject, bankingTransactionService);
    }

    protected abstract void specificConvert(ToObjectConverterContext<T> context);
    protected abstract T specificConvert(ToJsonConverterContext context);
}
