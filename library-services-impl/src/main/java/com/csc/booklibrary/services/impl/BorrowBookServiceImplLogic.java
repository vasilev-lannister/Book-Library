package com.csc.booklibrary.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.csc.booklibrary.persistence.interfaces.BookCopy;
import com.csc.booklibrary.persistence.interfaces.BookCopyRepository;
import com.csc.booklibrary.persistence.interfaces.BookRepository;
import com.csc.booklibrary.persistence.interfaces.Borrow;
import com.csc.booklibrary.persistence.interfaces.Request;
import com.csc.booklibrary.persistence.interfaces.User;
import com.csc.booklibrary.persistence.interfaces.UserRepository;
import com.csc.booklibrary.services.exceptions.NoFreeCopyOfBookException;
import com.csc.booklibrary.services.exceptions.NoSuchRequestException;
import com.csc.booklibrary.services.exceptions.NoSuchUserIdException;
import com.csc.booklibrary.services.exceptions.RequestAlreadyAnsweredException;

/***
 * Service for processing book borrowing.
 *
 * @author lbosilkov
 *
 */
public final class BorrowBookServiceImplLogic {

    /**
     * Method for borrowing a book.
     *
     * @param requestId The id of the request for borrowing.
     * @param finalDate The date on which the book must be returned.
     * @param adminId Non-null, the id of the admin who processed the request.
     * @param bookCopyRepository Non-null, repository for book copies.
     * @param userRepository Non-null, repository for users.
     * @param bookRepository Non-null, repository for books.
     * @throws RequestAlreadyAnsweredException thrown, when request has already been answered.
     * @throws NoSuchUserIdException thrown, when user cannot be found.
     * @throws NoFreeCopyOfBookException thrown, when there is no free copy of book.
     * @throws NoSuchRequestException thrown, when there is no such request.
     */
    public void borrowBook(final long requestId, final LocalDate finalDate, final long adminId,
            final BookCopyRepository bookCopyRepository, final UserRepository userRepository,
            final BookRepository bookRepository) {
        assert finalDate != null;
        assert bookCopyRepository != null;
        assert userRepository != null;
        assert bookRepository != null;
        final Request requestBook = getRequestBook(requestId, bookRepository);
        if (requestBook.getDateAnswered() != null) {
            throw new RequestAlreadyAnsweredException(requestId);
        }
        final BookCopy bookCopy = getAvailableCopy(requestBook, bookCopyRepository);
        borrowCopy(finalDate, adminId, userRepository, requestBook, bookCopyRepository, bookCopy);
    }

    private void borrowCopy(final LocalDate finalDate, final long adminId, final UserRepository userRepo,
            final Request request, final BookCopyRepository bookCopyRepository, final BookCopy bookCopy) {
        final Borrow newBorrow = bookCopyRepository.addBorrow(request, bookCopy);
        newBorrow.setDateTaken(LocalDate.now());
        newBorrow.setFinalDate(finalDate);
        bookCopy.setTaken(1);
        request.setDateAnswered(LocalDateTime.now());
        final User admin = getAdmin(adminId, userRepo);
        request.setAdmin(admin);
    }

    private User getAdmin(final long adminId, final UserRepository userRepository) {
        final User admin = userRepository.findUser(adminId);
        if (admin == null) {
            throw new NoSuchUserIdException(adminId);
        }
        return admin;
    }

    private BookCopy getAvailableCopy(final Request request, final BookCopyRepository bookRepository) {
        final List<BookCopy> bookCopies = bookRepository.findAvailableBookCopies(request.getBook());
        if (bookCopies.isEmpty()) {
            throw new NoFreeCopyOfBookException(request.getBook().getBookId());
        }
        return bookCopies.get(0);
    }

    private Request getRequestBook(final long requestId, final BookRepository bookRepository) {
        final Request request = bookRepository.findRequest(requestId);
        if (request == null) {
            throw new NoSuchRequestException(requestId);
        }
        return request;
    }
}
