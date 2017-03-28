package com.csc.booklibrary.services.exceptions;
/**
 * Thrown to indicate that there is no free copy of book.
 * @author lbosilkov
 *
 */
public class NoFreeCopyOfBookException extends RuntimeException {
    private final long bookContentId;
    private static final long serialVersionUID = -8065030908502448755L;

    /**
     * Constructor.
     * @param bookContentId id of the book content which wasn't found.
     */
    public NoFreeCopyOfBookException(final long bookContentId) {
        this.bookContentId = bookContentId;
    }

    /**
     *
     * @return id of the book content which wasn't found.
     */
    public long getBookId() {
        return bookContentId;
    }
}
