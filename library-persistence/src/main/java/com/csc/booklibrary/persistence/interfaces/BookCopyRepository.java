package com.csc.booklibrary.persistence.interfaces;

import java.util.List;
/**
 * Manages operations related with book copy.
 *
 * @author lbosilkov
 *
 */
public interface BookCopyRepository {
    /**
     * Finds all available book copies for book.
     *
     * @param book Non-null object, which will be sought for free copies.
     * @return List with available copies.
     */
    List<BookCopy> findAvailableBookCopies(Book book);

    /**
     * Borrowing a book.
     *
     * @param request Non-null  object, which will be processed.
     * @param bookCopy Non-null object, which will be borrowed.
     * @return Object containing state of newly created borrow.
     */
    Borrow addBorrow(Request request, BookCopy bookCopy);
}
