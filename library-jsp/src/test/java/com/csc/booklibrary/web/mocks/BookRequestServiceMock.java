package com.csc.booklibrary.web.mocks;

import java.util.ArrayList;
import java.util.List;

import com.csc.booklibrary.services.dto.RequestDTO;
import com.csc.booklibrary.services.dto.RequestTypeDTO;
import com.csc.booklibrary.services.exceptions.NoSuchRequestTypeException;
import com.csc.booklibrary.services.exceptions.NoSuchUserIdException;
import com.csc.booklibrary.services.interfaces.BookRequestService;

public class BookRequestServiceMock implements BookRequestService {

    TransactionHandlerMock handler;

    public BookRequestServiceMock(final TransactionHandlerMock handler) {
        this.handler = handler;
    }

    @Override
    public List<RequestDTO> getRequests() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<RequestDTO> getRequests(final long userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<RequestDTO> getPendingRequests() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<RequestDTO> getPendingRequests(final long userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createRequest(final long bookContentId, final int reqestType, final String comment, final long userId) {
        if (reqestType < 1 || reqestType > 4) {
            throw new NoSuchRequestTypeException(reqestType);
        }
        if (userId != 2) {
            throw new NoSuchUserIdException(userId);
        }

    }

    @Override
    public RequestDTO getRequestById(final long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<RequestTypeDTO> getRequestTypes() {
        final List<RequestTypeDTO> reqList = new ArrayList<>();
        reqList.add(new RequestTypeDTO(2, "Personal Use"));
        reqList.add(new RequestTypeDTO(1, "Project Use"));
        reqList.add(new RequestTypeDTO(3, "Suggestion"));
        reqList.add(new RequestTypeDTO(4, "Extra Copy"));
        return reqList;
    }

}
