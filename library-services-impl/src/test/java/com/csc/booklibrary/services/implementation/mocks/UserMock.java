package com.csc.booklibrary.services.implementation.mocks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.csc.booklibrary.persistence.interfaces.Borrow;
import com.csc.booklibrary.persistence.interfaces.Comment;
import com.csc.booklibrary.persistence.interfaces.Message;
import com.csc.booklibrary.persistence.interfaces.Request;
import com.csc.booklibrary.persistence.interfaces.User;

/**
 *
 * A mock class of User used for testing.
 * 
 * @author mduhovnikov
 *
 */
public class UserMock implements User {

    private final int id;
    private String userName;
    private String password;
    private final List<Comment> comments = new ArrayList<>();
    private final List<Message> messages = new ArrayList<>();
    
    /**
     * Constructor of the class
     * 
     * @param id Mock id of the user.
     */
    public UserMock(final int id) {
        this.id = id;
    }
    
    /**
     * Second constructor of the class
     * 
     * @param id Mock id of the user.
     * @param userName Mock userName of the user.
     */
    public UserMock(final int id, final String userName) {
        this.id = id;
        this.userName = userName;
    }

    @Override
    public long getUserId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(final String password) {
        this.password = password;
    }

    @Override
    public String getFirstName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setFirstName(final String firstName) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getLastName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setLastName(final String lastName) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getUserRoleId() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setUserRoleId(final int userRoleId) {
        // TODO Auto-generated method stub

    }

    @Override
    public LocalDate getDateInactive() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setDateInactive(final LocalDate dateInactive) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getEmail() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setEmail(final String email) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getPhone() {
        return "666666";
    }

    @Override
    public void setPhone(final String phone) {
        // TODO Auto-generated method stub

    }

    @Override
    public byte[] getPicture() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setPicture(final byte[] picture) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public LocalDate getDateRegistered() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Message> getMessagesSent() {
        // TODO Auto-generated method stub
        return messages;
    }

    @Override
    public List<Message> getMessagesReceived() {
        // TODO Auto-generated method stub
        return messages;
    }

    @Override
    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public List<Request> getRequests() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addRequest(final Request req) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addMessageSent(final Message m) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addMessageReceived(final Message m) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addComment(final Comment comment) {
        comments.add(comment);
    }

    @Override
    public List<Borrow> getBorrowsReturned() {
        // TODO Auto-generated method stub
        return null;
    }

}
