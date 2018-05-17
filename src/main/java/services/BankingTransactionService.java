package services;

import facadeServices.IBankingTransactionService;
import models.*;

import javax.ejb.Stateless;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Nader on 29/04/2018.
 */
@Stateless
public class BankingTransactionService implements IBankingTransactionService {
    @PersistenceContext
    private EntityManager entityManager;

    public WithDrawTransaction createWithDrawTransaction(int ammount, String debitAccount) {
        BankAccount bankAccount = entityManager.find(BankAccount.class, debitAccount);
        WithDrawTransaction withDrawTransaction = bankAccount.withdraw(ammount);
        entityManager.persist(bankAccount);
        entityManager.persist(withDrawTransaction);
        return withDrawTransaction;
    }

    public DepositTransaction createDepositTransaction(int ammount, String creditAccount) {
        BankAccount bankAccount = entityManager.find(BankAccount.class, creditAccount);
        DepositTransaction depositTransaction = bankAccount.deposit(ammount);
        entityManager.persist(bankAccount);
        entityManager.persist(depositTransaction);
        return depositTransaction;
    }

    public TransferTransaction createTransferTransaction(int ammount, String debitAccount, String creditAccount) {
        BankAccount fromAccount = entityManager.find(BankAccount.class, debitAccount);
        BankAccount toAccount = entityManager.find(BankAccount.class, creditAccount);
        TransferTransaction transferTransaction = fromAccount.transferTo(ammount, toAccount);
        entityManager.persist(fromAccount);
        entityManager.persist(toAccount);
        entityManager.persist(transferTransaction);
        return transferTransaction;
    }

    public List<BankingTransaction> getAll() {
        final TypedQuery<BankingTransaction> query = entityManager.createQuery("select bt from BankingTransactions bt", BankingTransaction.class);
        return query.getResultList();
    }

    public BankingTransaction get(String id) {
        return entityManager.find(BankingTransaction.class,id);
    }
}
