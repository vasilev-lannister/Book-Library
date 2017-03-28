package com.csc.booklibrary.services.implementation.mocks;

import java.time.LocalDate;
import java.util.List;

import com.csc.booklibrary.persistence.interfaces.Book;
import com.csc.booklibrary.persistence.interfaces.BookCopy;
import com.csc.booklibrary.persistence.interfaces.Borrow;
import com.csc.booklibrary.persistence.interfaces.User;

/**
 * A mock class of BookCopy used for testing.
 * 
 * @author mduhovnikov
 *
 */
public class BookCopyMock implements BookCopy {

    @Override
    public Book getBook() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setBook(final Book book) {
        // TODO Auto-generated method stub

    }

    private int taken;

    @Override
    public LocalDate getDateRemoved() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LocalDate getDateAdded() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getTaken() {
        return taken;
    }

    @Override
    public void setTaken(final int taken) {
        this.taken = taken;
    }

    @Override
    public void setDateRemoved(final LocalDate dateRemoved) {
        // TODO Auto-generated method stub

    }

    @Override
    public User getRegisteredBy() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setRegisteredBy(final User registeredBy) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Borrow> getBookCopyBorrows() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getBookCopyId() {
        // TODO Auto-generated method stub
        return 0;
    }

}
