package services;

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
public class UserService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAll()
    {
        final TypedQuery<User> query = entityManager.createQuery("select u from User u", User.class);
        return query.getResultList();
    }

    public void create(User user) {
        entityManager.persist(user);
    }
}
