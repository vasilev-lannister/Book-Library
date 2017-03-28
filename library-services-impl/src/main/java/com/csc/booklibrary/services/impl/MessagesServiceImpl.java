package com.csc.booklibrary.services.impl;

import java.util.List;

import com.csc.booklibrary.persistence.interfaces.TransactionHandler;
import com.csc.booklibrary.persistence.interfaces.UserRepository;
import com.csc.booklibrary.services.dto.MessagesDTO;
import com.csc.booklibrary.services.interfaces.MessagesService;

/**
 * @see MessagesService
 *
 * @author lbosilkov
 *
 */
public class MessagesServiceImpl implements MessagesService {
    private final TransactionHandler transactionHandler;
    private final MessagesServiceImplLogic messagesServiceImplLogic = new MessagesServiceImplLogic();

    public MessagesServiceImpl(final TransactionHandler transactionHandler) {
        this.transactionHandler = transactionHandler;
    }

    @Override
    public List<MessagesDTO> getMessagesReceivedByUserId(final long userId) {
        return transactionHandler.execute(servicesFactory -> {
            return messagesServiceImplLogic.getMessagesReceivedByUserId(userId,
                    servicesFactory.findService(UserRepository.class));
        });

    }

    @Override
    public List<MessagesDTO> getMessagesSentByUserId(final long userId) {
        return transactionHandler.execute(servicesFactory -> {
            return messagesServiceImplLogic.getMessagesSentByUserId(userId,
                    servicesFactory.findService(UserRepository.class));
        });
    }

    @Override
    public void sendMessage(final long senderId, final String receiverUsername, final String messageText) {

        transactionHandler.execute(servicesFactory -> {
            messagesServiceImplLogic.sendMessage(senderId, receiverUsername, messageText,
                    servicesFactory.findService(UserRepository.class));
            return null;
        });
    }
}
