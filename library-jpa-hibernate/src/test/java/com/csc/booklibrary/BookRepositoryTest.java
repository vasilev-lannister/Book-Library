package com.csc.booklibrary;

import static org.junit.Assert.assertEquals;
import static org.unitils.reflectionassert.ReflectionAssert.assertPropertyLenientEquals;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;

import com.csc.booklibrary.persistence.interfaces.Book;
import com.csc.booklibrary.persistence.interfaces.BookRepository;
import com.csc.booklibrary.persistence.interfaces.Request;
import com.csc.booklibrary.persistence.interfaces.TransactionHandler;
import com.csc.booklibrary.persistence.interfaces.User;
import com.csc.booklibrary.persistence.interfaces.UserRepository;
import com.csc.booklibrary.repositories.TransactionHandlerJPA;

/**
 * Checks if findBook() returns correct result
 *
 * @author dgetsov
 */
public class BookRepositoryTest extends UnitilsJUnit4 {

    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("library-jpa-hibernate");
    static TransactionHandler handler = new TransactionHandlerJPA(entityManagerFactory);

    @SuppressWarnings("nls")
    @Test
    @DataSet({ "Book/createBook.xml", "clean.xml" })
    public void findByBookId() {
        final Book book = handler.execute(serviceFactory -> {
            final BookRepository bookRepository = serviceFactory.findService(BookRepository.class);
            return bookRepository.findBook(1);
        });
        assertPropertyLenientEquals("yearPublished", 1989, book);
        assertPropertyLenientEquals("name", "The coming of third reich", book);
        assertPropertyLenientEquals("isbn", "5f4b7y8r321ee8d4", book);
        assertPropertyLenientEquals("categoryId", 1, book);
        assertPropertyLenientEquals("languageId", 1, book);
        assertPropertyLenientEquals("numberOfCopies", 1, book);
    }

    @Test
    @DataSet({ "clean.xml" })
    public void findNonExsistingBook() {
        final Book book = handler.execute(serviceFactory -> {
            final BookRepository bookRepository = serviceFactory.findService(BookRepository.class);
            return bookRepository.findBook(132);
        });
        assertReflectionEquals(null, book);
    }

    @Test
    @DataSet({ "Book/createRequestNotPending.xml", "clean.xml" })
    public void noPendingRequests() {
        final List<Request> requests = handler.execute(serviceFactory -> {
            final BookRepository bookRepository = serviceFactory.findService(BookRepository.class);
            return bookRepository.findPendingRequests();
        });
        assertEquals(true, requests.isEmpty());
    }

    @SuppressWarnings("nls")
    @Test
    @DataSet({ "Book/createTwoOfEachRequest.xml", "clean.xml" })
    public void successTwoPendingRequests() {
        final List<Request> requests = handler.execute(serviceFactory -> {
            final BookRepository bookRepository = serviceFactory.findService(BookRepository.class);
            final UserRepository userRepository = serviceFactory.findService(UserRepository.class);
            final List<Request> results = bookRepository.findPendingRequests();
            final Book book = bookRepository.findBook(1);
            final User user = userRepository.findUser(120);
            assertEquals(2, results.size());
            assertPropertyLenientEquals("user", user, results.get(0));
            assertPropertyLenientEquals("book", book, results.get(0));
            assertPropertyLenientEquals("user", user, results.get(1));
            assertPropertyLenientEquals("book", book, results.get(1));
            return results;
        });

        final Request request1 = requests.get(0);
        assertPropertyLenientEquals("requestId", 1, request1);
        assertPropertyLenientEquals("typeId", 1, request1);

        final Request request2 = requests.get(1);
        assertPropertyLenientEquals("requestId", 2, request2);
        assertPropertyLenientEquals("typeId", 2, request2);

    }

    @Test
    @DataSet({ "Book/createRequestNotPending.xml", "clean.xml" })
    public void noPendingRequestsFromUser() {
        final List<Request> requests = handler.execute(serviceFactory -> {
            final BookRepository bookRepository = serviceFactory.findService(BookRepository.class);
            final UserRepository userRepository = serviceFactory.findService(UserRepository.class);
            final User user = userRepository.findUser(120);
            return bookRepository.findPendingRequests(user);
        });
        assertEquals(true, requests.isEmpty());
    }

    @SuppressWarnings("nls")
    @Test
    @DataSet({ "Book/createPendingFromUser.xml", "clean.xml" })
    public void successPendingRequestsFromUser() {
        final List<Request> requests = handler.execute(serviceFactory -> {
            final BookRepository bookRepository = serviceFactory.findService(BookRepository.class);
            final UserRepository userRepository = serviceFactory.findService(UserRepository.class);
            final User user = userRepository.findUser(120);
            final Book book = bookRepository.findBook(1);
            final List<Request> results = bookRepository.findPendingRequests(user);
            assertEquals(1, results.size());
            assertPropertyLenientEquals("user", user, results.get(0));
            assertPropertyLenientEquals("book", book, results.get(0));
            return results;
        });
        final Request request1 = requests.get(0);
        assertPropertyLenientEquals("requestId", 2, request1);
        assertPropertyLenientEquals("typeId", 2, request1);
    }
}