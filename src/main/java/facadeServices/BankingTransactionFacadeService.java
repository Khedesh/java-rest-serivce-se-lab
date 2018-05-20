package facadeServices;

import controllers.IBankingTransactionFacadeService;
import facadeServices.converters.DepositTransactionConverter;
import facadeServices.converters.IBankingTransactionConverter;
import facadeServices.converters.TransferTransactionConverter;
import facadeServices.converters.WithDrawTransactionConverter;
import models.BankingTransaction;
import models.BankingTransactionType;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nader on 04/05/2018.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class BankingTransactionFacadeService implements IBankingTransactionFacadeService {
    private static Map<BankingTransactionType,IBankingTransactionConverter> bankingTransactionConverters =
            new HashMap<>();
    static {
        bankingTransactionConverters.put(BankingTransactionType.WITHDRAW,new WithDrawTransactionConverter());
        bankingTransactionConverters.put(BankingTransactionType.DEPOSIT,new DepositTransactionConverter());
        bankingTransactionConverters.put(BankingTransactionType.TRANSFER,new TransferTransactionConverter());
    }
    public static JsonObject convert(BankingTransaction bankingTransaction)
    {
        return bankingTransactionConverters.get(bankingTransaction.getType())
                .convertBankingTransaction(bankingTransaction);
    }

    public static BankingTransaction convert(JsonObject jsonObject, IBankingTransactionService bankingTransactionService) {
        String transactionType = jsonObject.getString("transactionType");
        BankingTransactionType key = BankingTransactionType.valueOf(transactionType);

        return bankingTransactionConverters.get(key)
                .convertJsonObject(jsonObject, bankingTransactionService);
    }

    private final IBankingTransactionService bankingTransactionService;

    @Inject
    public BankingTransactionFacadeService(IBankingTransactionService bankingTransactionService) {
        this.bankingTransactionService=bankingTransactionService;
    }

    protected BankingTransactionFacadeService() {
        //For CDI
        this.bankingTransactionService=null;
    }

    public JsonObject create(JsonObject jsonObject){
        BankingTransaction bankingTransaction= convert(jsonObject,bankingTransactionService);
        return convert(bankingTransaction);
    }

    public JsonObject get(String id) {
        return convert(bankingTransactionService.get(id));
    }

    public JsonArray getAll()
    {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (BankingTransaction bankingTransaction : bankingTransactionService.getAll()) {
            builder.add(convert(bankingTransaction));
        }
        return builder.build();
    }
}
