package com.csc.booklibrary.persistence.interfaces;

import java.time.LocalDate;
import java.util.List;

/**
 * This class has information about the copies of a book. The fields describe
 * the dates on which the copies have been added or removed, whether a copy has
 * been taken, the content to which the copy corresponds and by whom it has been
 * registered.
 *
 * @author mduhovnikov
 *
 */
public interface BookCopy {

    /**
     * @return The date on which the copy has been removed.
     */
    LocalDate getDateRemoved();

    /**
     * @return The date on which the copy has been added.
     */
    LocalDate getDateAdded();

    /**
     * @return A flag whether it has been taken or not.
     */
    int getTaken();

    /**
     * Change the flag if the book is taken or not.
     *
     * @param taken
     *            The new flag.
     */
    void setTaken(int taken);

    /**
     * @return The content to which the copy corresponds.
     */
    Book getBook();

    /**
     * Change the book to which the copy corresponds.
     *
     * @param book
     *            The new book.
     */
    void setBook(Book book);

    /**
     * Set the date on which the copy has been removed.
     *
     * @param dateRemoved
     *            The new date.
     */
    void setDateRemoved(LocalDate dateRemoved);

    /**
     * @return The user by whom the copy has been registered.
     */
    User getRegisteredBy();

    /**
     * Change the user who has registered a copy.
     *
     * @param registeredBy
     *            The new user.
     */
    void setRegisteredBy(User registeredBy);

    /**
     * @return A list of borrows for this copy.
     */
    List<Borrow> getBookCopyBorrows();

    /**
     * @return The id of the copy of a book.
     */
    long getBookCopyId();
}
