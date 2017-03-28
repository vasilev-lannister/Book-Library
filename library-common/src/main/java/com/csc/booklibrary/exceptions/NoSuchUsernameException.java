package com.csc.booklibrary.exceptions;

/**
 * Exception thrown when a user cannot be found by username in the persistence
 * layer. Stores the username of that user.
 *
 * @author mvitanov
 *
 */
public class NoSuchUsernameException extends RuntimeException {

    private static final long serialVersionUID = 6985917784287457706L;

    private final String username;

    /**
     * @param username
     *            Username of the user that couldn't be found.
     */
    public NoSuchUsernameException(String username) {
        this.username = username;
    }

    /**
     * @return Username of the user that couldn't be found.
     */
    public String getUsername() {
        return username;
    }

}