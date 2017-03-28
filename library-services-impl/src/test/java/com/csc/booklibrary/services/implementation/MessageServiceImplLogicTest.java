package com.csc.booklibrary.services.implementation;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.csc.booklibrary.exceptions.NoSuchUsernameException;
import com.csc.booklibrary.persistence.interfaces.Message;
import com.csc.booklibrary.persistence.interfaces.UserRepository;
import com.csc.booklibrary.services.dto.MessagesDTO;
import com.csc.booklibrary.services.exceptions.NoSuchUserIdException;
import com.csc.booklibrary.services.impl.MessagesServiceImplLogic;
import com.csc.booklibrary.services.implementation.mocks.UserRepositoryMock;

public class MessageServiceImplLogicTest {

    @Test
    public void sendSuccessful() {
        // given
        final long senderId = 1;
        final String receiverUsername = "Jo75";
        final String messageText = "Testing MessageServiceImplLogic";
        final MessagesServiceImplLogic messageServiceImplLogic = new MessagesServiceImplLogic();
        final UserRepository userRepositoryMock = new UserRepositoryMock();
        // when
        messageServiceImplLogic.sendMessage(senderId, receiverUsername, messageText, userRepositoryMock);
        // then
        final List<Message> receivedMessages = userRepositoryMock.findMessagesReceivedByUserId(22);
        final Message lastMessage = receivedMessages.get(0);

        Assert.assertEquals("Testing MessageServiceImplLogic", lastMessage.getMessageText());
    }

    @Test(expected = NoSuchUsernameException.class)
    public void NonExistingReceiver() {
        // given
        final long senderId = 1;
        final String receiverUsername = "Noone";
        final String messageText = "Testing MessageServiceImplLogic";
        final MessagesServiceImplLogic messageServiceImplLogic = new MessagesServiceImplLogic();
        final UserRepository userRepositoryMock = new UserRepositoryMock();
        // when
        messageServiceImplLogic.sendMessage(senderId, receiverUsername, messageText, userRepositoryMock);
    }

    @Test(expected = NoSuchUserIdException.class)
    public void NonExistingSender() {
        // given
        final long senderId = 117;
        final String receiverUsername = "Jo75";
        final String messageText = "Testing MessageServiceImplLogic";
        final MessagesServiceImplLogic messageServiceImplLogic = new MessagesServiceImplLogic();
        final UserRepository userRepositoryMock = new UserRepositoryMock();
        // when
        messageServiceImplLogic.sendMessage(senderId, receiverUsername, messageText, userRepositoryMock);
    }

    @Test
    public void getMessagesByReceiverIdExisting() {
        // given
        final MessagesServiceImplLogic messageServiceImplLogic = new MessagesServiceImplLogic();
        final long receiverId = 22;
        final UserRepository userRepositoryMock = new UserRepositoryMock();
        // when
        final List<MessagesDTO> returnedMessages = messageServiceImplLogic.getMessagesReceivedByUserId(receiverId,
                userRepositoryMock);
        // then
        Assert.assertNotEquals(null, returnedMessages);
    }

    @Test
    public void getMessagesByReceiverIdNonExisting() {
        // given
        final MessagesServiceImplLogic messageServiceImplLogic = new MessagesServiceImplLogic();
        final long receiverId = 1912;
        final UserRepository userRepositoryMock = new UserRepositoryMock();
        // when
        final List<MessagesDTO> returnedMessages = messageServiceImplLogic.getMessagesReceivedByUserId(receiverId,
                userRepositoryMock);
        // then
        Assert.assertNotEquals(null, returnedMessages);
    }

    @Test
    public void getMessagesBySenderIdExisting() {
        // given
        final MessagesServiceImplLogic messageServiceImplLogic = new MessagesServiceImplLogic();
        final long senderId = 22;
        final UserRepository userRepositoryMock = new UserRepositoryMock();
        // when
        final List<MessagesDTO> returnedMessages = messageServiceImplLogic.getMessagesSentByUserId(senderId,
                userRepositoryMock);
        // then
        Assert.assertNotEquals(null, returnedMessages);
    }

    @Test
    public void getMessagesBySenderIdNonExisting() {
        // given
        final MessagesServiceImplLogic messageServiceImplLogic = new MessagesServiceImplLogic();
        final long senderId = 1912;
        final UserRepository userRepositoryMock = new UserRepositoryMock();
        // when
        final List<MessagesDTO> returnedMessages = messageServiceImplLogic.getMessagesSentByUserId(senderId,
                userRepositoryMock);
        // then
        Assert.assertNotEquals(null, returnedMessages);
    }

}
