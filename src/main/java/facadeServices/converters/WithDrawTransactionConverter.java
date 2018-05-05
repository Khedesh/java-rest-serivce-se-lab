package facadeServices.converters;

import facadeServices.IBankingTransactionService;
import models.WithDrawTransaction;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * Created by Nader on 03/05/2018.
 */
public class WithDrawTransactionConverter extends BankingTransactionConverter<WithDrawTransaction> {

    @Override
    protected void specificConvert(ToObjectConverterContext<WithDrawTransaction> context) {
        JsonObjectBuilder builder = context.getBuilder();
        WithDrawTransaction withDrawTransaction = context.getBankingTransaction();
        builder.add("debitAccount", withDrawTransaction.getDebitAccount());
    }

    @Override
    protected WithDrawTransaction specificConvert(ToJsonConverterContext context) {
        JsonObject jsonObject=context.getJsonObject();
        IBankingTransactionService bankingTransactionService=context.getBankingTransactionService();
        String debitAccount = jsonObject.getString("debitAccount");
        return bankingTransactionService.createWithDrawTransaction(context.getAmount(),debitAccount);
    }
}
