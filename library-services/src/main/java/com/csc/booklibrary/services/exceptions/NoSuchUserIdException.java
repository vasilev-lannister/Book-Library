package com.csc.booklibrary.services.exceptions;

/**
 * Exception thrown when a User cannot be found in the persistence layer. Stores
 * the user's id.
 * 
 * @author mvitanov
 *
 */
public final class NoSuchUserIdException extends RuntimeException {

    private static final long serialVersionUID = 355689859026499684L;

    private final long userId;

    /**
     * @param userId
     *            Id of the user that wasn't found.
     */
    public NoSuchUserIdException(final long userId) {
        this.userId = userId;
    }

    /**
     * @return Id of the user that wasn't found.
     */
    public long getUserId() {
        return userId;
    }
}