package com.csc.booklibrary.services.implementation.mocks;

import java.util.ArrayList;
import java.util.List;

import com.csc.booklibrary.persistence.interfaces.Book;
import com.csc.booklibrary.persistence.interfaces.BookCopy;
import com.csc.booklibrary.persistence.interfaces.BookCopyRepository;
import com.csc.booklibrary.persistence.interfaces.Borrow;
import com.csc.booklibrary.persistence.interfaces.Request;

/**
 * A mock class of BookCopyRepository used for testing.
 *
 * @author mduhovnikov
 *
 */
public class BookCopyRepositoryMock implements BookCopyRepository {

    private final BookCopy bookCopy;

    /**
     * Constructor of the class. Creates the mock copy.
     */
    public BookCopyRepositoryMock() {
        bookCopy = new BookCopyMock();
    }


    @Override
    public Borrow addBorrow(final Request request, final BookCopy book) {
        final Borrow borrow = new BorrowMock();
        return borrow;
    }

    @Override
    public List<BookCopy> findAvailableBookCopies(final Book book) {
        if (book.getBookId() == 1) {
            final List<BookCopy> bookCopies = new ArrayList<>();
            bookCopies.add(bookCopy);
            return bookCopies;
        } else {
            return new ArrayList<>();
        }
    }

}
