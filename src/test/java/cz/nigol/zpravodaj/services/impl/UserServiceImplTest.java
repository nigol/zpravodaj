package cz.nigol.zpravodaj.services.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import cz.nigol.zpravodaj.entities.User;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<User> typedQuery;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testGetAllUsers() {
        when(entityManager.createNamedQuery(User.GET_ALL, User.class)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(new ArrayList<>());
        userService.getAllUsers();
        verify(entityManager).createNamedQuery(User.GET_ALL, User.class);
        verify(typedQuery).getResultList();
    }

    @Test
    public void testGetUserById() {
        when(entityManager.find(User.class, "")).thenReturn(new User());
        userService.getUserById("");
        verify(entityManager).find(User.class, "");
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        when(entityManager.merge(user)).thenReturn(user);
        userService.saveUser(user);
        verify(entityManager).merge(user);
    }

    @Test
    public void testGetActiveUsers() {
        when(entityManager.createNamedQuery(User.GET_ACTIVE, User.class)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(new ArrayList<>());
        userService.getActiveUsers();
        verify(entityManager).createNamedQuery(User.GET_ACTIVE, User.class);
        verify(typedQuery).getResultList();
    }
}
