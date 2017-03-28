package com.csc.booklibrary.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.csc.booklibrary.exceptions.NoSuchUsernameException;
import com.csc.booklibrary.persistence.interfaces.Message;
import com.csc.booklibrary.persistence.interfaces.User;
import com.csc.booklibrary.persistence.interfaces.UserRepository;
import com.csc.booklibrary.services.dto.MessagesDTO;
import com.csc.booklibrary.services.exceptions.NoSuchUserIdException;
import com.csc.booklibrary.services.interfaces.MessagesService;

/**
 * @see MessagesService
 *
 * @author mvasilev
 *
 */
public class MessagesServiceImplLogic {
    /**
     * @author dgetsov
     * @param receiverId
     *            The id of the receiver, id >= 0
     * @param userRepository
     *            UserRepository instance, not null
     * @return List of DTOMessage objects, can be empty if messages not found
     */
    public List<MessagesDTO> getMessagesReceivedByUserId(final long receiverId, final UserRepository userRepository) {
        return getMessages(receiverId, userRepository, user -> user.getMessagesReceived());
    }

    public List<MessagesDTO> getMessagesSentByUserId(final long senderId, final UserRepository userRepository) {
        return getMessages(senderId, userRepository, user -> user.getMessagesSent());
    }

    /**
     * @author dgetsov Sends a message to an existing user
     * @param senderId
     *            The id of the sender, id >= 0
     * @param receiverUsername
     *            The username of the receiver, not null
     * @param messageText
     *            Not null
     * @param userRepository
     *            UserRepository instance, not null
     */
    public void sendMessage(final long senderId, final String receiverUsername, final String messageText,
            final UserRepository userRepository) {
        assert senderId >= 0;
        assert receiverUsername != null;
        assert messageText != null;
        assert userRepository != null;
        final User receiver = userRepository.findUserByUsername(receiverUsername);
        if (receiver == null) {
            throw new NoSuchUsernameException(receiverUsername);
        }
        final User sender = userRepository.findUser(senderId);
        if (sender == null) {
            throw new NoSuchUserIdException(senderId);
        }
        userRepository.addMessage(receiver, messageText, sender);
    }

    private List<MessagesDTO> getMessages(final long userId, final UserRepository userRepository,
            final Function<User, List<Message>> function) {
        final User user = userRepository.findUser(userId);
        if (user == null) {
            return new ArrayList<>();
        }

        return function.apply(user).stream().map(DTOConverter::convertMessage).collect(Collectors.toList());
    }
}
