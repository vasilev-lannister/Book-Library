package com.csc.booklibrary.services.implementation.mocks;

import java.time.LocalDate;

import com.csc.booklibrary.persistence.interfaces.BookCopy;
import com.csc.booklibrary.persistence.interfaces.Borrow;
import com.csc.booklibrary.persistence.interfaces.Request;
import com.csc.booklibrary.persistence.interfaces.User;

/**
 * A mock class of Borrow used for testing.
 * 
 * @author mduhovnikov
 *
 */
public class BorrowMock implements Borrow {

    @Override
    public LocalDate getFinalDate() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Request getRequest() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BookCopy getBookCopy() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LocalDate getDateTaken() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setDateTaken(LocalDate dateTaken) {
        // TODO Auto-generated method stub

    }

    @Override
    public LocalDate getDateReturned() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setFinalDate(LocalDate finalDate) {
        // TODO Auto-generated method stub

    }

    @Override
    public User getUser() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setUser(User user) {
        // TODO Auto-generated method stub

    }

}
