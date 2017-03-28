package com.csc.booklibrary.services.exceptions;

/**
 * Exception thrown when an invalid input is detected in any of the fields. 
 * Stores the field name where the exception occurred.
 * 
 * @author mduhovnikov
 *
 */
public class InvalidFieldDataInputException extends IllegalArgumentException {

    private static final long serialVersionUID = 477223683566664254L;
    private final String fieldName;
    
    /**
     * 
     * @param fieldName The name of the field with invalid input.
     */
    public InvalidFieldDataInputException(final String fieldName) {
        super();
        this.fieldName = fieldName;
    }
    
    /**
     * 
     * @return The name of the field;
     */
    public String getFieldName() {
        return fieldName;
    }
}
