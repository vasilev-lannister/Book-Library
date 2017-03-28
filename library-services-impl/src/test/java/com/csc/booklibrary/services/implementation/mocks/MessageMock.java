package com.csc.booklibrary.services.implementation.mocks;

import java.time.LocalDateTime;

import com.csc.booklibrary.persistence.interfaces.Message;
import com.csc.booklibrary.persistence.interfaces.User;

/**
 * A mock class of Message used for testing.
 * 
 * @author mduhovnikov
 *
 */
public class MessageMock implements Message {

    private User fromUser;
    private User toUser;
    private String messageText;
    
    /**
     * Constructor of the class.
     * 
     * @param fromUser The user from which the mock message is.
     * @param messageText The text of the mock message.
     * @param toUser The user to which the mock message is.
     */
    public MessageMock(User fromUser, String messageText, User toUser) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.messageText = messageText;
    }

    @Override
    public long getMessageId() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public User getToUser() {
        return toUser;
    }

    @Override
    public String getMessageText() {
        return messageText;
    }

    @Override
    public User getFromUser() {
        return fromUser;
    }

    @Override
    public LocalDateTime getMessageDate() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LocalDateTime getReadDate() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setReadDate(LocalDateTime arg0) {
        // TODO Auto-generated method stub

    }
}
