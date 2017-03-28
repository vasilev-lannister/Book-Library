package com.csc.booklibrary.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.csc.booklibrary.persistence.interfaces.Book;
import com.csc.booklibrary.persistence.interfaces.BookRepository;
import com.csc.booklibrary.persistence.interfaces.Request;
import com.csc.booklibrary.persistence.interfaces.Type;
import com.csc.booklibrary.persistence.interfaces.User;
import com.csc.booklibrary.persistence.interfaces.UserRepository;
import com.csc.booklibrary.services.dto.RequestDTO;
import com.csc.booklibrary.services.exceptions.NoSuchBookIdException;
import com.csc.booklibrary.services.exceptions.NoSuchRequestException;
import com.csc.booklibrary.services.exceptions.NoSuchRequestTypeException;
import com.csc.booklibrary.services.exceptions.NoSuchUserIdException;

public class BookRequestServiceImplLogic {

    public List<RequestDTO> getPendingRequests(final BookRepository bookRepository) {
        final List<Request> requests = bookRepository.findPendingRequests();
        return convertToDTORequests(requests);
    }

    public List<RequestDTO> getPendingRequests(final long userId, final BookRepository bookRepository,
            final UserRepository userRepository) {
        final User user = userRepository.findUser(userId);
        if (user == null) {
            throw new NoSuchUserIdException(userId);
        }
        final List<Request> requests = bookRepository.findPendingRequests(user);
        return convertToDTORequests(requests);
    }

    public List<RequestDTO> getRequests(final BookRepository bookRepository) {
        final List<Request> requests = bookRepository.findRequests();
        return convertToDTORequests(requests);
    }

    public List<RequestDTO> getRequests(final long userId, final BookRepository bookRepository,
            final UserRepository userRepository) {
        final User user = userRepository.findUser(userId);
        if (user == null) {
            throw new NoSuchUserIdException(userId);
        }
        final List<Request> requests = bookRepository.findRequests(user);
        return convertToDTORequests(requests);
    }

    public RequestDTO getRequestById(final long id, final BookRepository bookRepository) {
        final Request request = bookRepository.findRequest(id);
        if (request == null) {
            throw new NoSuchRequestException(id);
        }
        return DTOConverter.convertRequest(request);
    }

    public Request createRequest(final long bookId, final int requestTypeId, final String comment, final long userId,
            final BookRepository bookRepository, final UserRepository usrRep) {
        final Book book = bookRepository.findBook(bookId);
        if (book == null) {
            throw new NoSuchBookIdException(bookId);
        }

        final Type reqType = getRequestType(requestTypeId);
        if (reqType == null) {
            throw new NoSuchRequestTypeException(requestTypeId);
        }

        if ((reqType != Type.EXTRACOPY && book.getNumberOfCopies() <= 0) || reqType == Type.SUGGESTION) {
            throw new NoSuchRequestTypeException(requestTypeId, book.getNumberOfCopies());
        }

        final User user = usrRep.findUser(userId);
        if (user == null) {
            throw new NoSuchUserIdException(userId);
        }

        final Request request = bookRepository.addRequest(user, book, reqType);
        request.setRequestComment(comment);
        return request;
    }

    private List<RequestDTO> convertToDTORequests(final List<Request> requests) {
        final List<RequestDTO> requestsDTO = new ArrayList<>();
        for (final Request r : requests) {
            requestsDTO.add(DTOConverter.convertRequest(r));
        }
        return requestsDTO;
    }

    private Type getRequestType(final long requestTypeId) {
        for (final Type re : Type.values()) {
            if (re.getTypeId() == requestTypeId) {
                return re;
            }
        }
        return null;
    }

}
