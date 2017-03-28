package com.csc.booklibrary.services.dto;

import java.io.Serializable;

/**
 * This class has information about the publisher of a book.
 * 
 * @author mduhovnikov
 *
 */
public class PublisherDTO implements Serializable {

    private static final long serialVersionUID = -4147144596255058113L;
    private long publisherId;
    private String name;
    
    /**
     * @param publisherId The id of the publisher.
     * @param name The name of the publisher.
     */
    public PublisherDTO(final long publisherId, final String name) {
        assert publisherId > 0;
        assert name != null;
        this.publisherId = publisherId;
        this.name = name;
    }
    
    /**
     * @return The id of the publisher.
     */
    public long getPublisherId() {
        return publisherId;
    }
    
    /**
     * @return The name of the publisher.
     */
    public String getName() {
        return name;
    }
}
