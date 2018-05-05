package models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Nader on 29/04/2018.
 */
@Entity
@DiscriminatorValue("withdraw")
public class WithDrawTransaction extends BankingTransaction{

    public WithDrawTransaction(int amount, String debitAccount) {
        super(amount);
        this.debitAccount=debitAccount;
    }

    protected WithDrawTransaction() {
        //For JPA
    }

    @Override
    public BankingTransactionType getType() {
        return BankingTransactionType.WITHDRAW;
    }

    @Column private String debitAccount;

    public String getDebitAccount() {
        return debitAccount;
    }
}
