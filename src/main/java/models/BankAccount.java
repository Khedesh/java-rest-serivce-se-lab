package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Nader on 29/04/2018.
 */
@Entity
@Table(name="bankaccounts")
public class BankAccount {

    public BankAccount(User user, String accountNumber) {
        this.user = user.getEmail();
        this.accountNumber = accountNumber;
    }

    public BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    protected BankAccount() {
        //For JPA
    }

    @Column(name = "\"User\"")
    private String user;

    public String getUser() {
        return user;
    }

    @Id
    private String accountNumber;

    public String getAccountNumber() {
        return accountNumber;
    }

    @Column
    private int balance = 0;

    public int getBalance() {
        return balance;
    }

    private void debit(int amount) {
        if (balance > amount) {
            balance -= amount;
        }
    }

    private void credit(int amount) {
        balance += amount;
    }

    public WithDrawTransaction withdraw(int amount)
    {
        debit(amount);
        return new WithDrawTransaction(amount,accountNumber);
    }

    public DepositTransaction deposit(int amount)
    {
        credit(amount);
        return new DepositTransaction(amount,accountNumber);
    }

    public TransferTransaction transferTo(int amount,BankAccount creditAccount)
    {
        debit(amount);
        creditAccount.credit(amount);
        return new TransferTransaction(amount,accountNumber,creditAccount.accountNumber);
    }
}
