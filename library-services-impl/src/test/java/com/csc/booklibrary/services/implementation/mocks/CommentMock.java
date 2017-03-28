package com.csc.booklibrary.services.implementation.mocks;

import java.time.LocalDateTime;
import java.util.List;

import com.csc.booklibrary.persistence.interfaces.Book;
import com.csc.booklibrary.persistence.interfaces.Comment;
import com.csc.booklibrary.persistence.interfaces.User;

/**
 * A mock class of Comment used for testing.
 * 
 * @author mduhovnikov
 *
 */
public class CommentMock implements Comment {

    private final User user;
    private final Book book;
    private final String content;
    
    /**
     * Constructor of the class.
     * 
     * @param user The user who made the mock comment.
     * @param book The book for which the mock comment was made.
     * @param content The content of the mock comment.
     */
    public CommentMock(final User user, final Book book, final String content) {
        this.user = user;
        this.book = book;
        this.content = content;
    }

    @Override
    public void addComments(final Comment arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public Book getBook() {
        return book;
    }

    @Override
    public LocalDateTime getCommentDate() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Comment> getComments() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public long getId() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Comment getParent() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User getUser() {
        return user;
    }

}
