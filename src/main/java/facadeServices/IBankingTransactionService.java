package facadeServices;

import models.BankingTransaction;
import models.DepositTransaction;
import models.TransferTransaction;
import models.WithDrawTransaction;

import java.util.List;

/**
 * Created by Nader on 04/05/2018.
 */
public interface IBankingTransactionService {
    WithDrawTransaction createWithDrawTransaction(int ammount, String debitAccount);
    DepositTransaction createDepositTransaction(int ammount, String creditAccount);
    TransferTransaction createTransferTransaction(int ammount, String debitAccount, String creditAccount);
    List<BankingTransaction> getAll();
}
