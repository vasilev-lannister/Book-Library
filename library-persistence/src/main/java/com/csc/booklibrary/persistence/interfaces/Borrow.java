package com.csc.booklibrary.persistence.interfaces;

import java.time.LocalDate;

/**
 * This class has information about the borrowing of a book. The fields describe
 * the request for which the borrowing is about, the user that is requesting the
 * book, the book that is being requested, the dates on which the book is taken
 * and returned and the date on which it should actually be returned.
 *
 * @author mduhovnikov
 *
 */
public interface Borrow {

    /**
     * @return The date on which the book should be returned.
     */
    LocalDate getFinalDate();

    /**
     * @return The request to which the borrowing corresponds.
     */
    Request getRequest();

    /**
     * @return The book copy that is being taken.
     */
    BookCopy getBookCopy();

    /**
     * @return The date on which the book has been taken.
     */
    LocalDate getDateTaken();

    /**
     * Changes the date on which the book has been taken.
     *
     * @param dateTaken
     *            The new date of taking.
     */
    void setDateTaken(LocalDate dateTaken);

    /**
     * @return The date on which the book has been returned.
     */
    LocalDate getDateReturned();

    /**
     * Changes the date on which the book should be returned.
     *
     * @param finalDate
     *            The new date of return.
     */
    void setFinalDate(LocalDate finalDate);

    /**
     * @return The user that is requesting the book.
     */
    User getUser();

    /**
     * Changes the user that is requesting the book.
     *
     * @param user
     *            The new user.
     */
    void setUser(User user);

}
