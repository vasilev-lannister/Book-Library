package com.csc.booklibrary.common;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * This class is used for registering a service.
 *
 * @author mduhovnikov
 *
 * @param <Argument>
 *            The constructor argument that is given as a parameter to register
 *            a service
 */
public final class ServiceRegistry<Argument> {

    private final Map<Class<?>, Function<Argument, ?>> factoryMap = new HashMap<>();

    /**
     * This method registers a new service.
     *
     * @param serviceInterface
     *            the interface with which a service will be registered. Cannot
     *            be null.
     * @param serviceConstructor
     *            constructor of the interface implementation used for creating
     *            the service. Cannot be null.
     */
    public <Service> void registerService(final Class<Service> serviceInterface,
            final Function<Argument, Service> serviceConstructor) {
        assert serviceInterface != null;
        assert serviceInterface.isInterface();
        assert serviceConstructor != null;
        factoryMap.put(serviceInterface, serviceConstructor);
    }

    /**
     * This method is responsible for finding service constructors.
     *
     * @param serviceInterface
     *            the interface of the service to search for. Should not be
     *            null.
     * @return constructor of the provided service interface
     *
     */
    public <Service> Function<Argument, ?> findService(final Class<Service> serviceInterface) {
        assert serviceInterface != null;
        return factoryMap.get(serviceInterface);
    }

}
