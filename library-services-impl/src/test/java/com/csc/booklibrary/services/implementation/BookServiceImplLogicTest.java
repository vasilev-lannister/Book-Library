package com.csc.booklibrary.services.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.csc.booklibrary.persistence.interfaces.Author;
import com.csc.booklibrary.persistence.interfaces.Book;
import com.csc.booklibrary.persistence.interfaces.BookRepository;
import com.csc.booklibrary.persistence.interfaces.Publisher;
import com.csc.booklibrary.persistence.interfaces.UserRepository;
import com.csc.booklibrary.services.dto.BookDTO;
import com.csc.booklibrary.services.dto.CreateBookDTO;
import com.csc.booklibrary.services.exceptions.NoSuchBookIdException;
import com.csc.booklibrary.services.exceptions.NoSuchCategoryException;
import com.csc.booklibrary.services.exceptions.NoSuchLanguageException;
import com.csc.booklibrary.services.exceptions.NoSuchUserIdException;
import com.csc.booklibrary.services.impl.BookServiceImplLogic;
import com.csc.booklibrary.services.implementation.mocks.BookRepositoryMock;
import com.csc.booklibrary.services.implementation.mocks.UserRepositoryMock;

/**
 * This class tests the proper functionality of the addBook method in the
 * BookServiceImplLogic class for adding books.
 * 
 * @author mduhovnikov
 *
 */
public class BookServiceImplLogicTest {

    final BookServiceImplLogic bookService = new BookServiceImplLogic();
    private BookRepository bookRepository;
    private UserRepository userRepository;

    final List<String> authorsNames = new ArrayList<>();
    final String createdPublisherName = "SamplePublisher";
    final String existingPublisherName = "SamplePublisher1";

    /**
     * Initializing objects with new mock repositories before each test.
     */
    @Before
    public void initialize() {
        bookRepository = new BookRepositoryMock();
        userRepository = new UserRepositoryMock();
    }

    /**
     * This method tests the function when the selected publisher does not exist
     * and is created and then successfully creates a new book.
     */
    @Test
    public void testSuccessfulAdditionWithCreatedPublisher() {

        // given
        final CreateBookDTO createBookDTO = new CreateBookDTO("SampleName", "123-12345-12445-X", 2001, 1, 2, 6, 1);
        assertNull(bookRepository.findBook(4));
        assertNull(bookRepository.findPublisherByName(createdPublisherName));

        // when
        final Book b = bookService.addBook(createBookDTO, createdPublisherName, authorsNames, 2, 6, 12, userRepository,
                bookRepository);
        final Publisher p = bookRepository.findPublisherByName(createdPublisherName);

        // then
        final Book book = bookRepository.findBook(4);
        assertEquals(p.getName(), createdPublisherName);
        assertEquals(book, b);
    }

    /**
     * This method tests the function when the selected publisher exists and is
     * found and then successfully creates a new book.
     */
    @Test
    public void testSuccessfulAdditionWithExistingPublisher() {

        // given
        final CreateBookDTO createBookDTO = new CreateBookDTO("SampleName", "123-12345-12445-X", 2002, 2, 3, 4, 2);
        assertNull(bookRepository.findBook(4));
        assertNotNull(bookRepository.findPublisherByName(existingPublisherName));

        // when
        final Publisher p = bookRepository.findPublisherByName(existingPublisherName);
        final Book b = bookService.addBook(createBookDTO, existingPublisherName, authorsNames, 3, 4, 12, userRepository,
                bookRepository);

        // then
        final Book book = bookRepository.findBook(4);
        assertEquals(p.getName(), existingPublisherName);
        assertEquals(book, b);
    }

    /**
     * This method tests the function when there are two authors of the book,
     * one of which exists and is found and another one who does not exist and
     * is therefore created and then successfully creates a new book.
     */
    @Test
    public void testSuccessfulAdditionWithAuthors() {

        // given
        final String createdAuthorName = "SampleAuthor";
        final String existingAuthorName = "SampleAuthor1";
        final CreateBookDTO createBookDTO = new CreateBookDTO("SampleName", "123-12345-12445-X", 2006, 6, 5, 1, 3);
        assertTrue(authorsNames.add(createdAuthorName));
        assertTrue(authorsNames.add(existingAuthorName));
        assertNull(bookRepository.findBook(4));
        assertNotNull(bookRepository.findAuthorByName(existingAuthorName));
        assertNull(bookRepository.findAuthorByName(createdAuthorName));

        // when
        final Author existingAuthor = bookRepository.findAuthorByName(existingAuthorName);
        final Book b = bookService.addBook(createBookDTO, "publisherName", authorsNames, 3, 4, 12, userRepository,
                bookRepository);
        final Author createdAuthor = bookRepository.findAuthorByName(createdAuthorName);

        // then
        final Book book = bookRepository.findBook(4);
        assertEquals(existingAuthor.getName(), existingAuthorName);
        assertEquals(createdAuthor.getName(), createdAuthorName);
        assertEquals(book, b);
    }

    /**
     * This method tests the function when there is no user that can create the
     * new book. Should throw an appropriate exception.
     */
    @Test(expected = NoSuchUserIdException.class)
    public void testMissingUser() {

        // given
        final long missingRegisteredBy = 20;
        final CreateBookDTO createBookDTO = new CreateBookDTO("SampleName", "123-12345-12445-X", 2003, 3, 1, 5, 3);

        // when
        bookService.addBook(createBookDTO, createdPublisherName, authorsNames, 1, 5, missingRegisteredBy,
                userRepository, bookRepository);
    }

    /**
     * This method tests the function when there is no such category option for
     * creating a new book. Should throw an appropriate exception.
     */
    @Test(expected = NoSuchCategoryException.class)
    public void testMissingCategory() {

        // given
        final long missingCategoryId = 7;
        final CreateBookDTO createBookDTO = new CreateBookDTO("SampleName", "123-12345-12445-X", 2004, 4,
                missingCategoryId, 3, 4);

        // when
        bookService.addBook(createBookDTO, createdPublisherName, authorsNames, missingCategoryId, 3, 12, userRepository,
                bookRepository);
    }

    /**
     * This method tests the function when there is no such language option for
     * creating a new book. Should throw an appropriate exception.
     */
    @Test(expected = NoSuchLanguageException.class)
    public void testMissingLanguage() {

        // given
        final long missingLanguageId = 10;
        final CreateBookDTO createBookDTO = new CreateBookDTO("SampleName", "123-12345-12445-X", 2005, 5, 4,
                missingLanguageId, 5);

        // when
        bookService.addBook(createBookDTO, createdPublisherName, authorsNames, 4, missingLanguageId, 12, userRepository,
                bookRepository);
    }

    /**
     * This method tests the function when there is no book with the given id.
     * Should throw an appropriate exception.
     */
    @Test(expected = NoSuchBookIdException.class)
    public void testMissingBookById() {
        // given
        final long bookId = 7;

        // when
        bookService.getBookById(bookId, bookRepository);
    }

    /**
     * This method tests the function when a book is successfully found by its
     * id.
     */
    @Test
    public void testSuccessfulFoundBookById() {
        // given
        final long bookId = 2;

        // when
        final BookDTO book = bookService.getBookById(bookId, bookRepository);

        // then
        assertEquals(bookId, book.getBookId());
    }

    /**
     * This methods tests the function when all books are successfully found.
     */
    @Test
    public void testSuccessfulFoundBooks() {
        // when
        final List<BookDTO> books = bookService.getBooks(bookRepository);

        // then
        assertNotEquals(null, books);
    }
}
