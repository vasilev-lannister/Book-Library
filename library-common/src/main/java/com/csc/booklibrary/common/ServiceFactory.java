package com.csc.booklibrary.common;

import java.util.function.Function;

/**
 * This class finds services and creates instances corresponding to them.
 * 
 * @author mduhovnikov
 *
 * @param <Argument>
 *            The constructor argument that is given as a parameter to register
 *            a service.
 */
public final class ServiceFactory<Argument> {

    private final Argument constructorArgument;
    private final ServiceRegistry<Argument> registry;

    /**
     * Constructor that sets values for the registry and the constructor
     * argument.
     * 
     * @param registry
     *            the registry from which a service can be searched
     * @param constructorArgument
     *            the argument uses for creating a service instance
     */
    public ServiceFactory(final ServiceRegistry<Argument> registry, final Argument constructorArgument) {
        this.registry = registry;
        this.constructorArgument = constructorArgument;
    }

    /**
     * This methods finds a service by a given interface and creates its
     * instance.
     * 
     * @param service
     *            The interface that we look for.
     * @return The instance corresponding to the provided interface.
     */
    public <Service> Service findService(Class<Service> service) {
        Function<Argument, ?> constructor = registry.findService(service);

        Service result = null;
        if (constructor != null) {
            final Object tmp = constructor.apply(constructorArgument);
            assert tmp != null;
            assert service.isAssignableFrom(tmp.getClass());
            result = service.cast(tmp);
            assert result != null;
        }
        return result;
    }
}
