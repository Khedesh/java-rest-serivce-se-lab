package services;

import models.BankAccount;
import models.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Nader on 29/04/2018.
 */
@Stateless
public class BankAccountService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<BankAccount> getAll()
    {
        final TypedQuery<BankAccount> query = entityManager.createQuery("select ba from BankAccount ba", BankAccount.class);
        return query.getResultList();
    }

    public void create(BankAccount bankAccount) {
        entityManager.persist(bankAccount);
    }
}
