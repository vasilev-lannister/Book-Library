package com.csc.booklibrary.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.csc.booklibrary.persistence.interfaces.BookRepository;
import com.csc.booklibrary.persistence.interfaces.TransactionHandler;
import com.csc.booklibrary.persistence.interfaces.Type;
import com.csc.booklibrary.persistence.interfaces.UserRepository;
import com.csc.booklibrary.services.dto.RequestDTO;
import com.csc.booklibrary.services.dto.RequestTypeDTO;
import com.csc.booklibrary.services.interfaces.BookRequestService;

public final class BookRequestServiceImpl implements BookRequestService {

    private final TransactionHandler transactionHandler;

    private final BookRequestServiceImplLogic bookReqLogic = new BookRequestServiceImplLogic();

    public BookRequestServiceImpl(final TransactionHandler transactionHandler) {
        this.transactionHandler = transactionHandler;
    }

    @Override
    public List<RequestDTO> getPendingRequests() {
        return transactionHandler.execute(sf -> bookReqLogic.getPendingRequests(sf.findService(BookRepository.class)));
    }

    @Override
    public List<RequestDTO> getPendingRequests(final long userId) {
        return transactionHandler.execute(sf -> bookReqLogic.getPendingRequests(userId,
                sf.findService(BookRepository.class), sf.findService(UserRepository.class)));
    }

    @Override
    public List<RequestDTO> getRequests() {
        return transactionHandler.execute(sf -> bookReqLogic.getRequests(sf.findService(BookRepository.class)));
    }

    @Override
    public List<RequestDTO> getRequests(final long userId) {
        return transactionHandler.execute(sf -> bookReqLogic.getRequests(userId, sf.findService(BookRepository.class),
                sf.findService(UserRepository.class)));
    }

    @Override
    public RequestDTO getRequestById(final long id) {
        return transactionHandler.execute(sf -> bookReqLogic.getRequestById(id, sf.findService(BookRepository.class)));
    }

    @Override
    public void createRequest(final long bookId, final int requestTypeId, final String comment, final long userId) {
        transactionHandler.execute(sf -> bookReqLogic.createRequest(bookId, requestTypeId, comment, userId,
                sf.findService(BookRepository.class), sf.findService(UserRepository.class)));

    }

    @Override
    public List<RequestTypeDTO> getRequestTypes() {
        final List<RequestTypeDTO> rql = new ArrayList<>();
        for (final Type t : Type.values()) {
            rql.add(new RequestTypeDTO(t.getTypeId(), t.getTypeName()));
        }
        return rql;
    }

}
