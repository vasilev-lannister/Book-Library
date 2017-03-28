package com.csc.booklibrary.services.implementation.mocks;

import java.time.LocalDateTime;

import com.csc.booklibrary.persistence.interfaces.Book;
import com.csc.booklibrary.persistence.interfaces.Borrow;
import com.csc.booklibrary.persistence.interfaces.RejectReason;
import com.csc.booklibrary.persistence.interfaces.Request;
import com.csc.booklibrary.persistence.interfaces.User;

/**
 * A mock class of Request used for testing.
 * 
 * @author mduhovnikov
 *
 */
public class RequestMock implements Request {

    private Book book;
    private String comment;
    private int typeId;
    private long requestId;
    private final User user;
    private LocalDateTime dateAnswered;
    
    /**
     * Constructor of the class.
     * 
     * @param user The user who has made the mock request.
     * @param book The book about which the mock request has been made.
     * @param typeId The id of the mock request.
     */
    public RequestMock(final User user, final Book book, final int typeId) {
        this.user = user;
        this.book = book;
        this.typeId = typeId;
    }

    @Override
    public String getRequestComment() {
        return comment;
    }

    @Override
    public long getRequestId() {
        return requestId;
    }

    @Override
    public void setRequestId(final long requestId) {
        this.requestId = requestId;
    }

    @Override
    public Book getBook() {
        return book;
    }

    @Override
    public int getTypeId() {
        return typeId;
    }

    @Override
    public void setTypeId(final int typeId) {
        this.typeId = typeId;

    }

    @Override
    public LocalDateTime getDateMade() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User getAdmin() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setAdmin(final User admin) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setRequestComment(final String requestComment) {
        this.comment = requestComment;

    }

    @Override
    public RejectReason getRejectReason() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setRejectReasonId(final RejectReason rejectReason) {
        // TODO Auto-generated method stub

    }

    @Override
    public LocalDateTime getDateAnswered() {
        return this.dateAnswered;
    }

    @Override
    public void setDateAnswered(final LocalDateTime dateAnswered) {
        this.dateAnswered = dateAnswered;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public Borrow getBorrow() {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * Sets a new book content for the book.
     * 
     * @param book The book for which the content is sent.
     */
    public void setBookContent(final Book book) {
        this.book = book;
    }

}
