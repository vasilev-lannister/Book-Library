package com.csc.booklibrary.web.mocks;

import java.time.LocalDate;

import com.csc.booklibrary.services.interfaces.BorrowBookService;

public class BorrowBookServiceMock implements BorrowBookService {

    public BorrowBookServiceMock(final TransactionHandlerMock handler) {
    }

    @Override
    public void borrowBook(long requestId, LocalDate finalDate, long adminId) {
    }

}
