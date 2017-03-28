package com.csc.booklibrary.services.exceptions;

/**
 * Exception thrown when a book content cannot be found by id in the persistence
 * layer. Stores the id of that book content.
 * 
 * @author mvitanov
 *
 */
public final class NoSuchBookIdException extends RuntimeException {

    private static final long serialVersionUID = -5129018699591716447L;

    private final long bookId;

    /**
     * @param bookId
     *            Id of the book which wasn't found.
     */
    public NoSuchBookIdException(final long bookId) {
        this.bookId = bookId;
    }

    /**
     * @return Id of the book which wasn't found.
     */
    public long getBookId() {
        return bookId;
    }

}
