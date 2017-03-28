package com.csc.booklibrary.exceptions;
/**
 *  Exception thrown when trying to create a user that already existed.
 *  Stores the username of that user.
 *
 * @author lbosilkov
 *
 */
public class UserAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = -4496224185830873135L;
    private final String username;

    /**
     *
     * @param username Username of the user that trying to create.
     */
    public UserAlreadyExistsException(final String username) {
        this.username = username;
    }

    /**
     * @return Username of the user that trying to create.
     */
    public String getUsername() {
        return username;
    }

}
