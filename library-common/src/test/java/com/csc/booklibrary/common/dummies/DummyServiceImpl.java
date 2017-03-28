package com.csc.booklibrary.common.dummies;

/**
 * @see DummyService
 * 
 * @author mduhovnikov
 *
 * @param <Argument>
 *            The constructor argument that is used when registering a service.
 */
public class DummyServiceImpl<Argument> implements DummyService {

    /**
     * Constructor of the dummy class.
     * 
     * @param argument
     *            The constructor argument.
     */
    public DummyServiceImpl(final Argument argument) {
    }

    @Override
    public String findDummyByName(String dummyName) {
        return null;
    }
}
