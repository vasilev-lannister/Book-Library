package com.csc.booklibrary.services.exceptions;

/**
 * Exception thrown when a language cannot be found by id in the persistence
 * layer. Stores the id of that language.
 * 
 * @author mduhovnikov
 *
 */
public class NoSuchLanguageException extends RuntimeException {

    private static final long serialVersionUID = -7083565898310981692L;
    private final long languageId;

    /**
     * Constructor.
     * 
     * @param languageId
     *            id of the language which wasn't found.
     */
    public NoSuchLanguageException(final long languageId) {
        this.languageId = languageId;
    }

    /**
     *
     * @return id of the language which wasn't found.
     */
    public long getLanguageId() {
        return languageId;
    }
}