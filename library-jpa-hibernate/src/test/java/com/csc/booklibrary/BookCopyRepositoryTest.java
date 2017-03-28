package com.csc.booklibrary;

import static org.junit.Assert.assertTrue;
import static org.unitils.reflectionassert.ReflectionAssert.assertPropertyLenientEquals;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;

import com.csc.booklibrary.persistence.interfaces.Book;
import com.csc.booklibrary.persistence.interfaces.BookCopy;
import com.csc.booklibrary.persistence.interfaces.BookCopyRepository;
import com.csc.booklibrary.persistence.interfaces.BookRepository;
import com.csc.booklibrary.persistence.interfaces.Borrow;
import com.csc.booklibrary.persistence.interfaces.Request;
import com.csc.booklibrary.persistence.interfaces.TransactionHandler;
import com.csc.booklibrary.repositories.TransactionHandlerJPA;

/**
 * This class tests the proper functionality of the BookCopyRepository class.
 *
 * @author lbosilkov
 *
 */
public class BookCopyRepositoryTest extends UnitilsJUnit4 {
    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("library-jpa-hibernate");
    static TransactionHandler handler = new TransactionHandlerJPA(entityManagerFactory);

    /**
     * Find book and searching for available book copies of this book.
     * The returned list has only one book copy.
     */
    @Test
    @DataSet({ "bookCopy/createBookWithOneAvailableBookCopy.xml", "clean.xml" })
    public void findBookCopyByBook() {
        final List<BookCopy> bookCopies = handler.execute(serviceFactory -> {
            final BookRepository bookRepository = serviceFactory.findService(BookRepository.class);
            final Book book = bookRepository.findBook(1);
            final BookCopyRepository bookCopyRepository = serviceFactory.findService(BookCopyRepository.class);
            return bookCopyRepository.findAvailableBookCopies(book);
        });
        assert bookCopies.size() == 1;
        final BookCopy bookCopy = bookCopies.get(0);
        assertPropertyLenientEquals("bookCopyId", 1, bookCopy);
        assertPropertyLenientEquals("taken", 0, bookCopy);
    }

    /**
     * Find book and searching for available book copies of this book.
     * The returned list has two book copies.
     */
    @Test
    @DataSet({ "bookCopy/createBookWithTwoAvailableBookCopies.xml", "clean.xml" })
    public void findBookCopiesByBook() {
        final List<BookCopy> bookCopies = handler.execute(serviceFactory -> {
            final BookRepository bookRepository = serviceFactory.findService(BookRepository.class);
            final Book book = bookRepository.findBook(1);
            final BookCopyRepository bookCopyRepository = serviceFactory.findService(BookCopyRepository.class);
            return bookCopyRepository.findAvailableBookCopies(book);
        });
        assert bookCopies.size() == 2;
        final BookCopy bookCopy1 = bookCopies.get(0);
        assertPropertyLenientEquals("bookCopyId", 1, bookCopy1);
        assertPropertyLenientEquals("taken", 0, bookCopy1);
        final BookCopy bookCopy2 = bookCopies.get(0);
        assertPropertyLenientEquals("bookCopyId", 1, bookCopy2);
        assertPropertyLenientEquals("taken", 0, bookCopy2);
    }

    /**
     * Find book and searching for available book copies of this book.
     * The book doesn't have available book copies.
     */
    @Test
    @DataSet({ "bookCopy/createBookWithoutAvailableBookCopy.xml", "clean.xml" })
    public void noAvailableBookCopy(){
        final List<BookCopy> bookCopies = handler.execute(serviceFactory -> {
            final BookRepository bookRepository = serviceFactory.findService(BookRepository.class);
            final Book book = bookRepository.findBook(1);
            final BookCopyRepository bookCopyRepository = serviceFactory.findService(BookCopyRepository.class);
            return bookCopyRepository.findAvailableBookCopies(book);
        });
        assertTrue(bookCopies.isEmpty());
    }

    /**
     * Execute addBorrow method with correct data and check database.
     */
    @Test
    @DataSet({ "bookCopy/createBookCopyAndRequest.xml", "clean.xml" })
    @ExpectedDataSet("bookCopy/expectedBorrow.xml")
    public void borrowBookCheckInDatabase(){
        handler.execute(serviceFactory -> {
            final BookRepository bookRepository = serviceFactory.findService(BookRepository.class);
            final Request request = bookRepository.findRequest(1);
            final Book book = bookRepository.findBook(1);
            final BookCopyRepository bookCopyRepository = serviceFactory.findService(BookCopyRepository.class);
            final List<BookCopy> bookCopies = bookCopyRepository.findAvailableBookCopies(book);
            assert bookCopies.size() == 1;
            final BookCopy bookCopy = bookCopies.get(0);
            bookCopyRepository.addBorrow(request, bookCopy);
            return null;
        });
    }

    /**
     * Execute addBorrow method with correct data and check the returned object.
     */
    @Test
    @DataSet({ "bookCopy/createBookCopyAndRequest.xml", "clean.xml" })
    public void borrowBookCheckReturnedObject(){
        handler.execute(serviceFactory -> {
            final BookRepository bookRepository = serviceFactory.findService(BookRepository.class);
            final Request request = bookRepository.findRequest(1);
            final Book book = bookRepository.findBook(1);
            final BookCopyRepository bookCopyRepository = serviceFactory.findService(BookCopyRepository.class);
            final List<BookCopy> bookCopies = bookCopyRepository.findAvailableBookCopies(book);
            assert bookCopies.size() == 1;
            final BookCopy bookCopy = bookCopies.get(0);
            final Borrow borrow = bookCopyRepository.addBorrow(request, bookCopy);
            assertPropertyLenientEquals("request", request, borrow);
            assertPropertyLenientEquals("bookCopy", bookCopy, borrow);
            return null;
        });
    }

    /**
     * Execute addBorrow method with non-existing request.
     */
    @Test(expected = AssertionError.class)
    @DataSet({ "bookCopy/createBookCopyAndRequest.xml", "clean.xml" })
    public void borrowBookWithNonExistingRequest(){
        handler.execute(serviceFactory -> {
            final BookRepository bookRepository = serviceFactory.findService(BookRepository.class);
            final Request request = bookRepository.findRequest(12);
            final Book book = bookRepository.findBook(1);
            final BookCopyRepository bookCopyRepository = serviceFactory.findService(BookCopyRepository.class);
            final List<BookCopy> bookCopies = bookCopyRepository.findAvailableBookCopies(book);
            final BookCopy bookCopy = bookCopies.get(0);
            bookCopyRepository.addBorrow(request, bookCopy);
            return null;
        });
    }

    /**
     * Execute addBorrow method with request for non-existing book.
     * Expected findAvailableBookCopies() to throw an exception.
     */
    @Test(expected = AssertionError.class)
    @DataSet({ "bookCopy/createBookCopyAndRequest.xml", "clean.xml" })
    public void borrowBookWithNonExistingBook(){
        handler.execute(serviceFactory -> {
            final BookRepository bookRepository = serviceFactory.findService(BookRepository.class);
            final Book book = bookRepository.findBook(111);
            final BookCopyRepository bookCopyRepository = serviceFactory.findService(BookCopyRepository.class);
            bookCopyRepository.findAvailableBookCopies(book);
            return null;
        });
    }

    /**
     * Execute addBorrow method with request for non-available book copy.
     * Expected the list with available book to be empty.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    @DataSet({ "bookCopy/createBookWithoutAvailableBookCopyAndRequest.xml", "clean.xml" })
    public void borrowBookWithAvailableCopy(){
        handler.execute(serviceFactory -> {
            final BookRepository bookRepository = serviceFactory.findService(BookRepository.class);
            final Book book = bookRepository.findBook(1);
            final BookCopyRepository bookCopyRepository = serviceFactory.findService(BookCopyRepository.class);
            final List<BookCopy> bookCopies = bookCopyRepository.findAvailableBookCopies(book);
            bookCopies.get(0);
            return null;
        });
    }
}
