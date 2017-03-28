package com.csc.booklibrary.services.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import com.csc.booklibrary.persistence.interfaces.Request;
import com.csc.booklibrary.persistence.interfaces.Type;
import com.csc.booklibrary.services.dto.RequestDTO;
import com.csc.booklibrary.services.exceptions.NoSuchBookIdException;
import com.csc.booklibrary.services.exceptions.NoSuchRequestException;
import com.csc.booklibrary.services.exceptions.NoSuchRequestTypeException;
import com.csc.booklibrary.services.exceptions.NoSuchUserIdException;
import com.csc.booklibrary.services.impl.BookRequestServiceImplLogic;
import com.csc.booklibrary.services.impl.DTOConverter;
import com.csc.booklibrary.services.implementation.mocks.BookRepositoryMock;
import com.csc.booklibrary.services.implementation.mocks.UserRepositoryMock;

public class BookRequestServiceImplLogicTest {

    @Test(expected = NoSuchBookIdException.class)
    public void noSuchBookContentFound() {
        // before
        final BookRequestServiceImplLogic bookReqServImplLogic = new BookRequestServiceImplLogic();
        final long bookId = 12;
        final int requestTypeId = Type.EXTRACOPY.getTypeId();
        final String comment = "This is comment";
        final long userId = 7;

        final BookRepositoryMock bookRepositoryMock = new BookRepositoryMock();
        final UserRepositoryMock userRepositoryMock = new UserRepositoryMock();

        // when
        bookReqServImplLogic.createRequest(bookId, requestTypeId, comment, userId, bookRepositoryMock,
                userRepositoryMock);
    }

    @Test(expected = NoSuchRequestTypeException.class)
    public void noSuchRequestTypeFound() {
        // before
        final BookRequestServiceImplLogic bookReqServImplLogic = new BookRequestServiceImplLogic();
        final long bookId = 1;
        final int requestTypeId = 50;
        final String comment = "This is comment";
        final long userId = 7;

        final BookRepositoryMock bookRepositoryMock = new BookRepositoryMock();
        final UserRepositoryMock userRepositoryMock = new UserRepositoryMock();
        // when
        bookReqServImplLogic.createRequest(bookId, requestTypeId, comment, userId, bookRepositoryMock,
                userRepositoryMock);
    }

    @Test(expected = NoSuchRequestTypeException.class)
    public void bookQuantityIsZeroAndRequestTypeNotExtraCopy() {
        // before
        final BookRequestServiceImplLogic brSrvcImpl = new BookRequestServiceImplLogic();
        final long bookId = 2;
        final int requestTypeId = Type.PERSONAL.getTypeId();
        final String comment = "This is comment";
        final long userId = 7;
        final BookRepositoryMock bookRepositoryMock = new BookRepositoryMock();
        final UserRepositoryMock userRepositoryMock = new UserRepositoryMock();
        // when
        brSrvcImpl.createRequest(bookId, requestTypeId, comment, userId, bookRepositoryMock, userRepositoryMock);
    }

    @Test(expected = NoSuchUserIdException.class)
    public void noSuchUser() {

        // before
        final BookRequestServiceImplLogic brSrvcImpl = new BookRequestServiceImplLogic();
        final long bookId = 1;
        final int requestTypeId = Type.PERSONAL.getTypeId();
        final String comment = "This is comment";
        final long userId = 11;
        final BookRepositoryMock bookRepositoryMock = new BookRepositoryMock();
        final UserRepositoryMock userRepositoryMock = new UserRepositoryMock();
        // when
        brSrvcImpl.createRequest(bookId, requestTypeId, comment, userId, bookRepositoryMock, userRepositoryMock);
    }

    @Test
    public void successCreateNewRequest() {
        // before
        final BookRequestServiceImplLogic brSrvcImpl = new BookRequestServiceImplLogic();
        final long bookId = 1;
        final int requestTypeId = Type.PERSONAL.getTypeId();
        final String comment = "This is comment";
        final long userId = 7;
        final BookRepositoryMock bookRepositoryMock = new BookRepositoryMock();
        final UserRepositoryMock userRepositoryMock = new UserRepositoryMock();
        // when
        final Request request = brSrvcImpl.createRequest(bookId, requestTypeId, comment, userId, bookRepositoryMock,
                userRepositoryMock);
        // after
        final long id = request.getRequestId();
        assertEquals(request, bookRepositoryMock.findRequest(id));
    }

    @Test(expected = NoSuchRequestException.class)
    public void cantFindRequestById() {
        // before
        final BookRequestServiceImplLogic brSrvcImpl = new BookRequestServiceImplLogic();
        final int id = 1;
        final BookRepositoryMock bcMock = new BookRepositoryMock();
        // when
        brSrvcImpl.getRequestById(id, bcMock);
    }

    @Test
    public void successFindRequestById() {
        // before
        final BookRequestServiceImplLogic brSrvcImpl = new BookRequestServiceImplLogic();
        final int id = 123;
        final BookRepositoryMock bcMock = new BookRepositoryMock();
        ;
        // when
        final RequestDTO reqDto = brSrvcImpl.getRequestById(id, bcMock);
        // after
        assertEquals(id, reqDto.getId());
    }

    @Test
    public void getAllRequest() {
        // before
        final BookRequestServiceImplLogic brSrvcImpl = new BookRequestServiceImplLogic();
        final BookRepositoryMock bcMock = new BookRepositoryMock();
        // when
        final List<RequestDTO> dtoList = brSrvcImpl.getRequests(bcMock);
        final List<RequestDTO> dtoListOriginal = new ArrayList<>();
        for (final Request req : bcMock.findRequests()) {
            dtoListOriginal.add(DTOConverter.convertRequest(req));
        }

        // after
        final Stream<RequestDTO> s = dtoList.stream();
        final Stream<RequestDTO> s2 = dtoListOriginal.stream();
        assertTrue(s.anyMatch(item1 -> s2.anyMatch(item2 -> item1.getId() == item2.getId())));

    }

    @Test
    public void getAllPendingRequest() {
        // before
        final BookRequestServiceImplLogic brSrvcImpl = new BookRequestServiceImplLogic();
        final BookRepositoryMock bcMock = new BookRepositoryMock();
        ;
        // when
        final List<RequestDTO> dtoList = brSrvcImpl.getPendingRequests(bcMock);
        final List<RequestDTO> dtoListOriginal = new ArrayList<>();
        for (final Request req : bcMock.findPendingRequests()) {
            dtoListOriginal.add(DTOConverter.convertRequest(req));
        }

        // after
        final Stream<RequestDTO> s = dtoList.stream();
        final Stream<RequestDTO> s2 = dtoListOriginal.stream();
        assertTrue(s.anyMatch(item1 -> s2.anyMatch(item2 -> item1.getId() == item2.getId())));

    }

}
