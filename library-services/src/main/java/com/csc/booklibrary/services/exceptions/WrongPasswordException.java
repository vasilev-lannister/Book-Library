package com.csc.booklibrary.services.exceptions;

/**
 * Exception thrown when a login attempt is made with a wrong password.
 * 
 * @author mvitanov
 *
 */
public final class WrongPasswordException extends RuntimeException {

    private static final long serialVersionUID = 3045597958630446289L;
    private final String username;
    private final String password;

    /**
     * @param username
     *            Username of the user for whose profile the login attempt was
     *            made.
     * @param password
     *            Wrong password used in the login attempt.
     */
    public WrongPasswordException(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * @return Username of the user for whose profile the login attempt was
     *         made.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return Wrong password used in the login attempt.
     */
    public String getPassword() {
        return password;
    }
}
