package cz.nigol.zpravodaj.services.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import cz.nigol.zpravodaj.entities.FileMetadata;
import cz.nigol.zpravodaj.entities.User;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class FileMetadataServiceImplTest {
    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<FileMetadata> typedQuery;
    @InjectMocks
    private FileMetadataServiceImpl fileMetadataService;

    @Test
    public void testGetByUser() {
        User user = new User();
        when(entityManager.createNamedQuery(FileMetadata.GET_BY_USER, FileMetadata.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter(FileMetadata.USER_PARAM, user)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(new ArrayList<>());
        fileMetadataService.getByUser(user);
        verify(entityManager).createNamedQuery(FileMetadata.GET_BY_USER, FileMetadata.class);
        verify(typedQuery).setParameter(FileMetadata.USER_PARAM, user);
        verify(typedQuery).getResultList();
    }
}
