package com.csc.booklibrary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.csc.booklibrary.common.ServiceFactory;
import com.csc.booklibrary.common.ServiceRegistry;
import com.csc.booklibrary.persistence.interfaces.BookCopyRepository;
import com.csc.booklibrary.repositories.BookCopyRepositoryJPA;

/**
 * This class tests the proper functionality of the findService method in the
 * ServiceFactory class for repositories
 *
 * @author mduhovnikov
 *
 */
public class ServiceFactoryForRepositoriesTest {

    static final EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("library-jpa-hibernate");
    ServiceRegistry<EntityManager> register = new ServiceRegistry<>();
    ServiceFactory<EntityManager> servicesFactory = new ServiceFactory<>(register,
            entityManagerFactory.createEntityManager());

    /**
     * This method tests the function when an existing repository class is given
     * as its parameter.
     */
    @Test
    public void searchForExistingRepository() {
        register.registerService(BookCopyRepository.class, BookCopyRepositoryJPA::new);

        BookCopyRepository actual = servicesFactory.findService(BookCopyRepository.class);
        assertNotNull(actual);
        assertTrue(BookCopyRepository.class.isAssignableFrom(actual.getClass()));
        assertEquals(BookCopyRepositoryJPA.class, actual.getClass());
    }

    /**
     * This method tests the function when any non-existing repository class is
     * given as its parameter.
     */
    @Test
    public void searchForNonExistingRepositoryWhichShouldReturnNull() {
        assertNull(servicesFactory.findService(Object.class));
    }
}
