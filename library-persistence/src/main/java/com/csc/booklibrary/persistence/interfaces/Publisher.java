package com.csc.booklibrary.persistence.interfaces;

/**
 * This class has information about the publisher of the book. The fields
 * describe their name.
 *
 * @author mduhovnikov
 *
 */
public interface Publisher {

    /**
     * @return The name of the publisher.
     */
    String getName();

    /**
     * @return The id of the publisher.
     */
    long getPublisherId();
}