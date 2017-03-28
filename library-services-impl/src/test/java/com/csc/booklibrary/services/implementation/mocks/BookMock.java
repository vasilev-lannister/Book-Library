package com.csc.booklibrary.services.implementation.mocks;

import java.util.ArrayList;
import java.util.List;

import com.csc.booklibrary.persistence.interfaces.Author;
import com.csc.booklibrary.persistence.interfaces.Book;
import com.csc.booklibrary.persistence.interfaces.Comment;
import com.csc.booklibrary.persistence.interfaces.Keyword;
import com.csc.booklibrary.persistence.interfaces.Publisher;
import com.csc.booklibrary.persistence.interfaces.Request;
import com.csc.booklibrary.persistence.interfaces.User;

/**
 * A mock class of Book used for testing.
 * 
 * @author mduhovnikov
 *
 */
public class BookMock implements Book {

    private final int id;
    private int numberOfCopies;
    
    /**
     * Constructor of the class
     * 
     * @param id Mock ID of the book.
     */
    public BookMock(final int id) {
        this.id = id;
    }

    @Override
    public List<Request> getRequests() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Comment> getComments() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addRequest(final Request req) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getEdition() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setEdition(final int edition) {
        // TODO Auto-generated method stub

    }

    @Override
    public Publisher getPublisher() {
        return new PublisherMock(1, "WtF Publishers");
    }

    @Override
    public int getYearPublished() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public byte[] getCoverPhoto() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setCoverPhoto(final byte[] coverPhoto) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getIsbn() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setIsbn(final String isbn) {
        // TODO Auto-generated method stub

    }

    @Override
    public long getCategoryId() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long getLanguageId() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    @Override
    public void setNumberOfCopies(final int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;

    }

    @Override
    public User getRegisteredBy() {

        return new UserMock(1);
    }

    @Override
    public long getBookId() {
        return id;
    }

    @Override
    public List<Author> getAuthors() {
        return new ArrayList<Author>();
    }

    @Override
    public void setAuthors(final List<Author> authors) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Keyword> getKeywords() {
        // TODO Auto-generated method stub
        return null;
    }

}
