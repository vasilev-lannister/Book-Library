package com.csc.booklibrary.services.implementation;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csc.booklibrary.persistence.interfaces.Book;
import com.csc.booklibrary.persistence.interfaces.BookCopy;
import com.csc.booklibrary.persistence.interfaces.BookCopyRepository;
import com.csc.booklibrary.persistence.interfaces.BookRepository;
import com.csc.booklibrary.persistence.interfaces.UserRepository;
import com.csc.booklibrary.services.exceptions.NoFreeCopyOfBookException;
import com.csc.booklibrary.services.exceptions.NoSuchRequestException;
import com.csc.booklibrary.services.exceptions.NoSuchUserIdException;
import com.csc.booklibrary.services.exceptions.RequestAlreadyAnsweredException;
import com.csc.booklibrary.services.impl.BorrowBookServiceImplLogic;
import com.csc.booklibrary.services.implementation.mocks.BookCopyRepositoryMock;
import com.csc.booklibrary.services.implementation.mocks.BookRepositoryMock;
import com.csc.booklibrary.services.implementation.mocks.UserRepositoryMock;

/**
 * This class tests the functionality of the borrowBook method in the.
 * BorrowBookServiceImplLogic class for borrowing a book.
 *
 * @author lbosilkov
 *
 */
public class BorrowBookServiceImplLogicTest {
    private final BorrowBookServiceImplLogic borrowService = new BorrowBookServiceImplLogic();
    private BookRepository bookRepository;
    private BookCopyRepository bookCopyRepository;
    private UserRepository userRepository;

    /**
     * Initializing objects with new mock repositories before each test.
     */
    @Before
    public void initialize() {
        bookRepository = new BookRepositoryMock();
        bookCopyRepository = new BookCopyRepositoryMock();
        userRepository = new UserRepositoryMock();
    }

    /**
     * Test with correct data.
     */
    @Test
    public void successfulBorrowing() {
        final long requestId = 123;
        final long adminId = 12;
        final LocalDate finalDate = LocalDate.now();
        borrowService.borrowBook(requestId, finalDate, adminId, bookCopyRepository, userRepository, bookRepository);
        final Book book = bookRepository.findBook(1);
        final List<BookCopy> bookCopies = bookCopyRepository.findAvailableBookCopies(book);
        assert bookCopies.size() == 1;
        final BookCopy bookCopy = bookCopies.get(0);
        Assert.assertEquals(1, bookCopy.getTaken());
    }

    /**
     * Test with wrong requestId. NoSuchRequestException exception is expected.
     */
    @Test(expected = NoSuchRequestException.class)
    public void noSuchRequestFound() {
        final long requestId = 15;
        final long adminId = 7;
        final LocalDate finalDate = LocalDate.now();
        borrowService.borrowBook(requestId, finalDate, adminId, bookCopyRepository, userRepository, bookRepository);
    }

    /**
     * Tests the functionality when there is no free copy of the book.
     * NoFreeCopyOfBookException exception is expected.
     */
    @Test(expected = NoFreeCopyOfBookException.class)
    public void noFreeCopiesFound() {
        final long requestId = 567;
        final long adminId = 7;
        final LocalDate finalDate = LocalDate.now();
        borrowService.borrowBook(requestId, finalDate, adminId, bookCopyRepository, userRepository, bookRepository);
    }

    /**
     * Tests the functionality when wrong admindId is passed.
     * NoSuchUserIdException exception is expected.
     */
    @Test(expected = NoSuchUserIdException.class)
    public void noSuchAdminFound() {
        final long requestId = 123;
        final long adminId = 2;
        final LocalDate finalDate = LocalDate.now();
        borrowService.borrowBook(requestId, finalDate, adminId, bookCopyRepository, userRepository, bookRepository);
    }

    /**
     * Tests the functionality when request has already been answered.
     * RequestAlreadyAnsweredException exception is expected.
     */
    @Test(expected = RequestAlreadyAnsweredException.class)
    public void requestAlreadyBeenAnswered() {
        final long requestId = 89;
        final long adminId = 12;
        final LocalDate finalDate = LocalDate.now();
        borrowService.borrowBook(requestId, finalDate, adminId, bookCopyRepository, userRepository, bookRepository);
    }
}
