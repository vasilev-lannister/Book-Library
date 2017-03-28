package com.csc.booklibrary.services.implementation.mocks;

import com.csc.booklibrary.persistence.interfaces.Publisher;

/**
 * A mock class of Publisher used for testing.
 * 
 * @author mduhovnikov
 *
 */
public class PublisherMock implements Publisher {

    private final int id;
    private final String name;
    
    /**
     * Constructor of the class.
     * 
     * @param id The id of the mock publisher.
     * @param name The name of the mock publisher.
     */
    public PublisherMock(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getPublisherId() {
        return id;
    }

}
