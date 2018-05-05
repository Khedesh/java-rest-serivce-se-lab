package models;

import utils.Core;

import javax.persistence.*;

/**
 * Created by Nader on 29/04/2018.
 */
@Entity(name="BankingTransactions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Transaction_Type")
public abstract class BankingTransaction {

    protected BankingTransaction(int amount) {
        id = Core.GenerateRandomUUIDString();
        this.amount=amount;
    }
    protected BankingTransaction() {
        //For JPA
    }

    @Id
    protected String id;

    public String getId() {
        return id;
    }

    @Column
    protected int amount;

    public int getAmount() {
        return amount;
    }

    public abstract BankingTransactionType getType();
}
