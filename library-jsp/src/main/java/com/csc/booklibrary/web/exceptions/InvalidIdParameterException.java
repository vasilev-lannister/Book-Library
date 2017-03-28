package com.csc.booklibrary.web.exceptions;

/**
 * Exception thrown when the id parameter of the web request is not parse-able.
 * Stores the String value of that parameter.
 * 
 * @author mvitanov
 *
 */
public final class InvalidIdParameterException extends RuntimeException {

    private static final long serialVersionUID = -2011006924369608880L;

    private final String id;

    /**
     * @param id
     *            The String that failed to parse.
     */
    public InvalidIdParameterException(String id) {
        this.id = id;
    }

    /**
     * @return The String that failed to parse.
     */
    public String getId() {
        return id;
    }

}
