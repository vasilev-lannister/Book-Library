package com.csc.booklibrary.initialization.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.csc.booklibrary.common.ServiceFactory;
import com.csc.booklibrary.common.ServiceRegistry;
import com.csc.booklibrary.persistence.interfaces.TransactionHandler;
import com.csc.booklibrary.repositories.TransactionHandlerJPA;
import com.csc.booklibrary.services.impl.BookServiceImpl;
import com.csc.booklibrary.services.interfaces.BookService;

/**
 * This class tests the proper functionality of the findService method in the
 * ServiceFactory class for services
 *
 * @author mduhovnikov
 *
 */
public class ServiceFactoryForServicesTest {

    TransactionHandler handler = new TransactionHandlerJPA(null);
    ServiceRegistry<TransactionHandler> register = new ServiceRegistry<>();
    ServiceFactory<TransactionHandler> servicesFactory = new ServiceFactory<>(register, handler);

    /**
     * This method tests the function when an existing service class is given as
     * its parameter.
     */
    @Test
    public void searchForExistingService() {

        register.registerService(BookService.class, BookServiceImpl::new);
        BookService actual = servicesFactory.findService(BookService.class);
        assertNotNull(actual);
        assertTrue(BookService.class.isAssignableFrom(actual.getClass()));
        assertEquals(BookServiceImpl.class, actual.getClass());
    }

    /**
     * This method tests the function when any non-existing service class is
     * given as its parameter.
     */
    @Test
    public void searchForNonExistingServiceWhichShouldReturnNull() {
        assertNull(servicesFactory.findService(Object.class));
    }
}
