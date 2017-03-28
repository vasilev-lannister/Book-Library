package com.csc.booklibrary.services.impl;

import java.time.LocalDate;

import com.csc.booklibrary.persistence.interfaces.BookCopyRepository;
import com.csc.booklibrary.persistence.interfaces.BookRepository;
import com.csc.booklibrary.persistence.interfaces.TransactionHandler;
import com.csc.booklibrary.persistence.interfaces.UserRepository;
import com.csc.booklibrary.services.interfaces.BorrowBookService;

/**
 * @see BorrowBookService
 *
 * @author lbosilkov
 *
 */
public final class BorrowBookServiceImpl implements BorrowBookService {
    private final TransactionHandler transactionHandler;
    private final BorrowBookServiceImplLogic borrowBookServiceImplLogic = new BorrowBookServiceImplLogic();

    /**
     * Constructor.
     * @param handler
     */
    public BorrowBookServiceImpl(final TransactionHandler handler) {
        this.transactionHandler = handler;
    }

    @Override
    public void borrowBook(final long requestId, final LocalDate finalDate, final long adminId) {
        transactionHandler.execute(servicesFactory -> {
            borrowBookServiceImplLogic.borrowBook(requestId, finalDate, adminId,
                    servicesFactory.findService(BookCopyRepository.class),
                    servicesFactory.findService(UserRepository.class),
                    servicesFactory.findService(BookRepository.class));
            return null;
        });
    }
}
