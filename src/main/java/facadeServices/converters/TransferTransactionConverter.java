package facadeServices.converters;

import facadeServices.IBankingTransactionService;
import models.TransferTransaction;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * Created by Nader on 05/05/2018.
 */
public class TransferTransactionConverter extends BankingTransactionConverter<TransferTransaction> {

    @Override
    protected void specificConvert(ToObjectConverterContext<TransferTransaction> context) {
        JsonObjectBuilder builder = context.getBuilder();
        TransferTransaction transferTransaction = context.getBankingTransaction();
        builder.add("creditAccount", transferTransaction.getCreditAccount());
        builder.add("debitAccount", transferTransaction.getDebitAccount());
    }

    @Override
    protected TransferTransaction specificConvert(ToJsonConverterContext context) {
        JsonObject jsonObject=context.getJsonObject();
        IBankingTransactionService bankingTransactionService=context.getBankingTransactionService();
        String creditAccount = jsonObject.getString("creditAccount");
        String debitAccount = jsonObject.getString("debitAccount");
        return bankingTransactionService.createTransferTransaction(context.getAmount(),debitAccount,creditAccount);
    }
}
