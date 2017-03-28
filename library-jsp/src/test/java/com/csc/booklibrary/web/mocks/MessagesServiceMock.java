package com.csc.booklibrary.web.mocks;

import java.util.List;

import com.csc.booklibrary.exceptions.NoSuchUsernameException;
import com.csc.booklibrary.services.dto.MessagesDTO;
import com.csc.booklibrary.services.interfaces.MessagesService;

public class MessagesServiceMock implements MessagesService {

    TransactionHandlerMock handler;

    public MessagesServiceMock(final TransactionHandlerMock handler) {
        this.handler = handler;
    }

    @Override
    public List<MessagesDTO> getMessagesReceivedByUserId(long arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<MessagesDTO> getMessagesSentByUserId(long arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void sendMessage(long arg0, String username, String text) {
        if ("ivan23".equals(username)) {
            throw new NoSuchUsernameException(username);
        }

    }

}
