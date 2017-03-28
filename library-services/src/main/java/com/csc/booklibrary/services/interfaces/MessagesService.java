package com.csc.booklibrary.services.interfaces;

import java.util.List;

import com.csc.booklibrary.services.dto.MessagesDTO;

/**
 *
 * A service that gets messages by receiver id and transfers them from the
 * persistence.
 *
 * @author lbosilkov
 *
 */
public interface MessagesService {
    /**
     * Gets all messages by receiver id.
     *
     * @param userId The id of the receiver.
     * @return A list of messages.
     */
    List<MessagesDTO> getMessagesReceivedByUserId(long userId);

    /**
     * Gets all messages, sent from the user with the given id.
     * 
     * @param userId
     *            The id of the sending user.
     * @return A list of the sent messages.
     */
    List<MessagesDTO> getMessagesSentByUserId(long userId);

    /**
     *
     * @param senderId
     *            the Id of the sender
     * @param receiverUsername
     *            The user name of the receiver
     * @param messageText
     *            The contained text of the message
     */
    void sendMessage(long senderId, String receiverUsername, String messageText);

}
