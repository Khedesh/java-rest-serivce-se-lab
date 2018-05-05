package models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Nader on 29/04/2018.
 */
@Entity
@DiscriminatorValue("deposit")
public class DepositTransaction extends BankingTransaction{

    public DepositTransaction(int amount, String creditAccount) {
        super(amount);
        this.creditAccount=creditAccount;
    }

    protected DepositTransaction() {
    }

    @Override
    public BankingTransactionType getType() {
        return BankingTransactionType.DEPOSIT;
    }

    @Column private String creditAccount;

    public String getCreditAccount() {
        return creditAccount;
    }

}
