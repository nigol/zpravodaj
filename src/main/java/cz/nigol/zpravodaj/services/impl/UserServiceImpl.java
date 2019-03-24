package cz.nigol.zpravodaj.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cz.nigol.zpravodaj.entities.User;
import cz.nigol.zpravodaj.services.UserService;

@Stateless
public class UserServiceImpl implements UserService {
    @PersistenceContext(unitName="zpravodajPU")
    private EntityManager em;

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> typedQuery = em.createNamedQuery(User.GET_ALL, User.class);
        return new ArrayList<>(typedQuery.getResultList());
    }

    @Override
    public User getUserById(String id) {
        return em.find(User.class, id);
    }

    @Override
    public User saveUser(User user) {
        return em.merge(user);
    }

    @Override
    public List<User> getActiveUsers() {
        TypedQuery<User> typedQuery = em.createNamedQuery(User.GET_ACTIVE, User.class);
        return new ArrayList<>(typedQuery.getResultList());
    }
}
