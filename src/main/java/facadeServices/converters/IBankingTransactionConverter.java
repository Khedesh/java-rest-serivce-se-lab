package facadeServices.converters;

import facadeServices.IBankingTransactionService;
import models.BankingTransaction;

import javax.json.JsonObject;

/**
 * Created by Nader on 03/05/2018.
 */
public interface IBankingTransactionConverter {
    JsonObject convertBankingTransaction(BankingTransaction bankingTransaction);
    BankingTransaction convertJsonObject(JsonObject jsonObject, IBankingTransactionService bankingTransactionService);
}
