package facadeServices.converters;

import facadeServices.IBankingTransactionService;
import models.DepositTransaction;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * Created by Nader on 03/05/2018.
 */
public class DepositTransactionConverter extends BankingTransactionConverter<DepositTransaction> {

    @Override
    protected void specificConvert(ToObjectConverterContext<DepositTransaction> context) {
        JsonObjectBuilder builder = context.getBuilder();
        DepositTransaction depositTransaction = context.getBankingTransaction();
        builder.add("creditAccount", depositTransaction.getCreditAccount());
    }

    @Override
    protected DepositTransaction specificConvert(ToJsonConverterContext context) {
        JsonObject jsonObject=context.getJsonObject();
        IBankingTransactionService bankingTransactionService=context.getBankingTransactionService();
        String creditAccount = jsonObject.getString("creditAccount");
        String debitAccount = jsonObject.getString("debitAccount");
        return bankingTransactionService.createDepositTransaction(context.getAmount(),creditAccount);
    }
}
