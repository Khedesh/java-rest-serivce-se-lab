package models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Nader on 05/05/2018.
 */
@Entity
@DiscriminatorValue("transfer")
public class TransferTransaction extends BankingTransaction {
    public TransferTransaction(int amount,  String debitAccount, String creditAccount) {
        super(amount);
        this.creditAccount=creditAccount;
        this.debitAccount=debitAccount;
    }

    protected TransferTransaction() {
    }

    @Override
    public BankingTransactionType getType() {
        return BankingTransactionType.TRANSFER;
    }

    @Column
    private String creditAccount;

    public String getCreditAccount() {
        return creditAccount;
    }

    @Column private String debitAccount;

    public String getDebitAccount() {
        return debitAccount;
    }
}
