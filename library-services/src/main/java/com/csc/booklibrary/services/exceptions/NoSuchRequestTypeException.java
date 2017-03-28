package com.csc.booklibrary.services.exceptions;

/**
 * Exception thrown when a request type cannot be found by id in the persistence
 * layer. Stores the id of that request type.
 * 
 * @author mduhovnikov
 *
 */
public class NoSuchRequestTypeException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 
     * Constructor with no parameters.
     */
    public NoSuchRequestTypeException() {
        super();
    }
    
    /**
     * @param s An appropriate message.
     */
    public NoSuchRequestTypeException(final String s) {
        super(s);
    }
    
    /**
     *
     * @param requestTypeId The id of the request type.
     */
    public NoSuchRequestTypeException(final int requestTypeId) {
        this(Integer.toString(requestTypeId));
    }
    
    /**
     * 
     * @param requestTypeId The id of the request type.
     * @param bookQuantity The quantity of the book.
     */
    public NoSuchRequestTypeException(final int requestTypeId, final int bookQuantity) {
        this(Integer.toString(requestTypeId) + " " + Integer.toString(bookQuantity));
    }
}
