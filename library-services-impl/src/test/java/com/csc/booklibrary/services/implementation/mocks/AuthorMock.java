package com.csc.booklibrary.services.implementation.mocks;

import java.util.List;

import com.csc.booklibrary.persistence.interfaces.Author;
import com.csc.booklibrary.persistence.interfaces.Book;

/**
 * A mock class of Authors used for testing.
 * 
 * @author mduhovnikov
 *
 */
public class AuthorMock implements Author {

    private final int id;
    private final String name;

    /**
     * Constructor of the class.
     * 
     * @param id
     *            The id of the mock author.
     * @param name
     *            The name of the mock author.
     */
    public AuthorMock(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getAuthorId() {
        return id;
    }

    @Override
    public String getNationality() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getNickname() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setNationality(String nationality) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setNickname(String nickname) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Book> getBooksWritten() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setBooksWritten(List<Book> booksWritten) {
        // TODO Auto-generated method stub

    }

}
