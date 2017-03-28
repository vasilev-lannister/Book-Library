package com.csc.booklibrary.common.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.csc.booklibrary.common.ServiceFactory;
import com.csc.booklibrary.common.ServiceRegistry;
import com.csc.booklibrary.common.dummies.DummyService;
import com.csc.booklibrary.common.dummies.DummyServiceImpl;

/**
 * This class tests the proper functionality of the findService method in the
 * ServiceFactory class.
 *
 * @author mduhovnikov
 * 
 * @param <Argument>
 *            The constructor argument that is given as a parameter to register
 *            a service.
 */
public class ServiceFactoryTest<Argument> {

    private ServiceRegistry<Argument> registry = new ServiceRegistry<>();
    ServiceFactory<Argument> servicesFactory = new ServiceFactory<>(registry, null);

    /**
     * This method tests the function when an existing class is given as its
     * parameter.
     */
    @Test
    public void searchForExistingService() {

        registry.registerService(DummyService.class, DummyServiceImpl<Argument>::new);
        DummyService actual = servicesFactory.findService(DummyService.class);
        assertNotNull(actual);
        assertTrue(DummyService.class.isAssignableFrom(actual.getClass()));
        assertEquals(DummyServiceImpl.class, actual.getClass());
    }

    /**
     * This method tests the function when any non-existing class is given as
     * its parameter.
     */
    @Test
    public void searchForNonExistingServiceWhichShouldReturnNull() {
        assertNull(servicesFactory.findService(String.class));
    }
}