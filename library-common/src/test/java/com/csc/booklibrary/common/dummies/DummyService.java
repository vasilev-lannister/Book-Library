package com.csc.booklibrary.common.dummies;

/**
 * This is a dummy service to test the proper functionality of the Service
 * Factory.
 * 
 * @author mduhovnikov
 *
 */
public interface DummyService {

    /**
     * The method finds a dummy by its name.
     * 
     * @param dummyName
     *            The name of the sought dummy.
     * @return The sought dummy.
     */
    String findDummyByName(String dummyName);
}
